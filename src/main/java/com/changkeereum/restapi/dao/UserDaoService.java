package com.changkeereum.restapi.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.changkeereum.restapi.bean.User;

@Component
public class UserDaoService {
    
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "Kenneth", new Date(), "test1", "111111-1111111"));
        users.add(new User(2, "Alice", new Date(), "test2", "222222-2222222"));
        users.add(new User(3, "Elien", new Date(), "test3", "333333-3333333"));
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

        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();

            if(user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()) {
            User user = iterator.next();

            if(user.getId() == id) {
                iterator.remove();
                return user;
            }
        }

        return null;
    }

    
}
