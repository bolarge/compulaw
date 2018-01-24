package com.congruent.compulaw.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.congruent.compulaw.domain.User;

public class MyBeanValidationService {
	@Autowired
	private Validator validator;

	public Set<ConstraintViolation<User>> validateCustomer(User user) {
		return validator.validate(user);
	}
}
