package com.shah.restfulwebservices.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shah.restfulwebservices.model.User;
import com.shah.restfulwebservices.repository.UserRepository;
import com.shah.restfulwebservices.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository repository;

	public UserServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User save(User user) {
		repository.save(user);
		return user;
	}

	@Override
	public User findOne(int id) {
		return repository.findById(id).orElse(new User());
	}

	@Override
	public void deleteUser(int id) {
		repository.deleteById(id);
	}
}
