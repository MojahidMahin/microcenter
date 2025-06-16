package com.microcenter.web.service;

import com.microcenter.web.domain.User;
import com.microcenter.web.dto.LoginDTO;
import com.microcenter.web.dto.UserDTO;
import com.microcenter.web.exceptions.UserNotFoundException;
import com.microcenter.web.repository.UserRepository;
import com.microcenter.web.servlet.SignupServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(SignupServlet.class);

    @Override
    public void saveUser(UserDTO userDTO) {
        LOGGER.info("Saving user: {}", userDTO);
        // Validate userDTO fields here if necessary
        if (userDTO.getUsername() == null || userDTO.getPassword() == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }

        String encryptedPass = encryptPassword(userDTO.getPassword());
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(encryptedPass);
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        // Save the user using the repository
        userRepository.save(user);
    }

    @Override
    public boolean isNotUniqueUsername(UserDTO userDTO) {
        return userRepository.findByUsername(userDTO.getUsername()).isPresent();
    }

    @Override
    public boolean isNotUniqueEmail(UserDTO userDTO) {
        return userRepository.findByEmail(userDTO.getEmail()).isPresent();
    }

    @Override
    public User verifyUser(LoginDTO loginDTO) {
        var user = userRepository.findByUsername(loginDTO.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found with provided username"));
        LOGGER.info("UserService Verifying user with username: {}", loginDTO.getUsername());
        var encryptedPass = encryptPassword(loginDTO.getPassword());
        if (user.getPassword().equals(encryptedPass)) {
            LOGGER.info("UserService verified User successfully: {}", user);
            return user;
        } else {
            LOGGER.warn("Incorrect password for user: {}", loginDTO.getUsername());
            throw new UserNotFoundException("Incorrect username or password");
        }
    }

    private String encryptPassword(String password) {
        try {
            var digest = MessageDigest.getInstance("SHA-256");
            var hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Unable to encrypt password", e);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            // Ensure each byte is represented by two hex digits by padding with leading zero if needed
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
