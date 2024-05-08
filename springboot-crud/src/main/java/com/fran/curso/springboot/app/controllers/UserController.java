package com.fran.curso.springboot.app.controllers;

import com.fran.curso.springboot.app.entities.User;
import com.fran.curso.springboot.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(originPatterns = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

//    @Autowired
//    private ProductValidation validation;

    @GetMapping
    public List<User> list() {

        return service.findAll();

    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> view(@PathVariable Long id) {
//
//        Optional<Product> optionalProduct = service.findById(id);
//
//        if (optionalProduct.isPresent()) {
//            return ResponseEntity.ok(optionalProduct.orElseThrow());
//        }
//
//        return ResponseEntity.notFound().build();
//
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid User user, BindingResult result) { // BindingResult Tiene que estar al lado del objeeto a validar

//        validation.validate(product, result);

        if (result.hasFieldErrors()) {
            return validation(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));

    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user, BindingResult result) { // BindingResult Tiene que estar al lado del objeeto a validar

        user.setAdmin(false);

        return create(user, result);

    }


//    @PutMapping("/{id}")
//    public ResponseEntity<?> update(@RequestBody @Valid Product product, BindingResult result, @PathVariable Long id) {
//
////        validation.validate(product, result);
//
//        if (result.hasFieldErrors()) {
//            return validation(result);
//        }
//
//        Optional<Product> optionalProduct = service.update(id, product);
//        if (optionalProduct.isPresent()) {
//            return ResponseEntity.ok(optionalProduct.orElseThrow());
//        }
//
//        return ResponseEntity.notFound().build();
//
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id) {
//
//
//        Optional<Product> optionalProduct = service.delete(id);
//
//        if (optionalProduct.isPresent()) {
//            return ResponseEntity.ok(optionalProduct.orElseThrow());
//        }
//
//        return ResponseEntity.notFound().build();
//
//    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(errors);

    }

}
