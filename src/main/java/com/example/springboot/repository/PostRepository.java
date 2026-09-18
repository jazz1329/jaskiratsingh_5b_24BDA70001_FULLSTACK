package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}