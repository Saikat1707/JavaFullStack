package com.ecom.ecomApp.repo;

import com.ecom.ecomApp.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Products,Long> {
    List<Products> findAllByListedBy_Id(Long adminId);
}
