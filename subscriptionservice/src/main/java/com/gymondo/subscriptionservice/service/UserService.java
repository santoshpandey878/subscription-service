package com.gymondo.subscriptionservice.service;

import java.util.List;

import com.gymondo.subscriptionservice.entity.User;

public interface UserService {

	/**
	 * Method to create user
	 * @param user
	 * @return
	 */
	User saveUser(User user);

	/**
	 * Method to get all users
	 * @return
	 */
	List<User> getAllUsers();

	/**
	 * Method to get user by email
	 * @param email
	 * @return
	 */
	User getUserByEmail(String email);
}
