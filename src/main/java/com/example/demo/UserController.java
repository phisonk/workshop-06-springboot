package com.example.demo;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {


    public List<UsersResponse> getAllUser() {
        return getAllUser(2,10 );
    }

    //http get
    @GetMapping("/users")
    public List<UsersResponse> getAllUser(@RequestParam(required = true,defaultValue = "2",name = "page") int page,@RequestParam(required = true,defaultValue = "10",name = "item_per_page") int item_per_page){
        List<UsersResponse> users = new ArrayList<>();
        for (int i = 1; i < item_per_page+1; i++) {
            users.add(new UsersResponse(i+((page-1)*item_per_page),"User "+((page-1)*item_per_page)));
        }
        return users;
    }

    @GetMapping("/users/{id}")
    public UsersResponse getUserById(@PathVariable int id){
        return new UsersResponse(id,"User "+id);
    }
}
