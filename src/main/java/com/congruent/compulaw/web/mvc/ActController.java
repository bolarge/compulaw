package com.congruent.compulaw.web.mvc;

import com.congruent.compulaw.domain.Act;
import com.congruent.compulaw.domain.ActSection;
import com.congruent.compulaw.domain.Document;
import com.congruent.compulaw.service.ActService;
import com.congruent.compulaw.service.DocumentService;
import com.congruent.compulaw.web.editor.DocumentEditor;
import com.congruent.compulaw.web.form.ActGrid;
import com.congruent.compulaw.web.form.Message;
import com.congruent.compulaw.web.util.UrlUtil;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/public/act")
@Controller
public class ActController{
	
  final Logger logger = LoggerFactory.getLogger(ActController.class);

  @Autowired
  private ActService actService;

  @Autowired
  private DocumentService documentService;

  @Autowired
  private MessageSource messageSource;
  
  private final static boolean accountStatus = true;

  @RequestMapping(method=RequestMethod.GET)
  public String list(Model uiModel) { 
	  //this.logger.info("Listing Acts");
    List<Act> acts = this.actService.findAll();
    uiModel.addAttribute("acts", acts);
    //this.logger.info("No. of Acts: " + acts.size());
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/act/list";
    }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel){
    //this.logger.info("Retrieving an Act");
    Act act = this.actService.findById(id);
    uiModel.addAttribute("act", act);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/act/show";
  }
  
  @RequestMapping(value="/document/{id}", method=RequestMethod.GET, produces="application/pdf")
  @ResponseBody
  public String showDocument(@PathVariable("id") Long id, Model uiModel, HttpServletRequest request, HttpServletResponse response) throws IOException {
    Act act = this.actService.findById(id);
    Document actDocument = act.getDocument();
    if ((actDocument == null) || (actDocument.getFileData().length == 0)) {
      response.getWriter().write("Error retrieving document. (ERR001)");
      return null;
    }
    response.setContentType("application/pdf");
    response.setContentLength(actDocument.getFileData().length);
    response.getOutputStream().write(actDocument.getFileData());
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/act/show";
  }

  @RequestMapping(value="/{id}", params="form", method=RequestMethod.POST)
  public String update(Act act, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="file", required=false) Part file){
    //this.logger.info("Updating an Act");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
        "act_save_fail", new Object[0], locale)));
      uiModel.addAttribute("act", act);
      return "public/act/edit";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", 
      new Message("success", this.messageSource.getMessage(
      "act_save_success", new Object[0], locale)));
    //this.logger.info("Act id: " + act.getId());

    Set<ActSection> sections = new HashSet<ActSection>();
    sections.add(new ActSection(act.getSectionTitle()));
    sections.add(new ActSection(act.getSection1()));
    sections.add(new ActSection(act.getSection2()));
    sections.add(new ActSection(act.getSection3()));
    sections.add(new ActSection(act.getSection4()));
    act.getTheSections().addAll(sections);

    Document actDoc = new Document();

    if (file != null) {
      this.logger.info("File name: " + file.getName());
      this.logger.info("File size: " + file.getSize());
      this.logger.info("File content type: " + file.getContentType());
      byte[] fileContent = (byte[])null;
      try {
        InputStream inputStream = file.getInputStream();
        if (inputStream == null)
          //this.logger.info("File inputstream is null");
        fileContent = IOUtils.toByteArray(inputStream);
        actDoc.setFileData(fileContent);
      } catch (IOException localIOException) {
        this.logger.error("Error saving uploaded file");
      }

      actDoc.setFileName(file.getName());
      actDoc.setDocumentType("A");
      actDoc.setCreated(new DateTime());
      actDoc.setFileData(fileContent);
      actDoc.setAct(act);
      act.setDocument(actDoc);
    }
    this.actService.save(act);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "redirect:/public/act/" + UrlUtil.encodeUrlPathSegment(act.getId().toString(), 
      httpServletRequest);
  }

  @RequestMapping(value="/{id}", params="form", method=RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute("act", this.actService.findById(id));
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/act/edit";
  }

  @RequestMapping(params="form", method=RequestMethod.POST)
  public String create(Act act, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="file", required=false) MultipartFile file){
    //this.logger.info("Creating New Act");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute(
        "message", 
        new Message("error", this.messageSource.getMessage(
        "act_save_fail", new Object[0], locale)));
      uiModel.addAttribute("act", act);
      return "public/act/edit";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute(
      "message", 
      new Message("success", this.messageSource.getMessage(
      "act_save_success", new Object[0], locale)));
    //this.logger.info("Act id: " + act.getId());

    Set<ActSection> sections = new HashSet<ActSection>();
    sections.add(new ActSection(act.getSectionTitle()));
    sections.add(new ActSection(act.getSection1()));
    sections.add(new ActSection(act.getSection2()));
    sections.add(new ActSection(act.getSection3()));
    sections.add(new ActSection(act.getSection4()));
    act.getTheSections().addAll(sections);

    Document actDoc = new Document();
    if (file != null) {
      this.logger.info("File name: " + file.getName());
      this.logger.info("File size: " + file.getSize());
      this.logger.info("File content type: " + file.getContentType());
      byte[] fileContent = (byte[])null;
      try {
        InputStream inputStream = file.getInputStream();
        if (inputStream == null)
          this.logger.info("File inputstream is null");
        fileContent = IOUtils.toByteArray(inputStream);
        actDoc.setFileData(fileContent);
      } catch (IOException localIOException) {
        this.logger.error("Error saving uploaded file");
      }

      actDoc.setFileName(file.getOriginalFilename());
      actDoc.setDocumentType("A");
      actDoc.setCreated(new DateTime());
      actDoc.setContentType(file.getContentType());
      actDoc.setFileData(fileContent);
      actDoc.setAct(act);
      act.setDocument(actDoc);
    }
    this.actService.save(act);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "redirect:/public/act/" + UrlUtil.encodeUrlPathSegment(act.getId().toString(), httpServletRequest);
  }

  @RequestMapping(params="form", method=RequestMethod.GET)
  public String createForm(@RequestParam(required=false, value="sectiontitle") String sectiontitle, Model uiModel){
    Act act = new Act();
    uiModel.addAttribute("act", act);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/act/edit";
  }

  @RequestMapping(value="/listgrid", method=RequestMethod.GET, produces="application/json")
  @ResponseBody
  public ActGrid listGrid(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="rows", required=false) Integer rows, @RequestParam(value="sidx", required=false) String sortBy, @RequestParam(value="sord", required=false) String order){
    this.logger.info("Listing acts for grid with page: {}, rows: {}", page, rows);

    Sort sort = null;
    String orderBy = sortBy;
    if ((orderBy != null) && (orderBy.equals("title")))
      orderBy = "title";
    if ((orderBy != null) && (order != null)) {
      if (order.equals("desc"))
        sort = new Sort(Sort.Direction.DESC, new String[] { orderBy });
      else {
        sort = new Sort(Sort.Direction.ASC, new String[] { orderBy });
      }

    }

    PageRequest pageRequest = null;
    if (sort != null)
      pageRequest = new PageRequest(page.intValue() - 1, rows.intValue(), sort);
    else {
      pageRequest = new PageRequest(page.intValue() - 1, rows.intValue());
    }

    Page<Act> actPage = this.actService.findAllByPage(pageRequest);

    ActGrid actGrid = new ActGrid();
    actGrid.setCurrentPage(actPage.getNumber() + 1);
    actGrid.setTotalPages(actPage.getTotalPages());
    actGrid.setTotalRecords(actPage.getTotalElements());
    actGrid.setActData(Lists.newArrayList(actPage.iterator()));
    return actGrid;
  }

  @InitBinder
  void initBinder(WebDataBinder binder){
    binder.registerCustomEditor(Document.class, new DocumentEditor(this.documentService));
  }
}