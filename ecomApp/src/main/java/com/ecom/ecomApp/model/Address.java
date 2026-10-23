package com.ecom.ecomApp.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Address {
    private Long id;
    private String Country;
    private String State;
    private String City;
    private String District;
    private String postalCode;
    private String contactNumber;
}
