package com.microcenter.web.repository;

import com.microcenter.web.domain.Cart;
import com.microcenter.web.domain.User;

import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findByUser(User currentUser);

    Cart save(Cart cart);
    Cart update(Cart cart);
}
