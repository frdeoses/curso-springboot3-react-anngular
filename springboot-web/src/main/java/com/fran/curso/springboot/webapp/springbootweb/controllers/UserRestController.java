package com.fran.curso.springboot.webapp.springbootweb.controllers;

import com.fran.curso.springboot.webapp.springbootweb.models.User;
import com.fran.curso.springboot.webapp.springbootweb.models.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping("/details")
    public UserDto details() {

        User user = new User("Fran", "Garcia");

        UserDto userDto = new UserDto();

        userDto.setUser(user);
        userDto.setTitle("Hola mundo");

        return userDto;
    }

    @GetMapping("/list")
    public List<User> list() {

        User user1 = new User("Fran", "Garcia");
        User user2 = new User("Pepe", "Ramiro");
        User user3 = new User("John", "Doe");

        List<User> userList = Arrays.asList(user1, user2, user3);
//        List<User> userList = new ArrayList<>();
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);

        return userList;
    }

    @GetMapping("/details-map")
    public Map<String, Object> detailsMap() {

        User user = new User("Fran", "Garcia");

        Map<String, Object> body = new HashMap<>();

        body.put("title", "Hola mundo");
        body.put("user", user);

        return body;
    }

}
