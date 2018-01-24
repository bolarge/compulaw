package com.congruent.compulaw.web.mvc;

import com.congruent.compulaw.domain.Caselaw;
import com.congruent.compulaw.domain.Counsel;
import com.congruent.compulaw.domain.Document;
import com.congruent.compulaw.domain.Justice;
import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.domain.LawSubCategory;
import com.congruent.compulaw.service.CaselawService;
import com.congruent.compulaw.service.DocumentService;
import com.congruent.compulaw.service.LawCategoryService;
import com.congruent.compulaw.service.LawSubCategoryService;
//import com.congruent.compulaw.web.editor.DateTimeEditor;
import com.congruent.compulaw.web.editor.DocumentEditor;
import com.congruent.compulaw.web.editor.LawCategoryEditor;
import com.congruent.compulaw.web.editor.LawSubCategoryEditor;
import com.congruent.compulaw.web.form.CaselawGrid;
import com.congruent.compulaw.web.form.Message;
import com.congruent.compulaw.web.util.UrlUtil;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/public/caselaw")
public class CaselawController{
	
  final Logger logger = LoggerFactory.getLogger(CaselawController.class);

  @Autowired
  private CaselawService caselawService;

  @Autowired
  private LawCategoryService lawCategoryService;

  @Autowired
  private LawSubCategoryService lawSubCategoryService;

  @Autowired
  private DocumentService documentService;

  @Autowired
  private MessageSource messageSource;
  
  private final static boolean accountStatus = true;
  
  /*
   * All Method here are for Administrative purpose of the system.
   * Do NOT modify/alter unless you are sure of what you doing duhhh
   */
  
  @RequestMapping(method=RequestMethod.GET)
  public String list(Model uiModel){
	List<Caselaw> caselaws = this.caselawService.findAll();
    uiModel.addAttribute("caselaws", caselaws);
    //this.logger.info("No. of caselaws: " + caselaws.size());
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/caselaw/list"; 
  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel){
    this.logger.info("Retrieving a Case law for display");
    Caselaw caselaw = this.caselawService.findById(id);
    uiModel.addAttribute("caselaw", caselaw);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/caselaw/show";
  }
  
  @RequestMapping(value="/document/{id}", method=RequestMethod.GET, produces="application/pdf")
  @ResponseBody
  public String showDocument(@PathVariable("id") Long id, Model uiModel, HttpServletRequest request, HttpServletResponse response) throws IOException {
    Caselaw caselaw = this.caselawService.findById(id);
    Document caseDocument = caselaw.getCaseLawDocument();
    if ((caseDocument == null) || (caseDocument.getFileData().length == 0)) {
      response.getWriter().write("Error retrieving document. (ERR001)");
      return null;
    }
    response.setContentType("application/pdf");
    response.setContentLength(caseDocument.getFileData().length);
    response.getOutputStream().write(caseDocument.getFileData());
    uiModel.addAttribute("accountStatus", accountStatus);
    return null;
  }

  @RequestMapping(value="/{id}", params="form", method = RequestMethod.POST)
  public String update(@ModelAttribute("caselaw") Caselaw caselaw, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="file", required=false) Part file){
    this.logger.info("Updating Caselaw: ");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
        "caselaw_save_fail", new Object[0], locale)));
      uiModel.addAttribute("caselaw", caselaw);
      return "public/caselaw/edit";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", 
      new Message("success", this.messageSource.getMessage(
      "caselaw_save_success", new Object[0], locale)));
    this.logger.info("Caselaw id: " + caselaw.getId());
    Document caseLawDocument = new Document();

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
        caseLawDocument.setFileData(fileContent);
      } catch (IOException localIOException) {
        this.logger.error("Error saving uploaded file");
      }
      caseLawDocument.setFileName(file.getName());
      caseLawDocument.setDocumentType("C");
      caseLawDocument.setCreated(new DateTime());
      caseLawDocument.setFileData(fileContent);
      caselaw.setCaseLawDocument(caseLawDocument);
    }
    this.caselawService.save(caselaw);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "redirect:/public/caselaw/" + UrlUtil.encodeUrlPathSegment(caselaw.getId().toString(), 
      httpServletRequest);
  }

  @RequestMapping(value="/{id}", params="form", method=RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute("caselaw", this.caselawService.findById(id));
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/caselaw/edit";
  }

  @ModelAttribute("lawCategories")
  public List<LawCategory> populateLawCategories() {
    return this.lawCategoryService.findAll();
  }

  @ModelAttribute("lawSubCategories")
  public List<LawSubCategory> populateLawSubCategories() {
    return this.lawSubCategoryService.findAll();
  }
  
  @ModelAttribute("justices")
  public List<Justice> populateJustice(){
	  return this.caselawService.getAllJustice();
  }
  
  @ModelAttribute("allcounsels")
  public List<Counsel> populateCounsel(){
	  return this.caselawService.getAllCounsel();
  }

  @RequestMapping(params="form", method=RequestMethod.POST)
  public String create(Caselaw caselaw, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="file", required=false) Part file){
    this.logger.info("Creating New Caselaw");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute(
        "message", 
        new Message("error", this.messageSource.getMessage(
        "caselaw_save_fail", new Object[0], locale)));
      uiModel.addAttribute("caselaw", caselaw);
      return "public/caselaw/edit";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute(
      "message", 
      new Message("success", this.messageSource.getMessage(
      "caselaw_save_success", new Object[0], locale)));
    //
    Document cc = new Document();
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
        cc.setFileData(fileContent);
      } catch (IOException localIOException) {
        this.logger.error("Error saving uploaded file");
      }
      //Add caselaw to sub category
      //LawSubCategory lsc = lawSubCategoryService.findById(caselaw.get)   
      cc.setFileName(file.getName());
      cc.setDocumentType("C");
      cc.setCreated(new DateTime());
      cc.setFileData(fileContent);
      caselaw.setCaseLawDocument(cc);
    }  
    caselaw.getTheSubCategories().add(new LawSubCategory(caselaw.getSubCategory()));
    this.caselawService.save(caselaw);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "redirect:/public/caselaw/" + 
      UrlUtil.encodeUrlPathSegment(caselaw.getId().toString(), httpServletRequest);
  }

  @RequestMapping(params="form", method=RequestMethod.GET)
  public String createForm(Model uiModel) {
    Caselaw caselaw = new Caselaw();
    uiModel.addAttribute("caselaw", caselaw);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/caselaw/edit";
  }
  
  @RequestMapping(value="/listgrid", method=RequestMethod.GET, produces="application/json")
  @ResponseBody
  public CaselawGrid listGrid(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="rows", required=false) Integer rows, @RequestParam(value="sidx", required=false) String sortBy, @RequestParam(value="sord", required=false) String order){
    this.logger.info("Listing case-law for grid with page: {}, rows: {}", page, rows);
    Sort sort = null;
    String orderBy = sortBy;
    if ((orderBy != null) && (orderBy.equals("caseTitle"))) {
      orderBy = "caseTitle";
    }
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
    Page<Caselaw> caselawPage = this.caselawService.findAllByPage(pageRequest);
    CaselawGrid caselawGrid = new CaselawGrid();
    caselawGrid.setCurrentPage(caselawPage.getNumber() + 1);
    caselawGrid.setTotalPages(caselawPage.getTotalPages());
    caselawGrid.setTotalRecords(caselawPage.getTotalElements());
    caselawGrid.setCaselawData(Lists.newArrayList(caselawPage.iterator()));
    return caselawGrid;
  }

  @InitBinder
  void initBinder(WebDataBinder binder) {
    //binder.registerCustomEditor(DateTime.class, new DateTimeEditor("dd-MM-yyyy"));
    binder.registerCustomEditor(LawCategory.class, new LawCategoryEditor(this.lawCategoryService));
    binder.registerCustomEditor(LawSubCategory.class, new LawSubCategoryEditor(this.lawSubCategoryService));
    binder.registerCustomEditor(Document.class, new DocumentEditor(this.documentService));
  }
}