package org.example.springbootauth.controller;

import jakarta.validation.Valid;
import org.example.springbootauth.enums.Authorities;
import org.example.springbootauth.model.Guest;
import org.example.springbootauth.model.User;
import org.example.springbootauth.service.AuthorizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.service = authorizationService;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Valid Guest guest) {
        return service.getAuthorities(guest);
    }

    @PostMapping("/signIn")
    public User addUser(@RequestBody @Valid User user) {
        return service.addUser(user);
    }

    @GetMapping("/users/{name}")
    public User getUserByName(@PathVariable("name") String name) {
        return service.getUser(name);
    }
}
