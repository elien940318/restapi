package com.changkeereum.restapi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.changkeereum.restapi.bean.User;

@Component
public class UserDaoService {
    
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Kenneth", new Date()));
        users.add(new User(2, "Alice", new Date()));
        users.add(new User(3, "Elien", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        if(ObjectUtils.isEmpty(user.getJoinDate())) {
            user.setJoinDate(new Date());
        }
        
        users.add(user);

        return user;
    }

    public User findOne(int id) {
        User user = users.stream()
            .filter(v->id == v.getId())
            .findFirst()
            .orElse(null);

        return user;
    }
}
