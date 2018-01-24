package com.congruent.compulaw.enums;

public enum SubscriptionType
{
  MONTHLY("30"), QUARTERLY("90"), BI_ANNUAL("180"), ANNUALLY("360");

  private String code;

  private SubscriptionType(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }

  public String toString()
  {
    return this.code;
  }
}