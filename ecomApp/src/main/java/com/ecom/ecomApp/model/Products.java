package com.ecom.ecomApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin listedBy;

    @OneToMany(mappedBy = "product")
    private List<CartItems> carted;

    @OneToMany(mappedBy = "product")
    private  List<OrderItems> ordered;
}
