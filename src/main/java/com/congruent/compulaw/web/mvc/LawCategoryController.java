package com.congruent.compulaw.web.mvc;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.service.LawCategoryService;
import com.congruent.compulaw.web.form.LawCategoryGrid;
import com.congruent.compulaw.web.form.Message;
import com.congruent.compulaw.web.util.UrlUtil;
import com.google.common.collect.Lists;

@Controller
@RequestMapping({"/public/sb_lcat"})
public class LawCategoryController {
	
	final Logger logger = LoggerFactory.getLogger(LawCategoryController.class);

	  @Autowired
	  private LawCategoryService lawCategoryService;

	  @Autowired
	  private MessageSource messageSource;
	  
	  private final static boolean accountStatus = true;
	  
	  @RequestMapping(method={RequestMethod.GET})
	  public String list(Model uiModel) {
	    List<LawCategory> lawCategories = this.lawCategoryService.findAll();
	    uiModel.addAttribute("lawCategories", lawCategories);
	    uiModel.addAttribute("accountStatus", accountStatus);
	    return "public/sb_lcat/list";
	  }
	  
	  @RequestMapping(value={"/{id}"}, method={RequestMethod.GET})
	  public String show(@PathVariable("id") Long id, Model uiModel) {
	    this.logger.info("Retrieving a category");
	    LawCategory category = this.lawCategoryService.findById(id);
	    uiModel.addAttribute("category", category);
	    uiModel.addAttribute("accountStatus", accountStatus);
	    return "public/sb_lcat/show";
	  }
	  
	  @RequestMapping(value={"/{id}"}, params={"form"}, method={RequestMethod.POST})
	  public String update(LawCategory category, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale, @RequestParam(value="file", required=false) Part file)
	  {
	    this.logger.info("Updating category");
	    if (bindingResult.hasErrors()) {
	      System.out.println(bindingResult.toString());
	      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage("category_save_fail", new Object[0], locale)));
	      uiModel.addAttribute("category", category);
	      return "public/sb_lcat/update";
	    }
	    uiModel.asMap().clear();
	    redirectAttributes.addFlashAttribute("message", 
	    		new Message("success", this.messageSource.getMessage("category_save_success", new Object[0], locale)));
	    //Add Sub Category
	    //Set<LawSubCategory> theSubs = new HashSet<LawSubCategory>();	    
	    /*theSubs.add(new LawSubCategory(category.getSubCategory1()));
	    theSubs.add(new LawSubCategory(category.getSubCategory2()));
	    theSubs.add(new LawSubCategory(category.getSubCategory3()));
	    theSubs.add(new LawSubCategory(category.getSubCategory4()));
	    theSubs.add(new LawSubCategory(category.getSubCategory5()));
	    category.getTheSubCategories().addAll(theSubs);*/
	    
	    this.lawCategoryService.save(category);
	    uiModel.addAttribute("accountStatus", accountStatus);
	    return "redirect:/public/sb_lcat/" + 
	      UrlUtil.encodeUrlPathSegment(category.getId().toString(), 
	      httpServletRequest);
	  }

	  @RequestMapping(value={"/{id}"}, params={"form"}, method={RequestMethod.GET})
	  public String updateForm(@PathVariable("id") Long id, Model uiModel) {
	    uiModel.addAttribute("category", this.lawCategoryService.findById(id));
	    uiModel.addAttribute("accountStatus", accountStatus);
	    return "public/sb_lcat/update";
	  }

	  @RequestMapping(params={"form"}, method={RequestMethod.POST})
	  public String create(LawCategory category, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale)
	  {
	    this.logger.info("Creating category");
	    if (bindingResult.hasErrors()) {
	      System.out.println(bindingResult.toString());
	      uiModel.addAttribute("message", new Message("error", this.messageSource.getMessage(
	        "category_save_fail", new Object[0], locale)));
	      uiModel.addAttribute("category", category);
	      return "public/sb_lcat/create";
	    }
	    uiModel.asMap().clear();
	    redirectAttributes.addFlashAttribute("message", new Message("success", this.messageSource.getMessage(
	      "category_save_success", new Object[0], locale)));
	    this.logger.info("User id: " + category.getId()); 
	   this.lawCategoryService.save(category);
	   uiModel.addAttribute("accountStatus", accountStatus);
	    return "redirect:/public/sb_lcat/" + 
	      UrlUtil.encodeUrlPathSegment(category.getId().toString(), 
	      httpServletRequest);
	  }

	  @RequestMapping(params={"form"}, method={RequestMethod.GET})
	  public String createForm(Model uiModel) {
	    LawCategory category = new LawCategory();
	    uiModel.addAttribute("category", category);
	    uiModel.addAttribute("accountStatus", accountStatus);
	    return "public/sb_lcat/create";
	  }

	  @RequestMapping(value={"/listgrid"}, method={RequestMethod.GET}, produces={"application/json"})
	  @ResponseBody
	  public LawCategoryGrid listGrid(@RequestParam(value="page", required=false) Integer page, @RequestParam(value="rows", required=false) Integer rows, @RequestParam(value="sidx", required=false) String sortBy, @RequestParam(value="sord", required=false) String order)
	  {
	    this.logger.info("Listing category for grid with page: {}, rows: {}", page, rows);
	    this.logger.info("Listing catgeory for grid with sort: {}, order: {}", sortBy, order);

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
	    Page<LawCategory> lawCategoryPage = this.lawCategoryService.findAllByPage(pageRequest);

	    LawCategoryGrid lawCategoryGrid = new LawCategoryGrid();
	    lawCategoryGrid.setCurrentPage(lawCategoryPage.getNumber() + 1);
	    lawCategoryGrid.setTotalPages(lawCategoryPage.getTotalPages());
	    lawCategoryGrid.setTotalRecords(lawCategoryPage.getTotalElements());
	    lawCategoryGrid.setLawCategoryData(Lists.newArrayList(lawCategoryPage.iterator()));
	    return lawCategoryGrid;
	  }
}
