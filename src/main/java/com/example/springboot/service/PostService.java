package com.example.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springboot.dto.PostRequest;
import com.example.springboot.dto.PostResponse;
import com.example.springboot.entity.Post;
import com.example.springboot.exception.PostNotFoundException;
import com.example.springboot.repository.PostRepository;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostResponse create(PostRequest request) {
		return PostResponse.from(postRepository.save(new Post(request.getTitle(), request.getContent())));
	}

	public List<PostResponse> findAll() {
		return postRepository.findAll().stream().map(PostResponse::from).toList();
	}

	public PostResponse findById(Long id) {
		return PostResponse.from(findPost(id));
	}

	public PostResponse update(Long id, PostRequest request) {
		Post post = findPost(id);
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		return PostResponse.from(postRepository.save(post));
	}

	public void delete(Long id) {
		postRepository.delete(findPost(id));
	}

	private Post findPost(Long id) {
		return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
	}
}