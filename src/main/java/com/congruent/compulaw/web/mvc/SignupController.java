/*package com.congruent.compulaw.web.mvc;

//import com.congruent.compulaw.domain.Role;
import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.service.MailService;
import com.congruent.compulaw.service.PersonService;
import com.congruent.compulaw.web.form.Message;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/sys_auth")
@Controller
public class SignupController{

  @Autowired
  private PersonService personService;

  @Autowired
  MessageSource messageSource;

  @Autowired
  MailService mailService;
  private static Logger logger = LoggerFactory.getLogger(SignupController.class);
  
  private final static boolean accountStatus = true;
  private final static boolean userSession = false;

  @RequestMapping(params="form", method= RequestMethod.POST)
  public String create(@Valid User person, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
	  
	//Check if email is available for new user	
	  	User muUser = personService.getAccount(person.getEmail());
	  	if(muUser != null){
	  		uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
	  	        "person_email_used", new Object[0], locale)));
	  	      uiModel.addAttribute("person", person);
	  	      return "sys_auth/create";
	  	    }
	  	uiModel.asMap().clear();
	  	//return upon binding failure
	  if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
        "person_save_fail", new Object[0], locale)));
      uiModel.addAttribute("person", person);
      return "sys_auth/create";
    }
	 //send alert    		
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", this.messageSource.getMessage(
      "person_mail_sent", new Object[0], locale)));
    logger.info("User id: " + person.getId());
   
  //Attached user to role
   // Role role = new Role("ROLE_USER");
    UserRole userRole = new UserRole();
    userRole.setUser(person);
    userRole.setRole(role);
    userRole.setCreatedBy("System");
    userRole.setCreated(new DateTime());
    person.getUserRoles().add(userRole);
    
    //person.addRole(role);
    
    //Disable alert
    this.mailService.sendSubscriberConfirmationAlert(person);

    ShaPasswordEncoder encPassword = new ShaPasswordEncoder();
    person.setPassword(encPassword.encodePassword(person.getPassword(), null));
    person.setUserType("S");
    this.personService.save(person);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "sys_auth/verify";
  }
  
  @RequestMapping(params="form", method= RequestMethod.GET)
  public String createForm(Model uiModel){
    User person = new User();
    uiModel.addAttribute("person", person);
    uiModel.addAttribute("accountStatus", accountStatus);
	uiModel.addAttribute("userSession", userSession);
    return "sys_auth/create";
  }
}*/