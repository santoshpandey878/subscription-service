package com.gymondo.subscriptionservice.core.constant;

/**
 * Enumerated values for subscription type
 */
public enum SubscriptionType {
	MONTHLY("MONTHLY"), 
	QUARTERLY("QUARTERLY"),
	HALF_YEARLY("HALF_YEARLY"),
	YEARLY("YEARLY");

	private String type;

	private SubscriptionType(String type) {
		this.type = type;
	}

	public String value(){
		return this.type;
	}
}