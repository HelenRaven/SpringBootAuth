package org.example.springbootauth.repository;

import org.example.springbootauth.enums.Authorities;
import org.example.springbootauth.model.Guest;
import org.example.springbootauth.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private final Map<String, User> users = new ConcurrentHashMap<>();

    public User addUser(User user){
        users.put(user.getName(), user);
        return user;
    }

    public User getUser(String name){
        return users.get(name);
    }

    public List<Authorities> getUserAuthorities(Guest guest) {
        User currentUser = users.get(guest.getName());
        if (currentUser == null || !currentUser.getPassword().equals(guest.getPassword()))
            return new ArrayList<>();
        return currentUser.getAuthorities();
    }
}
