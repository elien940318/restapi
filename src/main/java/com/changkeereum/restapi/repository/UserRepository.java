package com.changkeereum.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.changkeereum.restapi.bean.User;

public interface UserRepository extends JpaRepository<User, Integer> {}
