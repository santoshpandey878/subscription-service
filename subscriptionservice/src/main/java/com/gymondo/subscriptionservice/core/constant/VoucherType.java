package com.gymondo.subscriptionservice.core.constant;

public enum VoucherType {
	FIXED_AMOUNT("FIXED_AMOUNT"), 
	PERCENTAGE("PERCENTAGE");

	private String type;

	private VoucherType(String type) {
		this.type = type;
	}

	public String value(){
		return this.type;
	}
}
