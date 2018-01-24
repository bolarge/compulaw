package com.congruent.compulaw.domain;

public class CaselawSearchCriteria
{
  private String caseTitle;
  private String judge;
  private String counsels;
  private String citations1;
  private String citations2;
  private String citations3;
  private String keyword;

  public String getCaseTitle()
  {
    return this.caseTitle;
  }

  public void setCaseTitle(String caseTitle) {
    this.caseTitle = caseTitle;
  }

  public String getJudge() {
    return this.judge;
  }

  public void setJudge(String judge) {
    this.judge = judge;
  }

  public String getCounsels() {
    return this.counsels;
  }

  public void setCounsels(String counsels) {
    this.counsels = counsels;
  }

  public String getCitations1() {
    return this.citations1;
  }

  public void setCitations1(String citations1) {
    this.citations1 = citations1;
  }

  public String getCitations2() {
    return this.citations2;
  }

  public void setCitations2(String citations2) {
    this.citations2 = citations2;
  }

  public String getCitations3() {
    return this.citations3;
  }

  public void setCitations3(String citations3) {
    this.citations3 = citations3;
  }

  public String getKeyword() {
    return this.keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}