package edu.school21.cinema.validators;

import edu.school21.cinema.annotations.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CinemaUserPasswordValidator implements ConstraintValidator<ValidPassword, String> {
    private static final String PATTERN =  "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.matches(PATTERN);
    }
}
