package com.microcenter.web.service;

import com.microcenter.web.dto.UserDTO;

public interface UserService {
    void saveUser(UserDTO userDTO);

    boolean isNotUniqueUsername(UserDTO userDTO);

    boolean isNotUniqueEmail(UserDTO userDTO);
}
