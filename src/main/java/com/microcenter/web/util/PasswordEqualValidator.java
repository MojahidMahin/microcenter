package com.microcenter.web.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class PasswordEqualValidator implements ConstraintValidator<PasswordEqual, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;


    @Override
    public void initialize(PasswordEqual constraint) {
//        ConstraintValidator.super.initialize(constraintAnnotation);
        firstFieldName = constraint.first();
        secondFieldName = constraint.second();
        message = constraint.message();
    }


    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean valid = true;
        try {
//            final Object firstObj = value.getClass().getDeclaredField(firstFieldName);
//            final Object secondObj = value.getClass().getDeclaredField(secondFieldName);

//            Using reflection to get the values of the fields
            final Object firstObj = getValue(value, firstFieldName);
            final Object secondObj = getValue(value, secondFieldName);

            valid = firstObj.equals(secondObj);
        } catch (final Exception e) {
//            ignore the exception, as it will be handled by the validation framework
        }

        if (!valid) {
//            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }

    private Object getValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        return field.get(object);
    }
}
