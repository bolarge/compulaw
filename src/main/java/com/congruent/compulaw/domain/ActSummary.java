package com.congruent.compulaw.domain;

import java.io.Serializable;
import java.util.Arrays;

public class ActSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;
	private byte[] document;
	
	public ActSummary(String title, byte[] document) {
		super();
		this.title = title;
		this.document = document;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "ActSummary [title=" + title + ", document="
				+ Arrays.toString(document) + "]";
	}
	
	
	

}
