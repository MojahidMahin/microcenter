package com.microcenter.web.repository;

import com.microcenter.web.domain.CartItem;

public interface CartItemRepository {
    CartItem save(CartItem cartItem);
    CartItem update(CartItem cartItem);
}
