package com.congruent.compulaw.serviceImpl;

import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.congruent.compulaw.domain.Transaction;
import com.congruent.compulaw.domain.Transaction.UserSubscriptionId;
import com.congruent.compulaw.domain.User;
import com.congruent.compulaw.enums.SubscriptionStatus;
import com.congruent.compulaw.repository.PersonRepository;
import com.congruent.compulaw.repository.TransactionRepository;
import com.congruent.compulaw.service.MailService;
import com.congruent.compulaw.service.TransactionService;
import com.google.common.collect.Lists;

@Service("transactionService")
@Repository
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private MailService mailService;
	
	final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public Transaction findById(UserSubscriptionId paramUserSubscriptionId) {
		return transactionRepository.findOne(paramUserSubscriptionId);
	}

	@Override
	public Transaction save(Transaction tx) {
		Transaction newTx = null;
		if (tx.getTransactionId() == null) {
			if (tx.getId() != null) {
				Long uuid = Long.valueOf(new Random().nextLong() + 1L);
				tx.setTransactionId(uuid.toString());

				//this.logger.info("In Here Now................."+ tx.getSubscriptionType());
				newTx = new Transaction(tx.getApprovedBy(), tx.getUser(),
						tx.getSubscription(), tx.getDateCreated(),
						tx.getDateApproved(), tx.getTellerDate(),
						tx.getDueDate(), tx.getTellerNumber(),
						tx.getAmountPaid(), tx.getBankName(), tx.isEnabled(),
						tx.getVersion(), tx.getSubscription().getItemName(), tx
								.getUser().getEmail(), tx.getTransactionId(),
						tx.getStatus(), tx.getSubscriptionType());
			}
			Transaction processedTx = this.transactionRepository.save(newTx);
			//Disabled alert
			//this.mailService.sendSalesAlert(processedTx);
			return processedTx;
		} else {
			Transaction approvedTx = this.transactionRepository.save(tx);
			//Disabled Alert
			//this.mailService.sendSubcriptionApprovalAlert(approvedTx);
			return approvedTx;
		}
	}

	@Override
	public void deleteTransaction(UserSubscriptionId paramUserSubscriptionId) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Transaction> findAll() {
		return Lists.newArrayList(transactionRepository.findAll());
	}

	@Override
	public Transaction findByTransactionId(String paramString) {
		return transactionRepository.findByTransactionId(paramString);
	}

	@Override
	public Page<Transaction> findAllByPage(Pageable paramPageRequest) {
		return transactionRepository.findAll(paramPageRequest);
	}

	@Override
	public boolean isAccountActive(String paramString) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Transaction renewSubscription(Transaction transaction){
		return null;
	}
	
	@Override
	public boolean expireSubscription(User user){
		boolean isExpire = false;
		//Get user subscription account object 
		Transaction tx = this.transactionRepository.findByUser(user);
		DateTime subscriptionDueDate = tx.getDueDate();
		new DateTime();
		//Check if due date is mature
		if(subscriptionDueDate.isAfter(DateTime.now())){
			 //due date is greater than Now keep track on subscription			
			 this.logger.info("Subscription not due" + tx.toString());		 
			 isExpire = true;
		}else{
			//due date is less than Now so expired subscription
			User subscriber = tx.getUser();
			
			subscriber.setEnabled(false);
			this.logger.info("Subscriber is " + subscriber.toString());
			User disabledUser = this.personRepository.save(subscriber);
			this.logger.info("Disabled Subscriber is " + disabledUser.isEnabled());
			if(disabledUser.isEnabled() != true){
				//Disable user subscription 
				tx.setEnabled(false);
				this.transactionRepository.save(tx);
				this.logger.info("Expired Subscription is: " + tx.toString());
				subscriber = null;
				//Set logged in user to null
				boolean dueStatus = false;//cannot log in
				isExpire = dueStatus;
			}
		}
		return isExpire;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean checkTransactionAccountStatus(User user) {
		boolean status = false;
		Transaction tx = this.transactionRepository.findByUser(user);
		//boolean isSubscriptionActive = expireSubscription(user);
		//Check user subscription
		if (user != null) {
			//Check if subscribed to a plan isSubscriptionActive != false if (tx != null) {
			if (tx != null) {
				//Check account status
				//Transaction tx = this.transactionRepository.findByUser(user);
				SubscriptionStatus approvedStatus = SubscriptionStatus.APPROVED;
				//SubscriptionStatus approvedUserStatus = tx.getStatus();
				logger.info("Subscription status is ......" + tx.getStatus());
				if(approvedStatus.equals(tx.getStatus())){
					boolean isAccountEnabled = expireSubscription(user);
					if (isAccountEnabled) {
						status = isAccountEnabled;
						this.logger.info("User subscription is active: " + isAccountEnabled);
					} else {
						status = isAccountEnabled;
						this.logger.info("User subscription is NOT active: " + isAccountEnabled);
					}
				}
				else{
					status = false;
				}
			}
		} else {
			boolean pp = false;
			this.logger.info("User does not exist: ");
			status = pp;
		}
		return status;
	}

	@Override
	public Transaction findByUser(User user) {
		
		return this.transactionRepository.findByUser(user);
	}
	
}


