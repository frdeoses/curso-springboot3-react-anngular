package com.fran.curso.springboot.app.validation;

import com.fran.curso.springboot.app.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Objects;

@Component
public class ProductValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null,"es reequerido");

        if (Objects.isNull(product.getDescription()) || product.getDescription().isBlank()) {
            errors.rejectValue("description", null,"es requerido, por favor, vuelva a insertarlo..");
        }

        if (Objects.isNull(product.getPrice())) {
            errors.rejectValue("price", null,"no puede ser nulo!!");
        } else if (product.getPrice() < 500) {
            errors.rejectValue("price",null, "debe ser mayor o igual que 500!!");
        }
    }
}
