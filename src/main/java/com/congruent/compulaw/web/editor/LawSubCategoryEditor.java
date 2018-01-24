package com.congruent.compulaw.web.editor;

import java.beans.PropertyEditorSupport;

import com.congruent.compulaw.domain.LawSubCategory;
import com.congruent.compulaw.service.LawSubCategoryService;

public class LawSubCategoryEditor extends PropertyEditorSupport {

	//@Autowired
		private LawSubCategoryService lawSubCategoryService;

		public LawSubCategoryEditor() {}

		public LawSubCategoryEditor(LawSubCategoryService lawSubCategoryService) {
			super();
			this.lawSubCategoryService = lawSubCategoryService;
		}
		
		@Override
		public void setAsText(String text) throws IllegalArgumentException {
			if (text != null){
			Long lawCategoryId = Long.parseLong(text);
			LawSubCategory subCategory = lawSubCategoryService.findById(lawCategoryId);
			setValue(subCategory);
			}
		}
	
}
