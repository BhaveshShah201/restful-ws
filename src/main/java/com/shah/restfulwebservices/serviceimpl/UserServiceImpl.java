package com.shah.restfulwebservices.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shah.restfulwebservices.model.User;
import com.shah.restfulwebservices.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static List<User> userList = new ArrayList<>();
	private static int id = 0;

	static {
		userList.add(new User(++id, "Bhavesh", LocalDate.parse("1991-05-05")));
		userList.add(new User(++id, "Hetal", LocalDate.parse("1995-11-14")));
	}
	
	@Override
	public List<User> findAll() {
		return userList;
	}

	@Override
	public User save(User user) {
		user.setId(++id);
		userList.add(user);
		return user;
	}

	@Override
	public User findOne(int id) {
		return userList.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
	}

	@Override
	public void deleteUser(int id) {
		userList.removeIf(user -> user.getId() == id);
	}
}
