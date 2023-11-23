package com.shah.restfulwebservices.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shah.restfulwebservices.model.Post;
import com.shah.restfulwebservices.repository.PostRepository;
import com.shah.restfulwebservices.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository repository;

	public PostServiceImpl(PostRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Post> findAll() {
		return repository.findAll();
	}

	@Override
	public Post save(Post post) {
		repository.save(post);
		return post;
	}

	@Override
	public Post findOne(int id) {
		return repository.findById(id).orElse(new Post());
	}

	@Override
	public void deletePost(int id) {
		repository.deleteById(id);
	}
}
