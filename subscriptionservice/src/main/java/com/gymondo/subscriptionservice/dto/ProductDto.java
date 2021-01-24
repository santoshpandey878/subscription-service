package com.gymondo.subscriptionservice.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(description="All details about product.")
public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private boolean active;
	private Set<VoucherDto> vouchers;

	public ProductDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message = "Product name cannot be empty")
	@Size(max = 100, message = "Product name cannot be greater than 100 charecters")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<VoucherDto> getVouchers() {
		return vouchers;
	}

	public void setVouchers(Set<VoucherDto> vouchers) {
		this.vouchers = vouchers;
	}


}
