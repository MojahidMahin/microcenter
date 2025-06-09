package com.microcenter.web.servlet;

import com.microcenter.web.dto.UserDTO;
import com.microcenter.web.repository.UserRepository;
import com.microcenter.web.repository.UserRepositoryImpl;
import com.microcenter.web.service.UserService;
import com.microcenter.web.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignupServlet.class);

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving signup page");

        // Forward the request to the signup JSP page
        req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Processing signup form submission");

        UserDTO userDTO = copyParametersTo(req);
        if (isValid(userDTO)) {
            LOGGER.info("User signup data is valid: {}", userDTO);
            // Here you would typically save the user to a database
            userService.saveUser(userDTO);
            // Redirect to home page after successful signup
            resp.sendRedirect("/home");
        } else {
            LOGGER.warn("Invalid signup data: {}", userDTO);
            req.setAttribute("errorMessage", "Invalid signup data. Please try again.");
            req.getRequestDispatcher("/WEB-INF/views/signup.jsp").forward(req, resp);
        }
    }

    private UserDTO copyParametersTo(HttpServletRequest req) {
        var userDTO = new UserDTO();
        userDTO.setUsername(req.getParameter("username"));
        userDTO.setPassword(req.getParameter("password"));
        userDTO.setPasswordConfirmed(req.getParameter("passwordConfirmed"));
        userDTO.setEmail(req.getParameter("email"));
        userDTO.setFirstName(req.getParameter("firstName"));
        userDTO.setLastName(req.getParameter("lastName"));
        LOGGER.info("Copied parameters to UserDTO: {}", userDTO);
        return userDTO;
    }

    private boolean isValid(UserDTO userDTO) {
        return true;
    }
}
