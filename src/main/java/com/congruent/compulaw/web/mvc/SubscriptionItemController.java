/*package com.congruent.compulaw.web.mvc;

import com.congruent.compulaw.domain.Subscription;
import com.congruent.compulaw.enums.SubscriptionType;
import com.congruent.compulaw.service.SubscriptionItemService;
import com.congruent.compulaw.web.form.Message;
import com.congruent.compulaw.web.util.UrlUtil;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/public/sb_item")
public class SubscriptionItemController{

  @Autowired
  private SubscriptionItemService subscriptionItemService;

  @Autowired
  MessageSource messageSource;
  final Logger logger = LoggerFactory.getLogger(SubscriptionItemController.class);
  
  private final static boolean accountStatus = true;
  private final static boolean userSession = false;

  @RequestMapping(method=RequestMethod.GET)
  public String list(Model uiModel) {
    this.logger.info("Listing subscriptions services");
    List<Subscription> items = this.subscriptionItemService.findAll();
    uiModel.addAttribute("items", items);
    uiModel.addAttribute("accountStatus", accountStatus);
	uiModel.addAttribute("userSession", userSession);
    return "public/sb_item/list";
  }

  @RequestMapping(value="/{id}", method=RequestMethod.GET)
  public String show(@PathVariable("id") Long id, Model uiModel) {
    this.logger.info("Retrieving a subscription");   
    Subscription item = this.subscriptionItemService.findById(id);
    uiModel.addAttribute("item", item);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_item/show";
  }

  @ModelAttribute("subscriptionPlans")
  public SubscriptionType[] subscriptionPlans() {
    return SubscriptionType.values();
  }

  @RequestMapping(value="/{id}", params="form", method=RequestMethod.POST)
  public String update(@Valid Subscription item, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
    this.logger.info("Updating subscription offers");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
        "item_save_fail", new Object[0], locale)));
      uiModel.addAttribute("item", item);
      return "public/sb_item/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", 
      new Message("success", this.messageSource.getMessage(
      "item_save_success", new Object[0], locale)));

    this.subscriptionItemService.save(item);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "redirect:/public/sb_item" + UrlUtil.encodeUrlPathSegment(item.getId().toString(), 
      httpServletRequest);
  }

  @RequestMapping(value="/{id}", params="form", method=RequestMethod.GET)
  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
    uiModel.addAttribute("item", this.subscriptionItemService.findById(id));
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_item/update";
  }

  @RequestMapping(params="form", method=RequestMethod.POST)
  public String create(@Valid Subscription item, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale){
    this.logger.info("Creating subscription offer");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute(
        "message", 
        new Message("error", this.messageSource.getMessage(
        "item_save_fail", new Object[0], locale)));
      uiModel.addAttribute("item", item);
      return "public/sb_item/create";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute(
      "message", 
      new Message("success", this.messageSource.getMessage(
      "item_save_success", new Object[0], locale)));
    this.logger.info("item id: " + item.getId());

    this.subscriptionItemService.save(item);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "redirect:/public/sb_item" + 
      UrlUtil.encodeUrlPathSegment(item.getId().toString(), 
      httpServletRequest);
  }

  @RequestMapping(params="form", method=RequestMethod.GET)
  public String createForm(Model uiModel) {
    Subscription item = new Subscription();
    uiModel.addAttribute("item", item);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_item/create";
  }
}*/