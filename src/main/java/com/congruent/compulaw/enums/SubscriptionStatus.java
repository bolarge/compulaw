package com.congruent.compulaw.enums;

public enum SubscriptionStatus {
	
	REGISTERED("R"), SUBSCRIBED("S"), APPROVED("A"), DECLINED("D"), PENDING("P"), DUE("D");

	private String code;

	private SubscriptionStatus(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return this.code;
	}
}
