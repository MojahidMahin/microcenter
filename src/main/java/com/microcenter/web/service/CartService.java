package com.microcenter.web.service;

import com.microcenter.web.domain.Cart;
import com.microcenter.web.domain.User;

public interface CartService {
    Cart getCartByUser(User CurrentUser);
}
