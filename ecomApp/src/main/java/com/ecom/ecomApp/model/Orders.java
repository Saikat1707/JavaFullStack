package com.ecom.ecomApp.model;

import com.ecom.ecomApp.enums.OrderStatus;
import com.ecom.ecomApp.enums.PayMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDateAndTime;
    private LocalDateTime updateDateAndTime;
    private LocalDateTime deliveryDateAndTime;
    private Double totalPrice;

    @Enumerated(EnumType.STRING)
    private PayMethod payMethod = PayMethod.CASHONDELIVERY;

    private boolean isPaid;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.CONFIRMED;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @OneToMany(mappedBy = "orderLot")
    private List<OrderItems> orderItems = new ArrayList<>();
}
