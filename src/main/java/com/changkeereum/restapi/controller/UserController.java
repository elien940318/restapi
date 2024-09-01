package com.changkeereum.restapi.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.changkeereum.restapi.bean.User;
import com.changkeereum.restapi.dao.UserDaoService;
import com.changkeereum.restapi.exception.UserNotFoundException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserController {

    @Autowired UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }
    
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable("id") int id) {
        User user = service.findOne(id);

        if(ObjectUtils.isEmpty(user)) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }
    
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

        return ResponseEntity.created(location).build();
    }
    
}
