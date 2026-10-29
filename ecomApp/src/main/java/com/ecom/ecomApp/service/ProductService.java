package com.ecom.ecomApp.service;

import com.ecom.ecomApp.model.Products;

import java.util.List;

public interface ProductService {
    List<Products> getAllProducts();

    Products getProductById(Long productId);

    Products createProduct(Long adminId, Products product);

    Products updateProduct(Long adminId, Long productId, Products product);

    void deleteProduct(Long adminId, Long productId);

    List<Products> getAllProductsByAdmin(Long adminId);

    boolean isProductAvailable(Long productId);
}
