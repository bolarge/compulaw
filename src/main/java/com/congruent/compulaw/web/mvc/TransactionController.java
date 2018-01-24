package com.congruent.compulaw.web.mvc;

import com.congruent.compulaw.domain.Subscription;
import com.congruent.compulaw.domain.Transaction;
import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.enums.SubscriptionStatus;
import com.congruent.compulaw.enums.SubscriptionType;
import com.congruent.compulaw.repository.PersonRepository;
import com.congruent.compulaw.service.PersonService;
import com.congruent.compulaw.service.SubscriptionItemService;
import com.congruent.compulaw.service.SubscriptionService;
import com.congruent.compulaw.service.TransactionService;
//import com.congruent.compulaw.web.editor.DateTimeEditor;
import com.congruent.compulaw.web.editor.SubscriptionTypeEditor;
import com.congruent.compulaw.web.editor.TransactionEditor;
import com.congruent.compulaw.web.editor.UserSubscriptionIdEditor;
import com.congruent.compulaw.web.editor.UserTypeEditor;
import com.congruent.compulaw.web.form.Message;
import com.congruent.compulaw.web.form.TransactionGrid;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@SessionAttributes("subscription")
@RequestMapping("/public/sb_subs")
public class TransactionController{

  @Autowired
  private SubscriptionService subscriptionService;
  
  @Autowired
  private SubscriptionItemService subscriptionItemService;

  @Autowired
  private PersonRepository personRepository;

  @Autowired
  private TransactionService transactionService;

  @Autowired
  private PersonService userService;
  
  @Autowired
  private PersonService userDetailsService;

  @Autowired
  private MessageSource messageSource;
  
  final Logger logger = LoggerFactory.getLogger(TransactionController.class);
  private final static boolean accountStatus = true;

  @RequestMapping(method=RequestMethod.GET)
  public String list(Model uiModel) {
    List<Transaction> transactions = this.transactionService.findAll();
    uiModel.addAttribute("transactions", transactions);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_subs/list";
  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public String show(@PathVariable("id") String id, Model uiModel){
    Transaction transaction = this.transactionService.findByTransactionId(id);
    uiModel.addAttribute("transaction", transaction);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_subs/show";
  }

  @ModelAttribute("subscriptionItems")
  public List<Subscription> populateSubscriptionItems() {
    return this.subscriptionService.findAll();
  }

  @ModelAttribute("subscriptionPlans")
  public SubscriptionType[] subscriptionPlans() {
    return SubscriptionType.values();
  }
  
  @RequestMapping(value = "/getprice/{id}", method = RequestMethod.GET, produces="application/json")
	@ResponseBody
	public Subscription getSubscriptionPrice(@PathVariable("id") Long id) {
	  Subscription selectedPlan = this.subscriptionItemService.findById(id);
	  String price = selectedPlan.getPrice();
	  logger.info("Subscription price is: " + price);
	  return selectedPlan;
	}

  @ModelAttribute("user")
  public User currentUser(HttpServletRequest httpServletRequest) {
    String username = httpServletRequest.getRemoteUser();
    User thisUser = this.personRepository.findByEmail(username);
    return thisUser;
  }
  
  @RequestMapping(params="form", method=RequestMethod.GET)
  public String subscriptionForm(Model uiModel) {
	User user = this.userDetailsService.getCurrentUser();
    Transaction transaction = new Transaction();
    boolean accountStatus = this.transactionService.checkTransactionAccountStatus(user);
    uiModel.addAttribute("accountStatus", accountStatus);
    uiModel.addAttribute("transaction", transaction);
    return "public/sb_subs/subscr";
  }

  @RequestMapping(params="form", method=RequestMethod.POST)
  public String create(Transaction transaction, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus status){
    this.logger.info("Creating Transaction");
    
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.toString());
      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
        "subscription_save_fail", new Object[0], locale)));
      uiModel.addAttribute("subscriptionInstance", transaction);
      return "public/sb_subs/subscr";
    }
    //
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", this.messageSource.getMessage(
      "subscription_save_success", new Object[0], locale)));
    
    Transaction newTx = this.transactionService.save(transaction);
    //Check if user has transaction
    User user = this.userDetailsService.getCurrentUser();
    boolean accountStatus = this.transactionService.checkTransactionAccountStatus(user);
    uiModel.addAttribute("accountStatus", accountStatus);
    uiModel.addAttribute("newTx", newTx);

    return "public/sb_subs/summary";
    //return "redirect:/public/sb_subs/subsuccess" + UrlUtil.encodeUrlPathSegment(transaction.getTransactionId().toString(), 
    //httpServletRequest);
  }

  @RequestMapping(value={"/{id}"}, params={"form"}, method={RequestMethod.GET})
  public String subscriptionUpdate(@PathVariable("id") String transactionId, Model uiModel) {
    //
    Transaction transactionx = this.transactionService.findByTransactionId(transactionId);
    Transaction.UserSubscriptionId usi = new Transaction.UserSubscriptionId(transactionx.getUser().getId(), transactionx.getSubscription().getId());
    Transaction transaction = this.transactionService.findById(usi);
    this.logger.info("Display a Transaction " + transaction.getSubscription().getId());
    uiModel.addAttribute("accountStatus", accountStatus);
    uiModel.addAttribute("transaction", transaction);
    //
    return "public/sb_subs/approve";
  }

  @RequestMapping(value="/{id}", params="form", method=RequestMethod.POST)
  public String update(Transaction transaction, @PathVariable("id") String id, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, SessionStatus status){
    this.logger.info("Updating transaction ........ ");
    if (bindingResult.hasErrors()) {
      System.out.println(bindingResult.toString());
      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
        "subscription_save_fail", new Object[0], locale)));
      uiModel.addAttribute("transaction", transaction);
      return "public/sb_subs/approve";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", this.messageSource.getMessage("subscription_save_success", new Object[0], locale)));

    Transaction saction = this.transactionService.findByTransactionId(id);
    Transaction.UserSubscriptionId usi = new Transaction.UserSubscriptionId(saction.getUser().getId(), saction.getSubscription().getId());
    Transaction currentTx = this.transactionService.findById(usi);

    this.logger.info("Display Transaction Primary Key " + currentTx.getSubscription().getId());

    String uu = this.userService.getCurrentUser().getEmail();
    currentTx.setApprovedBy(uu);

    SubscriptionType tr = currentTx.getSubscriptionType();
    String dd = tr.getCode();
    Integer de = Integer.valueOf(Integer.parseInt(dd));
    this.logger.info("Display SubscriptionType value " + dd);

    DateTime startTime = new DateTime().toDateTime();
    DateTime dueTime = startTime.plusDays(de.intValue());

    currentTx.setDateApproved(startTime);
    currentTx.setDueDate(dueTime);

    currentTx.setStatus(SubscriptionStatus.APPROVED);
    currentTx.setEnabled(true);

    this.transactionService.save(currentTx);

    this.logger.info("Successfully Approved Subscription ....");
    //Return transaction summary page
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_subs/list";
  }

  @RequestMapping(value="/listgrid", method=RequestMethod.GET, produces="application/json")
  @ResponseBody
  public TransactionGrid listGrid(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="rows", required=false) Integer rows, @RequestParam(value="sidx", required=false) String sortBy, @RequestParam(value="sord", required=false) String order){
    this.logger.info("Listing transactions for grid with page: {}, rows: {}", page, rows);

    Sort sort = null;
    String orderBy = sortBy;
    if ((orderBy != null) && (orderBy.equals("subscriber")))
      orderBy = "subscriber";
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

    Page<Transaction> txsPage = this.transactionService.findAllByPage(pageRequest);

    TransactionGrid transactionGrid = new TransactionGrid();
    transactionGrid.setCurrentPage(txsPage.getNumber() + 1);
    transactionGrid.setTotalPages(txsPage.getTotalPages());
    transactionGrid.setTotalRecords(txsPage.getTotalElements());
    transactionGrid.setTransactionData(Lists.newArrayList(txsPage.iterator()));
    
    return transactionGrid;
  }

  @InitBinder
  void initBinder(WebDataBinder binder) {
    //binder.registerCustomEditor(DateTime.class, new DateTimeEditor("dd-MM-yyyy"));
    binder.registerCustomEditor(Subscription.class, new SubscriptionTypeEditor(this.subscriptionService));
    binder.registerCustomEditor(User.class, new UserTypeEditor(this.personRepository));
    binder.registerCustomEditor(Transaction.class, new TransactionEditor(this.transactionService));
    binder.registerCustomEditor(Transaction.UserSubscriptionId.class, new UserSubscriptionIdEditor(this.transactionService));
  }
}