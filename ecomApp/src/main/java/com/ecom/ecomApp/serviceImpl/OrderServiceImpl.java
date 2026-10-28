package com.ecom.ecomApp.serviceImpl;

import com.ecom.ecomApp.enums.OrderStatus;
import com.ecom.ecomApp.enums.PayMethod;
import com.ecom.ecomApp.model.*;
import com.ecom.ecomApp.repo.CartItemRepo;
import com.ecom.ecomApp.repo.CustomerRepo;
import com.ecom.ecomApp.repo.OrderRepo;
import com.ecom.ecomApp.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final CartItemRepo cartItemRepo;

    public OrderServiceImpl(OrderRepo orderRepo,
    CustomerRepo customerRepo, CartItemRepo cartItemRepo){
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.cartItemRepo = cartItemRepo;
    }


    @Override
    @Transactional
    public Orders placeOrder(Long customerId, PayMethod payMethod) {
        Customers customer = getCustomer(customerId);
        Cart cart = customer.getCart();

        if (cart == null || cart.getCartItemsList().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Orders orderLot = new Orders();
        double totalPrice = 0.0;

        for (CartItems item : cart.getCartItemsList()) {
            Products product = item.getProduct();

            if (product == null) {
                throw new RuntimeException("Product not found in cart");
            }

            if (item.getQuantity() <= 0) {
                throw new RuntimeException("Invalid cart item quantity");
            }

            if (product.getTotalStock() < item.getQuantity()) {
                throw new RuntimeException(
                        "Insufficient stock for product: " + product.getId()
                );
            }

            OrderItems orderItem = new OrderItems();
            orderItem.setOrderLot(orderLot);
            orderItem.setProduct(product);
            orderItem.setQuantity(item.getQuantity());

            double unitPrice = product.getPrice();
            double itemTotal = unitPrice * item.getQuantity();

            orderItem.setPurchasePrice(unitPrice);
            orderItem.setTotalPrice(itemTotal);

            totalPrice += itemTotal;
            orderLot.getOrderItems().add(orderItem);

            product.setTotalStock(
                    product.getTotalStock() - item.getQuantity()
            );
        }

        orderLot.setCustomer(customer);
        orderLot.setPayMethod(payMethod);
        orderLot.setOrderStatus(OrderStatus.CONFIRMED);
        orderLot.setOrderDateAndTime(LocalDateTime.now());
        orderLot.setDeliveryDateAndTime(LocalDateTime.now().plusDays(7));
        orderLot.setTotalPrice(totalPrice);
        orderLot.setPaid(false);

        Orders savedOrder = orderRepo.save(orderLot);

        cartItemRepo.deleteAll(cart.getCartItemsList());
        cart.getCartItemsList().clear();

        return savedOrder;
    }


    @Override
    public List<Orders> getOrderHistory(Long customerId) {
        Customers customer = getCustomer(customerId);
        return customer.getOrdersList();
    }

    @Override
    public Orders getOrderById(Long customerId, Long orderId) {
        List<Orders> ordersList = getOrderHistory(customerId);
        for(Orders order : ordersList) {
            if(order.getId().equals(orderId)) {
                return order;
            }
        }
        throw new RuntimeException("Order  not found");
    }

    @Override
    public OrderStatus getOrderStatus(Long customerId, Long orderId) {
        Orders order = getOrderById(customerId, orderId);
        return order.getOrderStatus();
    }

    private Customers getCustomer(Long customerId) {
        Customers customer = customerRepo.findById(customerId).orElseThrow(
                ()->new RuntimeException("Customer not found")
        );
        return customer;
    }
}
