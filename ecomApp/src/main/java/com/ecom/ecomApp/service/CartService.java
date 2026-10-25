package com.ecom.ecomApp.service;

import com.ecom.ecomApp.model.Cart;

public interface CartService {
    Cart getCartByCustomerId(Long customerId);

    Cart addProductToCart(Long customerId, Long productId, int quantity);

    Cart updateCartItemQuantity(Long customerId, Long cartItemId, int quantity);

    void removeProductFromCart(Long customerId, Long cartItemId);

    void clearCart(Long customerId);
}
