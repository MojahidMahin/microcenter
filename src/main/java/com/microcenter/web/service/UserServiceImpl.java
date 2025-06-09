package com.microcenter.web.service;

import com.microcenter.web.domain.User;
import com.microcenter.web.dto.UserDTO;
import com.microcenter.web.repository.UserRepository;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
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

    private String encryptPassword(String password) {
//        we will implement the process later
        return password;
    }
}
