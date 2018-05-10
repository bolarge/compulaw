/*package com.congruent.compulaw.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.Subscription;
import com.congruent.compulaw.repository.SubscriptionItemRepository;
import com.congruent.compulaw.service.SubscriptionItemService;
import com.google.common.collect.Lists;

@Service("subscriptionItemService")
@Repository
@Transactional
public class SubscriptionItemServiceImpl implements SubscriptionItemService{
	
	@Autowired
	private SubscriptionItemRepository subscriptionRepoistory;
		
	final Logger logger = LoggerFactory.getLogger(SubscriptionItemServiceImpl.class);

	@Override
	public List<Subscription> findAll() {
		return Lists.newArrayList(subscriptionRepoistory.findAll());
	}

	@Override
	public Subscription findById(Long id) {
		return subscriptionRepoistory.findOne(id);
	}

	@Override
	public Subscription save(Subscription subscription) {
		return subscriptionRepoistory.save(subscription);
	}

}
*/