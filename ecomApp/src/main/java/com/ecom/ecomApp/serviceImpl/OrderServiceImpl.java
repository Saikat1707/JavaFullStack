package com.ecom.ecomApp.serviceImpl;

import com.ecom.ecomApp.enums.OrderStatus;
import com.ecom.ecomApp.enums.PayMethod;
import com.ecom.ecomApp.model.Orders;
import com.ecom.ecomApp.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Override
    public Orders placeOrder(Long customerId, PayMethod payMethod) {
        return null;
    }

    @Override
    public List<Orders> getOrderHistory(Long customerId) {
        return List.of();
    }

    @Override
    public Orders getOrderById(Long customerId, Long orderId) {
        return null;
    }

    @Override
    public OrderStatus getOrderStatus(Long customerId, Long orderId) {
        return null;
    }
}
