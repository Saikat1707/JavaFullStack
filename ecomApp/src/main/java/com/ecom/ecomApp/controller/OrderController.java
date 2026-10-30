package com.ecom.ecomApp.controller;

import com.ecom.ecomApp.enums.OrderStatus;
import com.ecom.ecomApp.enums.PayMethod;
import com.ecom.ecomApp.model.Orders;
import com.ecom.ecomApp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;
    public  OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get/all/{customerId}")
    public ResponseEntity<List<Orders>> getAllOrders(
            @PathVariable Long customerId
    ) {
        return ResponseEntity.ok()
                .body(orderService.getOrderHistory(customerId));
    }

    @GetMapping("/get/{orderId}/{customerId}")
    public ResponseEntity<Orders> getAllOrders(
            @PathVariable Long orderId,
            @PathVariable Long customerId
    ){
        return ResponseEntity.ok()
                .body(orderService.getOrderById(orderId, customerId));
    }

    @GetMapping("/get/{orderId}/{customerId}")
    public ResponseEntity<OrderStatus> getOrderStatus(
            @PathVariable Long orderId,
            @PathVariable Long customerId
    ){
        return ResponseEntity.ok()
                .body(orderService.getOrderStatus(orderId, customerId));
    }

    @PostMapping("/place/{customerId}/{payMethod}")
    public ResponseEntity<Orders> placeOrder(
            @PathVariable Long customerId,
            @PathVariable PayMethod payMethod
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.placeOrder(customerId, payMethod));
    }
}
