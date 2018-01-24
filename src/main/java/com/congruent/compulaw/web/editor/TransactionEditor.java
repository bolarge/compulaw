package com.congruent.compulaw.web.editor;

import com.congruent.compulaw.domain.Transaction;
import com.congruent.compulaw.service.TransactionService;
import java.beans.PropertyEditorSupport;

public class TransactionEditor extends PropertyEditorSupport
{
  private TransactionService uSubService;

  public TransactionEditor()
  {
  }

  public TransactionEditor(TransactionService uSubService)
  {
    this.uSubService = uSubService;
  }

  public void setAsText(String text) throws IllegalArgumentException
  {
    Transaction uSub = this.uSubService.findByTransactionId(text);
    setValue(uSub);
  }
}