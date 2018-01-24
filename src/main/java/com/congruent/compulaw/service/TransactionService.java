package com.congruent.compulaw.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.congruent.compulaw.domain.Transaction;
import com.congruent.compulaw.domain.User;

public interface TransactionService {
	public Transaction findById(Transaction.UserSubscriptionId paramUserSubscriptionId);

	  public Transaction save(Transaction paramTransaction);

	  public void deleteTransaction(Transaction.UserSubscriptionId paramUserSubscriptionId);

	  public List<Transaction> findAll();

	  public Transaction findByTransactionId(String paramString);

	  public Page<Transaction> findAllByPage(Pageable pageable);

	  public boolean isAccountActive(String paramString);

	  public boolean checkTransactionAccountStatus(User paramUser); 
	  
	  public boolean expireSubscription(User user);

	public Transaction findByUser(User user);

}
