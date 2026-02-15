package com.microcenter.web.service;

import com.microcenter.web.domain.Cart;
import com.microcenter.web.domain.CartItem;
import com.microcenter.web.domain.Product;
import com.microcenter.web.domain.User;
import com.microcenter.web.exceptions.ProductNotFoundException;
import com.microcenter.web.repository.CartRepository;
import com.microcenter.web.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class CartServiceImpl implements CartService{
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart getCartByUser(User currentUser) {
        Optional<Cart> optionalCart = cartRepository.findByUser(currentUser);

        return optionalCart.orElseGet(() -> createNewCart(currentUser));
    }

    @Override
    public void addProductToCart(String productId, Cart cart) {
        if (productId == null || productId.length() == 0) {
            throw new IllegalArgumentException("Product id cannot be null");
        }

        Long id = parseProductId(productId);

        var product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found by id: " + id));

        addProductToCart(product, cart);

        Integer totalTotalItem = getTotalItem(cart);
        BigDecimal totalPrice = calculateTotalPrice(cart);

        cart.setTotalItem(totalTotalItem);
        cart.setTotalPrice(totalPrice);

        cartRepository.update(cart);

    }

    private void addProductToCart(Product product, Cart cart) {
        var cartItemOptional = findSimilarProductInCart(cart, Product);
        var cartItem = cartItemOptional.map(this.increaseQuantityByOne).orElseGet(() -> createNewCartItem(product));

        cart.getCartItems().add(cartItem);
    }

    private Long parseProductId(String productId) {
        try {
            return Long.parseLong(productId);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Product id must be a valid number", ex);
        }
    }

    private CartItem increaseQuantityByOne(CartItem cartItem) {
        cartItem.setQuantity(cartItem.getQuantity() + 1);

        BigDecimal totalPrice = cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        cartItem.setPrice(totalPrice);

        return cartItemRepository.update(cartItem);
    }

    private CartItem createNewShoppingCartItem(Product product) {
        var cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(1);
        cartItem.setPrice(product.getPrice());

        return cartItemRepository.save(cartItem);
    }

    private Integer getTotalItem(Cart cart) {
        return cart.getCartItems().stream().mapToInt(CartItem::getQuantity).reduce(0, Integer::sum);
    }

    private BigDecimal calculateTotalPrice(Cart cart) {
        return cart.getCartItems().stream().map(CartItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Cart createNewCart(User currentUser) {
        Cart cart = new Cart();
        cart.setUser(currentUser);
        return cartRepository.save(cart);
    }
}
