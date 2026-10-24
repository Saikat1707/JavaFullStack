package com.ecom.ecomApp.repo;

import com.ecom.ecomApp.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart,Long> {
}
