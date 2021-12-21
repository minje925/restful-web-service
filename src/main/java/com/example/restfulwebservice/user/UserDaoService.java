package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int usersCount = 3;

    static {
        users.add(new User(1, "minje", new Date()));
        users.add(new User(2, "gildong", new Date()));
        users.add(new User(3, "won", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);

        return user;
    }

    public User findOne(int id) {
        for(User u: users) {
            if(u.getId() == id)
                return u;
        }
        return null;
    }

    public User deleteById(int id) {
        // 순차형 데이터를 사용하기 위한 이터레이터 형태
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
