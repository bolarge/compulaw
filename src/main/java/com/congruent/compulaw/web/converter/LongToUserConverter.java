/*package com.congruent.compulaw.web.converter;

import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.service.PersonService;

import org.springframework.core.convert.converter.Converter;

public class LongToUserConverter implements Converter<Object, Object> {

	public LongToUserConverter() {
		super();
	}

	private PersonService personService;

	public LongToUserConverter(PersonService personService) {
		this.personService = personService;
	}	

	public Object convertTargetToSourceClass(User target, Class<Long> sourceClass)
			throws Exception {
		User person = target;
		return person.toString();
	}

	@Override
	public Object convert(Object source){
		Long longSource = (Long) source;
		if (longSource != null && longSource > 0) {
			return personService.findById(longSource);
		} else {
			return null;
		}
	}
}
*/