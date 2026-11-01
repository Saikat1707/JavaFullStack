package com.ecom.ecomApp.controller;

import com.ecom.ecomApp.model.Cart;
import com.ecom.ecomApp.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<Cart> getCart(
            @PathVariable("customerId") Long customerId) {
        return ResponseEntity.ok().body(cartService.getCartByCustomerId(customerId));
    }

    @PostMapping("/add/product/{customerId}/{productId}/{quantity}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable Long customerId,
            @PathVariable Long productId,
            @PathVariable int quantity
    ){
        return ResponseEntity.ok()
                .body(cartService.addProductToCart(customerId, productId, quantity));
    }
    @PatchMapping("/update/quantity/{customerId}/{cartItemId}/{quantity}")
    public ResponseEntity<Cart> updateProductQuantity(
            @PathVariable Long customerId,
            @PathVariable Long cartItemId,
            @PathVariable int quantity
    ){
        return ResponseEntity.ok()
                .body(cartService.updateCartItemQuantity(customerId, cartItemId, quantity));
    }

    @DeleteMapping("/remove/product/{customerId}/{cartItemId}")
    public ResponseEntity<Void> removeProductFromCart(
            @PathVariable Long customerId,
            @PathVariable Long cartItemId
    ){
        cartService.removeProductFromCart(customerId, cartItemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/clear/{customerId}")
    public ResponseEntity<Void> clearCart(
            @PathVariable Long customerId
    ){
        cartService.clearCart(customerId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available/{customerId}/{productId}")
    public boolean isProductAvailable(
            @PathVariable Long customerId,
            @PathVariable Long productId
    ){
        return cartService.isProductInCart(customerId,productId);
    }
}
