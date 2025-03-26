package org.example.springbootauth.model;

import org.example.springbootauth.enums.Authorities;

import java.util.ArrayList;
import java.util.List;

public class User extends Guest{
    private List<Authorities> authorities = new ArrayList<>();

    public User() {
    }

    public User(String name, String password, List<Authorities> authorities) {
        super(name, password);
        this.authorities = authorities;
    }

    public List<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authorities> authorities) {
        this.authorities = authorities;
    }
}
