package com.fran.curso.springboot.calendar.interceptor.springboothorario.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AppController {

    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        map.put("title", "Bienvenidos al sistema de atencion!");
        map.put("time", new Date());
        map.put("message", request.getAttribute("message"));

        return ResponseEntity.ok(map);
    }
}
