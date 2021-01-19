package com.gymondo.subscriptionservice.core.constant;

/**
 * Enumerated values for subscription status
 */
public enum SubscriptionStatus {
	ACTIVE("ACTIVE"), 
	CANCEL("CANCEL"),
	PAUSE("PAUSE");

	private String type;

	private SubscriptionStatus(String type) {
		this.type = type;
	}

	public String value(){
		return this.type;
	}
}
