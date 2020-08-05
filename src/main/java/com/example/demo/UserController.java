package com.example.demo;

import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
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
            @RequestParam(required = true,defaultValue = "1",name = "page") int page,
            @RequestParam(required = true,defaultValue = "10",name = "item_per_page") int itemPerPage){
        PagingResponse pagingResponse = new PagingResponse(page,itemPerPage);
        List<UsersResponse> usersResponseList = new ArrayList<>();

        Iterable<User> users =  userRepository.findAll();
        for(User user: users){
            usersResponseList.add(new UsersResponse(user.getId(),user.getName()));
        }
//        for (int i = 1; i < itemPerPage+1; i++) {
//            usersResponseList.add(new UsersResponse(i+((page-1)*itemPerPage),"User "+((page-1)*itemPerPage)));
//        }
        pagingResponse.setUsersResponses(usersResponseList);
        return pagingResponse;
    }

    @GetMapping("/users/{id}")
    public UsersResponse getUserById(@PathVariable int id){
        Optional<User> user =  userRepository.findById(id);
        return new UsersResponse(user.get().getId(),user.get().getName());
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
