package com.congruent.compulaw.web.mvc;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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

import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.domain.LawSubCategory;
import com.congruent.compulaw.service.LawCategoryService;
import com.congruent.compulaw.service.LawSubCategoryService;
import com.congruent.compulaw.web.editor.LawCategoryEditor;
import com.congruent.compulaw.web.editor.LawSubCategoryEditor;
import com.congruent.compulaw.web.form.LawSubCategoryGrid;
import com.congruent.compulaw.web.form.Message;
import com.congruent.compulaw.web.util.UrlUtil;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/public/sb_subcat")
public class LawSubCategoryController {

	final Logger logger = LoggerFactory.getLogger(LawSubCategoryController.class);

	@Autowired
	private LawSubCategoryService lawSubCategoryService;

	@Autowired
	private LawCategoryService lawCategoryService;

	@Autowired
	private MessageSource messageSource;
	
	private final static boolean accountStatus = true;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		List<LawSubCategory> lawSubCategories = this.lawSubCategoryService.findAll();
		uiModel.addAttribute("lawSubCategories", lawSubCategories);
		uiModel.addAttribute("accountStatus", accountStatus);
		return "public/sb_subcat/list";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		//this.logger.info("Retrieving a category");
		LawSubCategory subCategory = this.lawSubCategoryService.findById(id);
		uiModel.addAttribute("subCategory", subCategory);
		uiModel.addAttribute("accountStatus", accountStatus);
		return "public/sb_subcat/show";
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String update(LawSubCategory subCategory,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest,
			RedirectAttributes redirectAttributes, Locale locale) {
		//this.logger.info("Updating sub category");
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
			uiModel.addAttribute(
					"message",
					new Message("error", this.messageSource.getMessage(
							"sub_category_save_fail", new Object[0], locale)));
			uiModel.addAttribute("subCategory", subCategory);
			return "public/sb_subcat/update";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", this.messageSource.getMessage(
						"sub_category_save_fail", new Object[0], locale)));

		this.lawSubCategoryService.save(subCategory);
		uiModel.addAttribute("accountStatus", accountStatus);
		return "redirect:/public/sb_subcat/"
				+ UrlUtil.encodeUrlPathSegment(subCategory.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(value = "/{id}" , params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model uiModel) {
		uiModel.addAttribute("subCategory",
				this.lawSubCategoryService.findById(id));
		uiModel.addAttribute("accountStatus", accountStatus);
		return "public/sb_subcat/update";
	}

	@ModelAttribute("allcategories")
	public List<LawCategory> populateAllCategories() {
		return this.lawCategoryService.findAll();
	}

	@RequestMapping(params = "form", method = RequestMethod.POST )
	public String create(LawSubCategory subCategory,
			BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
		//this.logger.info("Creating sub category");
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult.toString());
			uiModel.addAttribute(
					"message",
					new Message("error", this.messageSource.getMessage(
							"sub_category_save_fail", new Object[0], locale)));
			uiModel.addAttribute("subCategory", subCategory);
			return "public/sb_subcat/create";
		}
		uiModel.asMap().clear();
		redirectAttributes.addFlashAttribute(
				"message",
				new Message("success", this.messageSource.getMessage(
						"sub_category_save_success", new Object[0], locale)));

		this.lawSubCategoryService.save(subCategory);
		uiModel.addAttribute("accountStatus", accountStatus);
		return "redirect:/public/sb_subcat/"
				+ UrlUtil.encodeUrlPathSegment(subCategory.getId().toString(),
						httpServletRequest);
	}

	@RequestMapping(params = "form", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		LawSubCategory subCategory = new LawSubCategory();
		//subCategory.setCategory(new LawCategory());
		uiModel.addAttribute("subCategory", subCategory);
		uiModel.addAttribute("accountStatus", accountStatus);
		return "public/sb_subcat/create";
	}

	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public LawSubCategoryGrid listGrid(
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order) {
		this.logger.info("Listing persons for grid with page: {}, rows: {}",
				page, rows);
		this.logger.info("Listing persons for grid with sort: {}, order: {}",
				sortBy, order);

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
			pageRequest = new PageRequest(page.intValue() - 1, rows.intValue(),
					sort);
		else {
			pageRequest = new PageRequest(page.intValue() - 1, rows.intValue());
		}
		Page<LawSubCategory> lawSubCategoryPage = this.lawSubCategoryService.findAllByPage(pageRequest);

		LawSubCategoryGrid lawSubCategoryGrid = new LawSubCategoryGrid();
		lawSubCategoryGrid.setCurrentPage(lawSubCategoryPage.getNumber() + 1);
		lawSubCategoryGrid.setTotalPages(lawSubCategoryPage.getTotalPages());
		lawSubCategoryGrid.setTotalRecords(lawSubCategoryPage.getTotalElements());
		lawSubCategoryGrid.setLawSubCategoryData(Lists.newArrayList(lawSubCategoryPage.iterator()));
		return lawSubCategoryGrid;
	}

	@InitBinder
	void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LawCategoryEditor.class, new LawCategoryEditor(this.lawCategoryService));
		binder.registerCustomEditor(LawSubCategoryEditor.class, new LawSubCategoryEditor(this.lawSubCategoryService));
	}


}
