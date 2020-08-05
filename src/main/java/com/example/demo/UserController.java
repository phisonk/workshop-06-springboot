package com.example.demo;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //http get
    @GetMapping("/users")
    public PagingResponse getAllUser(
            @RequestParam(required = true,defaultValue = "0",name = "page") int page,
            @RequestParam(required = true,defaultValue = "10",name = "item_per_page") int itemPerPage){
        PagingResponse pagingResponse = new PagingResponse(page,itemPerPage);
        List<UsersResponse> usersResponseList = new ArrayList<>();


        Pageable pageable = PageRequest.of(page, itemPerPage);
        Page<User> usersPagination = userRepository.findAll(pageable);
        Iterable<User> users = usersPagination.getContent();
        for(User user: usersPagination.getContent()){
            usersResponseList.add(new UsersResponse(user.getId(),user.getName()));
        }
        pagingResponse.setUsersResponses(usersResponseList);
        return pagingResponse;
    }

    @GetMapping("/users/{id}")
    public UsersResponse getUserById(@PathVariable int id){
        Optional<User> user =  userRepository.findById(id);
        return new UsersResponse(user.get().getId(),user.get().getName(),user.get().getAge());
    }

    @PostMapping(path = "/users")
    public UsersResponse createNewUser(@RequestBody NewUserRequest request){
        // Validate input
        // Create new user into database =>
        User user = new User();
        user.setName(request.getName());
        user.setAge(request.getAge());
        user = userRepository.save(user);
        return new UsersResponse(user.getId(), request.getName() + " " + request.getAge());
    }

    @PostMapping(path = "/users1")
    public String createNewUserWithFormData(NewUserRequest request){
        return request.getName() + request.getAge();
    }
}
