package com.ecom.ecomApp.repo;

import com.ecom.ecomApp.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customers,Long> {
    Customers findByEmail(String email);
}
