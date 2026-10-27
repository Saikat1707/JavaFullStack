package com.ecom.ecomApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    @NotNull(message = "Email is required")
    @Email(message = "Enter a valid email address")
    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @NotNull(message = "Organisation name is required")
    private String organisationName;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "listedBy")
    private List<Products> productsList = new ArrayList<>();
}
