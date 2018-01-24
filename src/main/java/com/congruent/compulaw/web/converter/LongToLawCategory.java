package com.congruent.compulaw.web.converter;

import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.service.LawCategoryService;
import org.springframework.core.convert.converter.Converter;

public class LongToLawCategory implements Converter<Object, Object>{
	
private LawCategoryService lawCategoryService;
	
	public LongToLawCategory(LawCategoryService lawCategoryService){
		this.lawCategoryService = lawCategoryService;
	}
	
	public Object convertTargetToSourceClass(LawCategory target, Class<Long> sourceClass)
			throws Exception {
		LawCategory category = target;
		return category.toString();
	}
	

	public LongToLawCategory() {
		super();
	}

	@Override
	public Object convert(Object source){
		Long longSource = (Long) source;
		if (longSource != null && longSource > 0) {
			return lawCategoryService.findById(longSource);
		} else {
			return null;
		}
	}

}
