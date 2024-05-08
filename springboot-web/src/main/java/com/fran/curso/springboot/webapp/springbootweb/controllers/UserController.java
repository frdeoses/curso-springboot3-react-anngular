package com.fran.curso.springboot.webapp.springbootweb.controllers;

import com.fran.curso.springboot.webapp.springbootweb.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model) {
        User user = new User("Fran", "Garcia");

        model.addAttribute("title", "Hola mundo");

        model.addAttribute("user", user);

        return "Details";
    }

    @GetMapping("/list")
    public String list(ModelMap model) {


        List<User> users = new ArrayList<>();
        model.addAttribute("title", "Listado de usuarios:");
//        model.addAttribute("users", users);
//        userList.add(user1);
//        userList.add(user2);
//        userList.add(user3);

        return "list";
    }

    @ModelAttribute("users")
    public List<User> usersModel() {

        User user1 = new User("Fran", "Garcia");
        User user2 = new User("Pepe", "Ramiro", "pepe@asdas.com");
        User user3 = new User("John", "Doe", "jh@asdsadasd.com");
        return Arrays.asList(user1, user2, user3);
        
    }

}
