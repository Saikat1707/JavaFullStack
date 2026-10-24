package com.ecom.ecomApp.repo;

import com.ecom.ecomApp.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products,Long> {
}
