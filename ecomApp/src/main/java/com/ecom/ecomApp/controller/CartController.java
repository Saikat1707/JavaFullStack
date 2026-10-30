package com.ecom.ecomApp.controller;

import com.ecom.ecomApp.model.Cart;
import com.ecom.ecomApp.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/add/product/{customerId}/{productId}/{quantity}")
    public ResponseEntity<Cart> addProductToCart(
            @PathVariable Long customerId,
            @PathVariable Long productId,
            @PathVariable int quantity
    ){
       return ResponseEntity.ok()
               .body(cartService.addProductToCart(customerId, productId, quantity));
    }
    @GetMapping("/update/quantity/{customerId}/{cartId}/{quantity}")
    public ResponseEntity<Cart> updateProductQuantity(
            @PathVariable Long customerId,
            @PathVariable Long cartId,
            @PathVariable int quantity
    ){
        return ResponseEntity.ok()
                .body(cartService.updateCartItemQuantity(customerId, cartId, quantity));
    }

    @GetMapping("/remove/product/{customerId}/{cartItemId}")
    public void removeProductFromCart(
            @PathVariable Long customerId,
            @PathVariable Long cartItemId
    ){
        cartService.removeProductFromCart(customerId, cartItemId);
    }

    @GetMapping("/clear/{customerId}")
    public void clearCart(
            @PathVariable Long customerId
    ){
        cartService.clearCart(customerId);
    }

    @GetMapping("/available/{customerId}/{productId}")
    public boolean isProductAvailable(
            @PathVariable Long customerId,
            @PathVariable Long productId
    ){
        return cartService.isProductInCart(customerId,productId);
    }
}
