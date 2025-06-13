package com.microcenter.web.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidationUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationUtil.class);
    private static final ValidationUtil INSTANCE = new ValidationUtil();
    private final Validator validator;
    private ValidationUtil() {
        try {
            var validatorFactory = Validation.buildDefaultValidatorFactory();
            this.validator = validatorFactory.getValidator();
        } catch (Exception e) {
            LOGGER.error("Failed to create ValidatorFactory", e);
            throw new RuntimeException("Validation failed", e);
        }
    }

    public static ValidationUtil getInstance() {
        return INSTANCE;
    }

    public <T> Map<String, String> validate(T object) {
        var violations = validator.validate(object);

//        style 1 without stream
//        Map<String, String> errors = new java.util.HashMap<>();
//
//        for (var violation : violations) {
//            String path = violation.getPropertyPath().toString();
//            String message = violation.getMessage();
//            if (errors.containsKey(path)) {
//                errors.put(path, errors.get(path) + " <br/> " + message);
//            } else {
//                errors.put(path, message);
//            }
//        }
//
//        return errors;

        // style 2 with stream
        return violations.stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage,
                        (existing, replacement) -> existing + " <br/> " + replacement));
    }
}
