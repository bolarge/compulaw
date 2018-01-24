package com.congruent.compulaw.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.exception.AuthenticationException;

public interface PersonService {

	public List<User> findAll();

	public User findById(Long paramLong);

	public User getCurrentUser();

	public User save(User paramUser);

	public Page<User> findAllByPage(Pageable paramPageable);

	public User login(String paramString1, String paramString2) throws AuthenticationException;

	public User getAccount(String paramString);

	public User findByUserName(String userName);
}
