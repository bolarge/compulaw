package com.congruent.compulaw.web.converter;

import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.service.PersonService;

import org.springframework.core.convert.converter.Converter;

public class StringToUserConverter implements Converter<String, User> {

	private PersonService personService;

	public StringToUserConverter(PersonService personService) {
		this.personService = personService;
	}	

	@Override
	public User convert(String target) {
		String stringSource = target;
		if (stringSource != null && stringSource.length() > 0) {
			return personService.findById(Long.valueOf(stringSource));
		} else {
			return null;
		}
	}
	

	public StringToUserConverter() {
		super();
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
