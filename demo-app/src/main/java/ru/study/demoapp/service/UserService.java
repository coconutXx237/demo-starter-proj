package ru.study.demoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;*/
import org.springframework.stereotype.Component;

import java.util.Map;

//@Component
public class UserService {

/*    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String addInMemoryUser(Map<String, String> user) {
        String username = user.get("username");
        String password = user.get("password");

        UserDetails newUser = org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles("USER")
                .build();
        inMemoryUserDetailsManager.createUser(newUser);
        return "User " + username + " with password " + password + " added successfully";
    }*/
}
