package com.app.spring.security.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/method")
//@PreAuthorize("denyAll()")
public class TestAuthController {

    //    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/get")
    public String helloGet() {
        return "Hola mundo - GET";
    }

    //    @PreAuthorize("hasAuthority('CREATE') or hasAuthority('READ')")
    @PostMapping("/post")
    public String helloPost() {
        return "Hola mundo - Post";
    }

    @PutMapping("/put")
    public String helloPut() {
        return "Hola mundo - Put";
    }

    @DeleteMapping("/delete")
    public String helloDelete() {
        return "Hola mundo - Delete";
    }

    //    @PreAuthorize("hasAuthority('REFACTOR')")
    @PatchMapping("/patch")
    public String helloPatch() {
        return "Hola mundo - Patch";
    }
}
