package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //http get
    @GetMapping("/users")
    public String getAllUser(){
        return "Get All User";
    }
}
