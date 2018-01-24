package com.congruent.compulaw.service;

import java.util.List;

import com.congruent.compulaw.domain.Subscription;

public interface SubscriptionItemService {
	
	//Find all service
	public List<Subscription> findAll();
	
	//Find by id
	public Subscription findById(Long id);
	
	// Save or Update a person object to the system
	public Subscription save(Subscription subscription);

}
