package com.congruent.compulaw.web.mvc;

import com.congruent.compulaw.domain.Role;
import com.congruent.compulaw.service.RoleService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/public/sb_auth")
public class RoleController
{

  @Autowired
  private RoleService roleService;

  @Autowired
  MessageSource messageSource;
  final Logger logger = LoggerFactory.getLogger(RoleController.class);
  
  private final static boolean accountStatus = true;

  @RequestMapping(method=RequestMethod.GET)
  public String list(Model uiModel) {
    //this.logger.info("Listing system roles");
    List<Role> roles = this.roleService.findAll();
    uiModel.addAttribute("roles", roles);
    uiModel.addAttribute("accountStatus", accountStatus);
    //this.logger.info("No. of system services: " + roles.size());
    return "public/sb_auth/list";
  }

  @RequestMapping(params="form", method=RequestMethod.POST)
  public String create(@Valid Role role, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale)
  {
    this.logger.info("Creating new role");
    if (bindingResult.hasErrors()) {
      uiModel.addAttribute(
        "message", 
        new Message("error", this.messageSource.getMessage(
        "person_save_fail", new Object[0], locale)));
      uiModel.addAttribute("role", role);
      return "public/sb_auth/create";
    }

    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute(
      "message", 
      new Message("success", this.messageSource.getMessage(
      "person_mail_sent", new Object[0], locale)));
    this.logger.info("User id: " + role.getRoleName());
    this.roleService.save(role);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "redirect:public/sb_auth/create/" + 
      UrlUtil.encodeUrlPathSegment(role.getRoleName(), 
      httpServletRequest);
  }

  @RequestMapping(params="form", method=RequestMethod.GET)
  public String createForm(Model uiModel) {
    Role role = new Role();
    uiModel.addAttribute("role", role);
    uiModel.addAttribute("accountStatus", accountStatus);
    return "public/sb_auth/create";
  }
}