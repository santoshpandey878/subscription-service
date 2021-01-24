package com.gymondo.subscriptionservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymondo.subscriptionservice.core.constant.MessageConstant;
import com.gymondo.subscriptionservice.core.exception.ServiceException;
import com.gymondo.subscriptionservice.core.utils.MessageUtil;
import com.gymondo.subscriptionservice.core.utils.NullUtil;
import com.gymondo.subscriptionservice.dao.UserRepository;
import com.gymondo.subscriptionservice.entity.User;
import com.gymondo.subscriptionservice.service.UserService;

/**
 * Service class responsible to have all business logic for User
 * @author santosh
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final MessageUtil message;

	@Autowired
	public UserServiceImpl(UserRepository userRepository,
			MessageUtil message) {
		this.userRepository = userRepository;
		this.message = message;
	}

	@Transactional
	@Override
	public User saveUser(User user) {
		//validate user
		validateUser(user);
		user.setActive(true);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if(NullUtil.isNull(user)) {
			throw new ServiceException(message.get(MessageConstant.USER_NOT_EXIST, email));
		}
		return user;
	}

	/**
	 * Method to validate user
	 * @param user
	 */
	private void validateUser(User user) {
		if(NullUtil.isNotNull(getUserByEmail(user.getEmail()))) {
			throw new ServiceException(message.get(MessageConstant.USER_ALREADY_EXIST, user.getEmail()));
		}
	}

}
