package com.congruent.compulaw.repository;

import com.congruent.compulaw.domain.Transaction;
import com.congruent.compulaw.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Transaction.UserSubscriptionId>
{
  public Transaction findByTellerNumber(String paramString);

  public Transaction findByTransactionId(String paramString);

  public Transaction findByUser(User paramUser);
}