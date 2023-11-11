package com.shah.restfulwebservices.rest;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shah.restfulwebservices.exception.UserNotFoundException;
import com.shah.restfulwebservices.model.User;
import com.shah.restfulwebservices.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UsersResource {

	private UserService userService;

	public UsersResource(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/findAll")
	public List<User> findAll() {
		return userService.findAll();
	}

	@PostMapping("/save")
	public ResponseEntity<User> save(@Valid @RequestBody User user) {
		User newUser = userService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}").buildAndExpand(newUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/findOne/{id}")
	public User findOne(@PathVariable int id) {
		User user = userService.findOne(id);

		if (Objects.isNull(user)) {
			throw new UserNotFoundException("id:" + id);
		}

		return user;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
	}

}
