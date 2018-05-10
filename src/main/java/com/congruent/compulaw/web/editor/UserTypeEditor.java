/*package com.congruent.compulaw.web.editor;

import java.beans.PropertyEditorSupport;

import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.repository.PersonRepository;

public class UserTypeEditor extends PropertyEditorSupport{
	
	private PersonRepository personRepository;
	
	public UserTypeEditor(){}

	public UserTypeEditor(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		User subscriber = personRepository.findByEmail(text);
		setValue(subscriber);
	}

}
*/