package org.example.springbootauth.service;

import org.example.springbootauth.enums.Authorities;
import org.example.springbootauth.exception.InvalidCredentials;
import org.example.springbootauth.exception.UnauthorizedUser;
import org.example.springbootauth.model.Guest;
import org.example.springbootauth.model.User;
import org.example.springbootauth.repository.UserRepository;

import java.util.List;

public class AuthorizationService {
    UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.addUser(user);
    }

    public User getUser(String name){
        return userRepository.getUser(name);
    }

    public List<Authorities> getAuthorities(Guest guest) {
        if (isEmpty(guest.getName()) || isEmpty(guest.getPassword())) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(guest);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser(String.format("Unknown user %s or wrong password %s", guest.getName(), guest.getPassword()));
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
