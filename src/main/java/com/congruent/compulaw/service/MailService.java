package com.congruent.compulaw.service;

import com.congruent.compulaw.domain.Transaction;
import com.congruent.compulaw.domain.User;

public abstract interface MailService
{
  public void sendSubscriberConfirmationAlert(User paramUser);

  public void sendSalesAlert(Transaction paramTransaction);

  public void sendSubcriptionApprovalAlert(Transaction paramTransaction);
}