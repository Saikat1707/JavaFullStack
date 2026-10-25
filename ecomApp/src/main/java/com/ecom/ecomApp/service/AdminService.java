package com.ecom.ecomApp.service;

import com.ecom.ecomApp.model.Admin;
import com.ecom.ecomApp.model.Products;

import java.util.List;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin loginAdmin(String email, String password);
    Admin getAdminById(Long adminId);
    Admin updateAdmin(Admin admin);

    Products createProduct(Long adminId,Products product);
    Products getProductById(Long adminId,Long productId);
    List<Products> getAllProducts(Long adminId);
}
