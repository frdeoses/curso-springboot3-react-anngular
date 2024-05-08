package com.fran.curso.springboot.webapp.springbootweb.controllers;

import com.fran.curso.springboot.webapp.springbootweb.models.dto.ParamDto;
import com.fran.curso.springboot.webapp.springbootweb.models.dto.ParamMixDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/params")
public class RequestParamsController {

    @GetMapping("/foo")
    public ParamDto foo(@RequestParam(required = false, defaultValue = "Hola que tal") String message) {

        ParamDto param = new ParamDto();

        param.setMessage(message);

        return param;
    }

    @GetMapping("/bar")
    public ParamMixDto bar(@RequestParam String message, @RequestParam Integer code) {

        ParamMixDto params = new ParamMixDto();

        params.setMessage(message);
        params.setCode(code);

        return params;
    }

    @GetMapping("/request")
    public ParamMixDto request(HttpServletRequest request) {

        Integer code = 0;

        try {
            code = Integer.parseInt(request.getParameter("code"));
        } catch (NumberFormatException e) {

        }

        ParamMixDto params = new ParamMixDto();

        params.setMessage(request.getParameter("message"));
        params.setCode(code);

        return params;
    }

//    @GetMapping("/list")
//    public List<User> list() {
//
//        User user1 = new User("Fran", "Garcia");
//        User user2 = new User("Pepe", "Ramiro");
//        User user3 = new User("John", "Doe");
//
//        List<User> userList = Arrays.asList(user1, user2, user3);
////        List<User> userList = new ArrayList<>();
////        userList.add(user1);
////        userList.add(user2);
////        userList.add(user3);
//
//        return userList;
//    }
//
//    @GetMapping("/details-map")
//    public Map<String, Object> detailsMap() {
//
//        User user = new User("Fran", "Garcia");
//
//        Map<String, Object> body = new HashMap<>();
//
//        body.put("title", "Hola mundo");
//        body.put("user", user);
//
//        return body;
//    }

}
