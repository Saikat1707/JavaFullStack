package com.ecom.ecomApp.repo;

import com.ecom.ecomApp.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<OrderItems,Long> {
}
