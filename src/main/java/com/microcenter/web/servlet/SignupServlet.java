package com.microcenter.web.servlet;

import com.microcenter.web.dto.UserDTO;
import com.microcenter.web.repository.UserRepositoryImpl;
import com.microcenter.web.service.UserService;
import com.microcenter.web.service.UserServiceImpl;
import com.microcenter.web.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignupServlet.class);

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving signup page from SignupServlet");

        // Forward the request to the signup JSP page
        req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Processing signup form submission from SignupServlet's doPost method");

        UserDTO userDTO = copyParametersTo(req);

//        Map<String, String> errors = validate(userDTO);

//        generate validation errors using ValidationUtil class
        Map<String, String> errors = ValidationUtil.getInstance().validate(userDTO);



        if (!errors.isEmpty()) {
            LOGGER.warn("Validation errors found: {}", errors);
            LOGGER.info("UserDTO is not valid: {}", userDTO);
            reqSet(req, resp, userDTO, errors);
        } else if (userService.isNotUniqueUsername(userDTO)) {
            LOGGER.info("Username is not unique: {}", userDTO.getUsername());
            errors.put("Username", "Username is not unique");
            reqSet(req, resp, userDTO, errors);
        } else if (userService.isNotUniqueEmail(userDTO)) {
            LOGGER.info("Email is not unique: {}", userDTO.getEmail());
            errors.put("Email", "Email is not unique");
            reqSet(req, resp, userDTO, errors);
        } else {
            LOGGER.info("No validation errors found for UserDTO: {}", userDTO);
            userService.saveUser(userDTO);
            resp.sendRedirect("/home");
        }


//        if (isValid(userDTO)) {
//            LOGGER.info("User signup data is valid: {}", userDTO);
//            // Here you would typically save the user to a database
//            userService.saveUser(userDTO);
//            // Redirect to home page after successful signup
//            resp.sendRedirect("/home");
//        } else {
//            LOGGER.warn("Invalid signup data: {}", userDTO);
//            req.setAttribute("errorMessage", "Invalid signup data. Please try again.");
//            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
//        }

    }

    private void reqSet(HttpServletRequest req, HttpServletResponse resp, UserDTO userDTO, Map<String, String> errors) throws ServletException, IOException {
        req.setAttribute("userDTO", userDTO);
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
    }

    private UserDTO copyParametersTo(HttpServletRequest req) {
        var userDTO = new UserDTO();
        userDTO.setUsername(req.getParameter("username"));
        userDTO.setPassword(req.getParameter("password"));
        userDTO.setPasswordConfirmed(req.getParameter("passwordConfirm"));
        userDTO.setEmail(req.getParameter("email"));
        userDTO.setFirstName(req.getParameter("firstName"));
        userDTO.setLastName(req.getParameter("lastName"));
        LOGGER.info("Copied parameters to UserDTO: {}", userDTO);
        return userDTO;
    }

//    private boolean isValid(UserDTO userDTO) {
//
//        try {
//            var validatorFactory = Validation.buildDefaultValidatorFactory();
//            var validator = validatorFactory.getValidator();
//
//            Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
//            if (!violations.isEmpty()) {
//                for (ConstraintViolation<UserDTO> violation : violations) {
//                    LOGGER.warn("User Validation error: {} - {}", violation.getPropertyPath(), violation.getMessage());
//                }
//                return false;
//            }
//            LOGGER.info("UserDTO is valid");
//            return true;
//        } catch (ValidationException e) {
//            LOGGER.error("Failed to create ValidatorFactory", e);
//            throw new RuntimeException("Validation failed", e);
//        }
//    }

//    private Map<String, String> validate(UserDTO userDTO) {
//        try {
//            var validatorFactory = Validation.buildDefaultValidatorFactory();
//            var validator = validatorFactory.getValidator();
//
//            Set<ConstraintViolation<UserDTO>> violations = validator.validate(userDTO);
//
//            Map<String, String> errors = new HashMap<>();
//
//            for (ConstraintViolation<UserDTO> violation : violations) {
//                String path = violation.getPropertyPath().toString();
//                String message = violation.getMessage();
//                String errorField = errors.get(path);
//                if (errors.containsKey(path)) {
//                    errors.put(path, errorField + " <br/> " + message);
//                } else {
//                    errors.put(path, message);
//                }
//            }
//
//            return errors;
//        } catch (ValidationException e) {
//            LOGGER.error("Failed to create ValidatorFactory", e);
//            throw new RuntimeException("Validation failed", e);
//        }
//    }
}
