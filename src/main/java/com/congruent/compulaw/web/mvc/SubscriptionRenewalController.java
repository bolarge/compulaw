package com.congruent.compulaw.web.mvc;

import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.congruent.compulaw.enums.SubscriptionStatus;
import com.congruent.compulaw.enums.SubscriptionType;
import com.congruent.compulaw.service.PersonService;
import com.congruent.compulaw.service.SubscriptionService;
import com.congruent.compulaw.service.TransactionService;
//import com.congruent.compulaw.web.editor.DateTimeEditor;
import com.congruent.compulaw.web.editor.SubscriptionTypeEditor;
import com.congruent.compulaw.web.editor.TransactionEditor;
import com.congruent.compulaw.web.editor.UserSubscriptionIdEditor;
import com.congruent.compulaw.web.form.Message;

@Controller
@RequestMapping("/sb_rnew")
public class SubscriptionRenewalController {
	
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
	    return "sb_rnew/list";
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
		  Transaction transaction = transactionService.findByUser(userVer);
		  uiModel.addAttribute("transaction", transaction);
		  //
		  return "sb_rnew/list"; 
	  }
	  
	  @RequestMapping(value="/{id}", params="form", method=RequestMethod.GET)
	  public String renewSubscriptionForm(@PathVariable("id") String id, Model uiModel){
		  uiModel.addAttribute("transaction", this.transactionService.findByTransactionId(id));
		  logger.info("Show is called ");
		  return "sb_rnew/rnew";
	  }
	  
	  @RequestMapping(value="/{transactionId}", params="form", method=RequestMethod.POST)
	  public String renewSubscription(Transaction transaction, @PathVariable("transactionId") String transactionId, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
		  this.logger.info("Renewing Subscription ........ ");
		    if (bindingResult.hasErrors()) {
		      System.out.println(bindingResult.toString());
		      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
		        "subscription_renewal_fail", new Object[0], locale)));
		      uiModel.addAttribute("transaction", transaction);
		      return "sb_rnew/rnew";
		    }
		    uiModel.asMap().clear();
		    redirectAttributes.addFlashAttribute("message", new Message("success", this.messageSource.getMessage("subscription_save_success", new Object[0], locale)));

		    Transaction saction = this.transactionService.findByTransactionId(transactionId);
		    Transaction.UserSubscriptionId usi = new Transaction.UserSubscriptionId(saction.getUser().getId(), saction.getSubscription().getId());
		    Transaction currentTx = this.transactionService.findById(usi);

		    this.logger.info("Display Transaction Primary Key " + currentTx.getSubscription().getId());

		    Long uuid = Long.valueOf(new Random().nextLong() + 1L);
		    currentTx.setTransactionId(uuid.toString());

		    SubscriptionType tr = currentTx.getSubscriptionType();
		    String dd = tr.getCode();
		    Integer de = Integer.valueOf(Integer.parseInt(dd));
		    this.logger.info("Display SubscriptionType value " + dd);

		    DateTime startTime = new DateTime().toDateTime();
		    DateTime dueTime = startTime.plusDays(de.intValue());

		    currentTx.setDateApproved(startTime);
		    currentTx.setDueDate(dueTime);

		    currentTx.setStatus(SubscriptionStatus.PENDING);
		    currentTx.setEnabled(false);

		    this.transactionService.save(currentTx);

		    //Return transaction summary page
		    uiModel.addAttribute("currentTx", currentTx);
		    return "sb_rnew/rnewsum";
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
