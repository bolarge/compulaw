/*package com.congruent.compulaw.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.congruent.compulaw.domain.LawCategory;
import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.service.LawCategoryService;

public class StringToLawCategoryConverter implements Converter<String, LawCategory>{
	
	private LawCategoryService lawCategoryService;
	
	public StringToLawCategoryConverter(LawCategoryService lawCategoryService){
		this.lawCategoryService = lawCategoryService;
	}
	

	
	public StringToLawCategoryConverter() {
		super();
	}



	@Override
	public LawCategory convert(String target) {
		String stringSource = target;
		if (stringSource != null && stringSource.length() > 0) {
			return lawCategoryService.findById(Long.valueOf(stringSource));
		} else {
			return null;
		}
	}

	public Object convertSourceToTargetClass(Object source, Class<User> targetClass)
			throws Exception {
		if(source != null)
			return ((User) source).toString();
		else return null;
	}

	public Class<String> getSourceClass() {
		return String.class;
	}

	public Class<User> getTargetClass() {
		return User.class;
	}
}
*/