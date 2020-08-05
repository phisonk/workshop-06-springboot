package com.example.demo;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {


    //http get
    @GetMapping("/users")
    public PagingResponse getAllUser(
            @RequestParam(required = true,defaultValue = "1",name = "page") int page,
            @RequestParam(required = true,defaultValue = "10",name = "item_per_page") int itemPerPage){
        PagingResponse pagingResponse = new PagingResponse(page,itemPerPage);
        List<UsersResponse> users = new ArrayList<>();
        for (int i = 1; i < itemPerPage+1; i++) {
            users.add(new UsersResponse(i+((page-1)*itemPerPage),"User "+((page-1)*itemPerPage)));
        }
        pagingResponse.setUsersResponses(users);
        return pagingResponse;
    }

    @GetMapping("/users/{id}")
    public UsersResponse getUserById(@PathVariable int id){
        return new UsersResponse(id,"User "+id);
    }

    @PostMapping("/users")
    public UsersResponse createNewUser(@RequestBody NewUserRequest request){
        return new UsersResponse(0,request.getName() + request.getAge());
    }
}
