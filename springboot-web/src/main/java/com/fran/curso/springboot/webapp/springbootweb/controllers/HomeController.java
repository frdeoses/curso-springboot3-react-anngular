package com.fran.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"", "/", "/home"})
    public String home() {

        // FIXME: Cambia la ruta a /list
//        return "redirect:/list";

        // FIXME: No cambia la ruta
        return "forward:/details";
    }


}
