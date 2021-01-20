package com.gymondo.subscriptionservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.gymondo.subscriptionservice.core.constant.SubscriptionType;
import com.gymondo.subscriptionservice.core.utils.PostgreSQLEnumType;

@Entity
@Table(name = "subscription_plan")
@TypeDef(name = "enumType",typeClass = PostgreSQLEnumType.class)
public class SubscriptionPlan {

	private Long id;
	private double pricePerMonth;
	private SubscriptionType subscriptionType;
	private List<Subscription> subscriptions;
	private Product product;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "price_per_month", nullable = false)
	public double getPricePerMonth() {
		return pricePerMonth;
	}

	public void setPricePerMonth(double pricePerMonth) {
		this.pricePerMonth = pricePerMonth;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriptionPlan", cascade = CascadeType.REMOVE, orphanRemoval = true)
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@OneToOne(mappedBy = "subscriptionPlan")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Enumerated(EnumType.STRING)
	@Type(type = "enumType")
	@Column(name = "subscription_type")
	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}


}
