package com.ecom.ecomApp.service;

import com.ecom.ecomApp.model.*;

import java.util.List;

public interface UserService {
    Customers createAccount(Customers customer);
    Customers login(String email, String password);
    Customers updateAccount(Customers customer);
    Customers getProfile(Long customerId);

    List<Orders> getAllOrdersOfTheUser(Long customerId);
    List<OrderItems> getOrderedProducts(Long orderId);
    Orders getSingleOrderById(Long orderId);

    Cart getCartById(Long customerId);
    List<CartItems> getCartItems(Long cartId);



}
