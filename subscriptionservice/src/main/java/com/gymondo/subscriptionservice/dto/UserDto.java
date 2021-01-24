package com.gymondo.subscriptionservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(description="All details about user.")
public class UserDto {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;

	public UserDto() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank(message = "First name cannot be empty")
	@Size(max = 50, message = "First name cannot be greater than 50 charecters")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotBlank(message = "Last name cannot be empty")
	@Size(max = 50, message = "Last name cannot be greater than 50 charecters")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@NotEmpty(message = "Email cannot be empty")
	@Size(max = 100, message = "Email cannot be greater than 100 charecters")
	@Email
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
