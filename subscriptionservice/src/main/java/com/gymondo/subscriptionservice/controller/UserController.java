package com.gymondo.subscriptionservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gymondo.subscriptionservice.core.utils.DtoConverter;
import com.gymondo.subscriptionservice.dto.UserDto;
import com.gymondo.subscriptionservice.entity.User;
import com.gymondo.subscriptionservice.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *User controller class responsible to handle client requests to create and list users.
 * DTO is used to interact with client.
 */
@RestController
@RequestMapping("users")
@Api(value = "User controller class responsible to handle client requests to create and list users.")
public class UserController {

	private final UserService userService;
	private final DtoConverter dtoConverter;

	@Autowired
	public UserController(UserService userService,
			DtoConverter dtoConverter) {
		this.userService = userService;
		this.dtoConverter = dtoConverter;
	}

	/**
	 * API to list all users
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "API to list all users")
	public List<UserDto> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users.stream()
				.map(user -> dtoConverter.convert(user, UserDto.class))
				.collect(Collectors.toList());
	}

	/**
	 * API to create user
	 * @param userDto
	 * @return
	 */
	@PostMapping
	@ApiOperation(value = "API to create user")
	public UserDto saveUser(@Valid @RequestBody UserDto userDto) {
		User user = userService.saveUser(dtoConverter.convert(userDto, User.class));
		return dtoConverter.convert(user, UserDto.class);
	}

}