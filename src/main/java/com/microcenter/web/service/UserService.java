package com.microcenter.web.service;

import com.microcenter.web.domain.User;
import com.microcenter.web.dto.LoginDTO;
import com.microcenter.web.dto.UserDTO;

public interface UserService {
    void saveUser(UserDTO userDTO);

    boolean isNotUniqueUsername(UserDTO userDTO);

    boolean isNotUniqueEmail(UserDTO userDTO);

    User verifyUser(LoginDTO loginDTO);
}
