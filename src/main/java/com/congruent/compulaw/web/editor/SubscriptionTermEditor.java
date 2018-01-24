package com.congruent.compulaw.web.editor;

import com.congruent.compulaw.enums.SubscriptionType;
import java.beans.PropertyEditorSupport;

public class SubscriptionTermEditor extends PropertyEditorSupport
{
  public void setAsText(String text)
    throws IllegalArgumentException
  {
    SubscriptionType sType = SubscriptionType.valueOf(text);

    setValue(sType);
  }
}