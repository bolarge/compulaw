/*package com.congruent.compulaw.web.mvc;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.congruent.compulaw.domain.Subscription;
import com.congruent.compulaw.domain.Transaction;
import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.service.PersonService;
import com.congruent.compulaw.service.SubscriptionService;
import com.congruent.compulaw.service.TransactionService;
//import com.congruent.compulaw.web.editor.DateTimeEditor;
import com.congruent.compulaw.web.editor.SubscriptionTypeEditor;
import com.congruent.compulaw.web.editor.TransactionEditor;
import com.congruent.compulaw.web.editor.UserSubscriptionIdEditor;
import com.congruent.compulaw.web.form.Message;
import com.congruent.compulaw.web.util.Subscriber;

@Controller
@RequestMapping("/sb_waps")
public class PasswordController {
	
	  @Autowired
	  private MessageSource messageSource;

	  //@Autowired
	  //private PersonService userDetailsService;
	  
	  @Autowired
	  private PersonService personService;
	  
	  @Autowired
	  private SubscriptionService subscriptionService;

	  @Autowired
	  private TransactionService transactionService;
	  
	  private Logger logger = LoggerFactory.getLogger(SubscriptionRenewalController.class);
	  //private final static boolean accountStatus = true;
	  
	  @RequestMapping(method=RequestMethod.GET)
	  public String list(Model uiModel,User user) {
		 //uiModel.addAttribute("accountStatus", accountStatus);
	    return "sb_rnew/plist";
	  }
	
	  @RequestMapping(params="form", method= RequestMethod.GET)
	  public String subscriptionRenewForm(Model uiModel) {
		  logger.info("Returning subscriber verification page");
		  User person = new User();
		  uiModel.addAttribute("person", person);
		  //uiModel.addAttribute("accountStatus", accountStatus);
	    return "sb_rnew/aver";
	  }
	  
	  @RequestMapping(params="form", method=RequestMethod.POST)
	  public String verifySubscriberAccount(User person, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus status){
		  
		  User userVer = personService.getAccount(person.getEmail());
		  if (userVer == null) {
		      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
		        "subscription_verification_fail", new Object[0], locale)));
		      uiModel.addAttribute("person", person);
		      logger.info("User is not found");
		      return "sb_rnew/aver";
		    }
		  if (bindingResult.hasErrors()) {
		      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
		        "person_save_fail", new Object[0], locale)));
		      uiModel.addAttribute("person", person);
		      return "sb_rnew/aver";
		    }
		  //Transaction transaction = transactionService.findByUser(userVer);
		  uiModel.addAttribute("userVer", userVer);
		  //
		  return "sb_rnew/plist"; 
	  }
	  
	  @RequestMapping(value="/{id}", params="form", method=RequestMethod.GET)
	  public String renewSubscriptionForm(@PathVariable("id") Long id, Model uiModel){
		  uiModel.addAttribute("person", this.personService.findById(id));
		  logger.info("Change password form");
		  return "sb_rnew/pword";
	  }
	  
	  @RequestMapping(value="/{id}", params="form", method=RequestMethod.POST)
	  public String renewSubscription(Subscriber person, @PathVariable("id") Long id, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
		  this.logger.info("Changing Password ........ ");
		  	if(!person.getPassword().equals(person.getDescription())){
		    //if (bindingResult.hasErrors()) {
		      System.out.println(bindingResult.toString());
		      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
		        "password_change_fail", new Object[0], locale)));
		      uiModel.addAttribute("person", person);
		      return "sb_rnew/pword";
		    }
		    uiModel.asMap().clear();
		    redirectAttributes.addFlashAttribute("message", new Message("success", this.messageSource.getMessage("password_change_success", new Object[0], locale)));

		    User uu = personService.findById(id);
		    
		    ShaPasswordEncoder encPassword = new ShaPasswordEncoder();
		    uu.setPassword(encPassword.encodePassword(person.getPassword(), null));
		    
		    this.personService.save(uu);
		    //Return transaction summary page
		    uiModel.addAttribute("person", person);
		    return "login";
		    //return "sb_rnew/plist";
	  }
	  
	  @InitBinder
	  void initBinder(WebDataBinder binder) {
	    //binder.registerCustomEditor(DateTime.class, new DateTimeEditor("dd-MM-yyyy"));
	    binder.registerCustomEditor(Subscription.class, new SubscriptionTypeEditor(this.subscriptionService));
	    //binder.registerCustomEditor(User.class, new UserTypeEditor(this.personRepository));
	    binder.registerCustomEditor(Transaction.class, new TransactionEditor(this.transactionService));
	    binder.registerCustomEditor(Transaction.UserSubscriptionId.class, new UserSubscriptionIdEditor(this.transactionService));
	  }
}
*/