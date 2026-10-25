package com.ecom.ecomApp.service;

import com.ecom.ecomApp.enums.OrderStatus;
import com.ecom.ecomApp.enums.PayMethod;
import com.ecom.ecomApp.model.Orders;

import java.util.List;

public interface OrderService {
    Orders placeOrder(Long customerId, PayMethod payMethod);

    List<Orders> getOrderHistory(Long customerId);

    Orders getOrderById(Long customerId, Long orderId);

    OrderStatus getOrderStatus(Long customerId, Long orderId);
}
