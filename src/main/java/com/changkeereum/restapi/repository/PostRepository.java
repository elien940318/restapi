package com.changkeereum.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.changkeereum.restapi.bean.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {}
