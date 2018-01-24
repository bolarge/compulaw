package com.congruent.compulaw.enums;

public enum UserType {

	SUBSCRIBER("S"), ADMINISTRATOR("A");
	
	private String code;

	private UserType(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return this.code;
	}
}
