package com.microcenter.web.servlet;

import com.microcenter.web.domain.User;
import com.microcenter.web.dto.LoginDTO;
import com.microcenter.web.dto.UserDTO;
import com.microcenter.web.exceptions.UserNotFoundException;
import com.microcenter.web.repository.UserRepositoryImpl;
import com.microcenter.web.service.UserService;
import com.microcenter.web.service.UserServiceImpl;
import com.microcenter.web.util.SecurityContext;
import com.microcenter.web.util.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

    private UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Serving login page");
//        this parameter is set when the user logs out, for clarity see logout servlet send redirect line.
        String logout = req.getParameter("logout");

        if (logout != null && Boolean.parseBoolean(logout)) {
            req.setAttribute("message", "You have been logged out successfully");
        }
        // Forward the request to the login JSP page
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var loginDTO = new LoginDTO(req.getParameter("username"), req.getParameter("password"));

        LOGGER.info("Processing login form submission: {}", loginDTO);

        var errors = ValidationUtil.getInstance().validate(loginDTO);

        if (!errors.isEmpty()) {
            LOGGER.warn("Validation errors found in login: {}", errors);
            LOGGER.info("LoginDTO is not valid: {}", loginDTO);
            reqSet(req, resp, loginDTO, errors);
            return;
        }

        try {
            login(loginDTO, req);
            resp.sendRedirect("/home");

        } catch (UserNotFoundException e) {
            LOGGER.error("Incorrect username or password", e);
            errors.put("username", "Incorrect username or password");
            errors.put("password", "Incorrect username or password");
            reqSet(req, resp, loginDTO, errors);
        }

    }

    private void login(LoginDTO loginDTO, HttpServletRequest req) throws UserNotFoundException{
        User user = userService.verifyUser(loginDTO);

//        new method to login
        SecurityContext.login(req, user);

//        old method to login
////        get the old session and invalidate it
//        HttpSession oldSession = req.getSession(false);
//        if (oldSession != null) {
//            oldSession.invalidate();
//        }
//
////        put the user in the session
//        HttpSession newSession = req.getSession(true);
//        newSession.setAttribute("user", user);
        LOGGER.info("User logged in successfully: {}", user.getUsername());
    }


    private void reqSet(HttpServletRequest req, HttpServletResponse resp, LoginDTO loginDTO, Map<String, String> errors) throws ServletException, IOException {
        req.setAttribute("loginDTO", loginDTO);
        req.setAttribute("errors", errors);
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }
}
