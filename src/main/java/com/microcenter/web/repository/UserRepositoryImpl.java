package com.microcenter.web.repository;

import com.microcenter.web.domain.User;
import com.microcenter.web.dto.UserDTO;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class UserRepositoryImpl implements UserRepository{
    private static final Set<User> USERS = new CopyOnWriteArraySet<>();
    @Override
    public void save(User user) {
        USERS.add(user);
    }
}
