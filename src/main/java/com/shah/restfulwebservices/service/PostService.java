package com.shah.restfulwebservices.service;

import java.util.List;

import com.shah.restfulwebservices.model.Post;

public interface PostService {

	List<Post> findAll();
	
	Post save(Post user);
	
	Post findOne(int id);
	
	void deletePost(int id);
}
