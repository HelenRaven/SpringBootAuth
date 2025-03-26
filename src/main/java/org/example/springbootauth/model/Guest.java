package org.example.springbootauth.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Guest {
    @NotBlank
    @Size(min = 1, max = 99)
    private String name;
    @NotBlank
    @Size(min = 3, max = 99)
    private String password;

    public Guest() {
    }

    public Guest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public @NotBlank @Size(min = 1, max = 99) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(min = 1, max = 99) String name) {
        this.name = name;
    }

    public @NotBlank @Size(min = 3, max = 99) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 3, max = 99) String password) {
        this.password = password;
    }
}
