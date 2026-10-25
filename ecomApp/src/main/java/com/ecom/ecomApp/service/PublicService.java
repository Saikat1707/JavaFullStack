package com.ecom.ecomApp.service;

import com.ecom.ecomApp.model.Products;

import java.util.List;

public interface PublicService {
    List<Products> viewAllProduct();
    Products viewProductById(Long id);
}
