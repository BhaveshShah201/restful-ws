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

import com.shah.restfulwebservices.exception.PostNotFoundException;
import com.shah.restfulwebservices.exception.UserNotFoundException;
import com.shah.restfulwebservices.model.Post;
import com.shah.restfulwebservices.model.User;
import com.shah.restfulwebservices.service.PostService;
import com.shah.restfulwebservices.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostResource {

	private PostService postService;
	private UserService userService;

	public PostResource(PostService postService, UserService userService) {
		this.postService = postService;
		this.userService = userService;
	}

	@GetMapping("/findAll")
	public List<Post> findAll() {
		return postService.findAll();
	}

	@PostMapping("/save")
	public ResponseEntity<User> save(@Valid @RequestBody Post post) {
		Post newPost = postService.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}").buildAndExpand(newPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/findOne/{id}")
	public Post findOne(@PathVariable int id) {
		Post post = postService.findOne(id);

		if (Objects.isNull(post)) {
			throw new PostNotFoundException("id:" + id);
		}

		return post;
	}

	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
		User user = userService.findOne(id);
		if (Objects.isNull(user)) {
			throw new UserNotFoundException("id:" + id);
		}
		post.setUser(user);
		Post newPost = postService.save(post);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}").buildAndExpand(newPost.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		postService.deletePost(id);
	}
}
