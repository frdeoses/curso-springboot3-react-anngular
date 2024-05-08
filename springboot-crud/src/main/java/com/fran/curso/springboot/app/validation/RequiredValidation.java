package com.fran.curso.springboot.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class RequiredValidation implements ConstraintValidator<IsRequired, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

//        return (Objects.nonNull(s) && !s.isEmpty() && !s.isBlank());
        return StringUtils.hasText(s);
    }
}
