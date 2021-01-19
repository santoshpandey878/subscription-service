package com.gymondo.subscriptionservice.dao;

import com.gymondo.subscriptionservice.entity.User;

/**
 * Repository to handle all database operation for user
 */
public interface UserRepository extends BaseRepository<User, Long>{

	/**
	 * Method to get user by email
	 * @param email
	 * @return
	 */
	User findByEmail(String email);
}