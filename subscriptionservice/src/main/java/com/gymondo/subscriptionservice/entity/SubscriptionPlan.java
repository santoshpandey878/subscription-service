package com.gymondo.subscriptionservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gymondo.subscriptionservice.core.constant.SubscriptionType;

@Entity
@Table(name = "subscription_plan")
public class SubscriptionPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "price_per_month", nullable = false)
	private double pricePerMonth;
	
	@Column(name = "subscription_type")
	private SubscriptionType subscriptionType;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriptionPlan", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Subscription> subscriptions;

	@OneToOne(mappedBy = "subscriptionPlan")
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPricePerMonth() {
		return pricePerMonth;
	}

	public void setPricePerMonth(double pricePerMonth) {
		this.pricePerMonth = pricePerMonth;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}


}
