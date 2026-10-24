package com.ecom.ecomApp.repo;

import com.ecom.ecomApp.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItems,Long> {
}
