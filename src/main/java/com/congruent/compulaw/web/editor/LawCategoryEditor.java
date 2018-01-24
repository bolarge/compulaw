package com.congruent.compulaw.web.editor;

import java.beans.PropertyEditorSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.service.LawCategoryService;

public class LawCategoryEditor extends PropertyEditorSupport{
	
	final Logger logger = LoggerFactory.getLogger(LawCategoryEditor.class);

	private LawCategoryService lawCategoryService;

	public LawCategoryEditor() {}

	public LawCategoryEditor(LawCategoryService lawCategoryService) {
		this.lawCategoryService = lawCategoryService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		 this.logger.info("About to convert to Law category object.");
		long lawCategoryId = Long.parseLong(text);
		LawCategory category = lawCategoryService.findById(lawCategoryId);
		 this.logger.info("Successfully converted ..." + category.toString());
		setValue(category);
	}
	
}
