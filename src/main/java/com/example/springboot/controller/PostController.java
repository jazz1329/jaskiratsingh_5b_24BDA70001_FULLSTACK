package com.example.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.dto.PostRequest;
import com.example.springboot.dto.PostResponse;
import com.example.springboot.response.ApiResponse;
import com.example.springboot.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<PostResponse>> create(@Valid @RequestBody PostRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ApiResponse.success(HttpStatus.CREATED.value(), "Post created successfully", postService.create(request)));
	}

	@GetMapping
	public ApiResponse<List<PostResponse>> findAll() {
		return ApiResponse.success(HttpStatus.OK.value(), "Posts retrieved successfully", postService.findAll());
	}

	@GetMapping("/{id}")
	public ApiResponse<PostResponse> findById(@PathVariable Long id) {
		return ApiResponse.success(HttpStatus.OK.value(), "Post retrieved successfully", postService.findById(id));
	}

	@PutMapping("/{id}")
	public ApiResponse<PostResponse> update(@PathVariable Long id, @Valid @RequestBody PostRequest request) {
		return ApiResponse.success(HttpStatus.OK.value(), "Post updated successfully", postService.update(id, request));
	}

	@DeleteMapping("/{id}")
	public ApiResponse<Void> delete(@PathVariable Long id) {
		postService.delete(id);
		return ApiResponse.success(HttpStatus.OK.value(), "Post deleted successfully", null);
	}
}