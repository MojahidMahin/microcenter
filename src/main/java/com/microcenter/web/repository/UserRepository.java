package com.microcenter.web.repository;

import com.microcenter.web.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
