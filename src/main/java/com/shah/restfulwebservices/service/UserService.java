package com.shah.restfulwebservices.service;

import java.util.List;

import com.shah.restfulwebservices.model.User;

public interface UserService {

	List<User> findAll();
	
	User save(User user);
	
	User findOne(int id);
	
	void deleteUser(int id);
}
