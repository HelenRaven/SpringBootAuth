package org.example.springbootauth.configuration;

import org.example.springbootauth.controller.AuthorizationController;
import org.example.springbootauth.repository.UserRepository;
import org.example.springbootauth.service.AuthorizationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public AuthorizationController authorizationController() {
        return new AuthorizationController(authorizationService());
    }

    @Bean
    public AuthorizationService authorizationService() {
        return new AuthorizationService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }
}
