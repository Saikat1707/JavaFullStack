package com.ecom.ecomApp.model;

import com.ecom.ecomApp.Enums.OrderStatus;
import com.ecom.ecomApp.Enums.PayMethod;
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

    @OneToMany(
            mappedBy = "orderLot",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItems> orderItems = new ArrayList<>();
}
