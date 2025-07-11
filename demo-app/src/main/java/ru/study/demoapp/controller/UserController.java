package ru.study.demoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.study.demoapp.service.UserService;

import java.util.Map;

@RestController
public class UserController {

/*    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/info")
    public String userInfo() {
        return "Hello, user!";
    }

    @GetMapping("/admin/info")
    public String adminInfo() {
        return "Hello, admin!";
    }

    @PostMapping("public/add-user")
    public String addUser(@RequestBody Map<String, String> user) {
        return userService.addInMemoryUser(user);
    }*/
}
