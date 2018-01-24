package com.congruent.compulaw.domain;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="DOCUMENT")
@Entity
public class Document
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Long id;
  private String fileName;
  private String contentType;
  private String documentType;
  private byte[] fileData;
  private DateTime created;
  private int version = 0;
  private Act act;

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name="DOCUMENT_ID")
  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  @Lob
  @Basic(fetch=FetchType.LAZY)
  @Column(name="CONTENT")
  public byte[] getFileData() { return this.fileData; }

  public void setFileData(byte[] fileData)
  {
    this.fileData = fileData;
  }

  @Column(name="CONTENT_TYPE")
  public String getContentType() {
    return this.contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
  @org.springframework.format.annotation.DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
  @Column(name="CREATED")
  public DateTime getCreated() { return this.created; }

  @Transient
  public String getCreatedString()
  {
    String createdString = "";
    if (this.created != null)
      createdString = org.joda.time.format.DateTimeFormat.forPattern(
        "dd-MM-yyyy").print(this.created);
    return createdString;
  }

  public void setCreated(DateTime created) {
    this.created = created;
  }

  @Column(name="DOCUMENT_TYPE")
  public String getDocumentType() {
    return this.documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  @Column(name="FILENAME")
  public String getFileName() {
    return this.fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  @Column(name="VERSION")
  public int getVersion() {
    return this.version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  @JsonIgnore
  @OneToOne(mappedBy="document")
  public Act getAct()
  {
    return this.act;
  }

  public void setAct(Act act) {
    this.act = act;
  }

  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.fileName == null ? 0 : this.fileName.hashCode());
    result = prime * result + (this.id == null ? 0 : this.id.hashCode());
    result = prime * result + this.version;
    return result;
  }

  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Document other = (Document)obj;
    if (this.fileName == null) {
      if (other.fileName != null)
        return false;
    } else if (!this.fileName.equals(other.fileName))
      return false;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (this.version != other.version)
      return false;
    return true;
  }

  public String toString()
  {
    return "Document [getId()=" + getId() + ", getFileData()=" + 
      Arrays.toString(getFileData()) + ", getContentType()=" + 
      getContentType() + ", getCreated()=" + getCreated() + 
      ", getCreatedString()=" + getCreatedString() + 
      ", getDocumentType()=" + getDocumentType() + 
      ", getFileName()=" + getFileName() + "]";
  }
}