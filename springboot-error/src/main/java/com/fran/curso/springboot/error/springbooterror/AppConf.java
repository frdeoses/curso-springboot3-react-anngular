package com.fran.curso.springboot.error.springbooterror;

import com.fran.curso.springboot.error.springbooterror.models.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConf {

    @Bean
//    @Primary
    List<User> listUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John", "Garcia"));
        users.add(new User(2L, "alex", "Bonill"));
        users.add(new User(3L, "Jorge", "Perez"));
        users.add(new User(4L, "Maria", "Mende"));
        users.add(new User(5L, "Diego", "Gonza"));
        return users;
    }


}
