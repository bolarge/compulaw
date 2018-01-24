package com.congruent.compulaw.web.mvc;

import com.congruent.compulaw.domain.Role;
import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.service.PersonService;
//import com.congruent.compulaw.web.editor.DateTimeEditor;
import com.congruent.compulaw.web.form.Message;
import com.congruent.compulaw.web.form.PersonGrid;
import com.congruent.compulaw.web.util.UrlUtil;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/public/sb_pson")
public class PersonController{

  @Autowired
  private PersonService personService;

  @Autowired
  private MessageSource messageSource;
  
  final Logger logger = LoggerFactory.getLogger(PersonController.class);
  
  private final static boolean accountStatus = true;

  @RequestMapping(method=RequestMethod.GET)
  public String list(Model uiModel) {
    List<User> persons = this.personService.findAll();
    uiModel.addAttribute("persons", persons);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_pson/list";
  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    this.logger.info("Retrieving a User");
    User person = this.personService.findById(id);
    uiModel.addAttribute("person", person);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_pson/show";
  }

  @RequestMapping(value="/{id}", params="form", method=RequestMethod.POST)
  public String update(User person, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="file", required=false) Part file){
    
	this.logger.info("Updating User" + person.toString());
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.toString());
      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage("person_save_fail", new Object[0], locale)));
      uiModel.addAttribute("person", person);
      return "public/sb_pson/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", this.messageSource.getMessage("person_save_success", new Object[0], locale)));

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
        person.setPhoto(fileContent);
      } catch (IOException localIOException) {
        this.logger.error("Error saving uploaded file");
      }
      person.setPhoto(fileContent);
    }
    this.logger.info("Updating User 2");
    //
    //if(person.getPassword() != null){
    ShaPasswordEncoder encPassword = new ShaPasswordEncoder();
    person.setPassword(encPassword.encodePassword(person.getPassword(), null));
    //}
    //
    this.logger.info("Updating User 3");
    User updatedUser = this.personService.save(person);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "redirect:/public/sb_pson/" + UrlUtil.encodeUrlPathSegment(updatedUser.getId().toString(), httpServletRequest);
  }

  @RequestMapping(value="/{id}", params="form", method=RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute("person", this.personService.findById(id));
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_pson/update";
  }

  @RequestMapping(params="form", method=RequestMethod.POST)
  public String create(@Valid User person, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="file", required=false) Part file){
	  
	//Check if email is available for new user	
	  	User muUser = personService.getAccount(person.getEmail());
	  	if(muUser != null){
	  		uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
	  	        "person_email_used", new Object[0], locale)));
	  	      uiModel.addAttribute("person", person);
	  	      return "public/sb_pson/create";
	  	    }
	  	uiModel.asMap().clear();
	  
    this.logger.info("Creating person");
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.toString());
      uiModel.addAttribute(
        "message", 
        new Message("error", this.messageSource.getMessage(
        "person_save_fail", new Object[0], locale)));
      uiModel.addAttribute("person", person);
      return "public/sb_pson/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute(
      "message", 
      new Message("success", this.messageSource.getMessage(
      "person_save_success", new Object[0], locale)));
    this.logger.info("User id: " + person.getId());

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
        person.setPhoto(fileContent);
      } catch (IOException localIOException) {
        this.logger.error("Error saving uploaded file");
      }
      person.setPhoto(fileContent);
    }
    //Attached user to role
    Role role = new Role("ROLE_ADMIN");
   /* UserRole userRole = new UserRole();
    userRole.setUser(person);
    userRole.setRole(role);
    userRole.setCreatedBy("System");
    userRole.setCreated(new DateTime());
    person.getUserRoles().add(userRole);*/
    
    person.addRole(role);

    ShaPasswordEncoder encPassword = new ShaPasswordEncoder();
    person.setPassword(encPassword.encodePassword(person.getPassword(), null));

    this.personService.save(person);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "redirect:/public/sb_pson/" + UrlUtil.encodeUrlPathSegment(person.getId().toString(), httpServletRequest);
  }

  @RequestMapping(params="form", method=RequestMethod.GET)
  public String createForm(Model uiModel) {
    User person = new User();
    uiModel.addAttribute("person", person);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_pson/create";
  }

  @RequestMapping(value="/listgrid", method=RequestMethod.GET, produces="application/json")
  @ResponseBody
  public PersonGrid listGrid(@RequestParam(value="page", required=false) Integer page, 
		  @RequestParam(value="rows", required=false) Integer rows, 
		  @RequestParam(value="sidx", required=false) String sortBy, 
		  @RequestParam(value="sord", required=false) String order){
	  
    this.logger.info("Listing persons for grid with page: {}, rows: {}", page, rows);
    this.logger.info("Listing persons for grid with sort: {}, order: {}", sortBy, order);

    Sort sort = null;
    String orderBy = sortBy;
    if ((orderBy != null) && (orderBy.equals("birthDateString")))
      orderBy = "birthDate";
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
    Page<User> personPage = this.personService.findAllByPage(pageRequest);

    PersonGrid personGrid = new PersonGrid();
    personGrid.setCurrentPage(personPage.getNumber() + 1);
    personGrid.setTotalPages(personPage.getTotalPages());
    personGrid.setTotalRecords(personPage.getTotalElements());
    personGrid.setPersonData(Lists.newArrayList(personPage.iterator()));
    return personGrid;
  }

  @RequestMapping(value="/photo/{id}", method=RequestMethod.GET)
  @ResponseBody
  public byte[] downloadPhoto(@PathVariable("id") Long id) {
    User person = this.personService.findById(id);

    if (person.getPhoto() != null) {
      this.logger.info("Downloading photo for id: {} with size: {}", 
        person.getId(), Integer.valueOf(person.getPhoto().length));
    }
    return person.getPhoto();
  }
  
  /*@InitBinder
  void initBinder(WebDataBinder binder) {
	  binder.registerCustomEditor(DateTime.class, new DateTimeEditor("dd-MM-yyyy"));
  }*/
}