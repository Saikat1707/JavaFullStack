package com.ecom.ecomApp.repo;

import com.ecom.ecomApp.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Long> {
}
