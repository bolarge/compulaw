package com.congruent.compulaw.web.editor;

import com.congruent.compulaw.domain.Transaction;
import com.congruent.compulaw.service.TransactionService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class UserSubscriptionIdEditor extends PropertyEditorSupport{

  @Autowired
  TransactionService transactionService;

  public UserSubscriptionIdEditor(){}

  public UserSubscriptionIdEditor(TransactionService transactionService){
    this.transactionService = transactionService;
  }

  public void setAsText(String text) throws IllegalArgumentException{
	  
    Transaction saction = this.transactionService.findByTransactionId(text);
    Transaction.UserSubscriptionId usi = new Transaction.UserSubscriptionId(saction.getUser().getId(), saction.getSubscription().getId());
    setValue(usi);
    saction = null;
  }
}