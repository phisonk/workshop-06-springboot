package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    //http get
    @GetMapping("/users")
    public List<UsersResponse> getAllUser(){
        List<UsersResponse> users = new ArrayList<>();
        users.add(new UsersResponse(1,"User 1"));
        users.add(new UsersResponse(2,"User 2"));
        users.add(new UsersResponse(3,"User3"));
        return users;
    }

    @GetMapping("/users/{id}")
    public UsersResponse getUserById(@PathVariable int id){
        return new UsersResponse(id,"User "+id);
    }
}
