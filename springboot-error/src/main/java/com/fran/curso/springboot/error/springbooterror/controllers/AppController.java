package com.fran.curso.springboot.error.springbooterror.controllers;

import com.fran.curso.springboot.error.springbooterror.exceptions.UserNotFoundException;
import com.fran.curso.springboot.error.springbooterror.models.domain.User;
import com.fran.curso.springboot.error.springbooterror.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public String index() {
//        int value = 100/0;
        int value = Integer.parseInt("10x");
        System.out.println(value);
        return "ok 200";
    }

    @GetMapping("/show/{id}")
    public User findUser(@PathVariable(name = "id") Long id) {
//    public ResponseEntity<?> findUser(@PathVariable(name = "id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Error: Usuario no existe"));

//        Optional<User> optionalUser = userService.findById(id);
//
//        if(optionalUser.isEmpty())
//            return ResponseEntity.notFound().build();

//       return ResponseEntity.ok(optionalUser.get());

    }
}
