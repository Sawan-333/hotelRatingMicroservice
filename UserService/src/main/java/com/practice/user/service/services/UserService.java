package com.practice.user.service.services;

import java.util.List;

import com.practice.user.service.entities.User;

public interface UserService {
 
	//user operations
	
	//create user
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get user by user id
	User getUserById(String userId);
	
	//update user
	User updateUserData(User user);
	
	//delete user
	void deleteUserById(String userId);
}
