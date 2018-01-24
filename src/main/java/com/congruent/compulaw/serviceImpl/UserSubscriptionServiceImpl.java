/*package com.congruent.compulaw.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.UserSubscription;
import com.congruent.compulaw.repository.UserSubscriptionRepository;
import com.congruent.compulaw.service.UserSubscriptionService;
import com.google.common.collect.Lists;

@Service("userSubscriptionService")
@Repository
@Transactional
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
	
	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;
	
	final Logger logger = LoggerFactory.getLogger(UserSubscriptionServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public UserSubscription findById(Long id) {
		return userSubscriptionRepository.findOne(id);
	}

	@Override
	public UserSubscription updateTransaction(UserSubscription transaction) {
		return userSubscriptionRepository.save(transaction);
	}

	@Override
	public void deleteTransaction(Long TransactionId) {
		userSubscriptionRepository.delete(TransactionId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserSubscription> findAll() {
		//logger.info("Find All Method Called...." + userSubscriptionRepository.findAll().toString());
		return Lists.newArrayList(userSubscriptionRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserSubscription> findAllByPage(Pageable pageable) {
		return userSubscriptionRepository.findAll(pageable);
	}

	@Override
	public UserSubscription findByTransactionId(Long transactionid) {
		logger.info("Find by transaction is: " + userSubscriptionRepository.findByTransactionId(transactionid).toString());
		return userSubscriptionRepository.findByTransactionId(transactionid);
	}

}
*/