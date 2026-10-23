package com.ecom.ecomApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double purchasePrice;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orderLot;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products product;

}
