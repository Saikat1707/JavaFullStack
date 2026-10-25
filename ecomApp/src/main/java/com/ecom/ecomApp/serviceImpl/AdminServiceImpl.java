package com.ecom.ecomApp.serviceImpl;

import com.ecom.ecomApp.model.Admin;
import com.ecom.ecomApp.model.Products;
import com.ecom.ecomApp.repo.AdminRepo;
import com.ecom.ecomApp.repo.ProductRepo;
import com.ecom.ecomApp.service.AdminService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;
    private final ProductRepo productRepo;

    public AdminServiceImpl(AdminRepo adminRepo, ProductRepo productRepo) {
        this.adminRepo = adminRepo;
        this.productRepo = productRepo;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        if (adminRepo.findByEmail(admin.getEmail()).isPresent()) {
            throw new RuntimeException(
                    "Admin with this email already exists"
            );
        }

        admin.setCreatedAt(LocalDateTime.now());

        return adminRepo.save(admin);
    }

    @Override
    public Admin loginAdmin(String email, String password) {
        Admin admin = adminRepo.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"
                        ));

        if (!admin.getPassword().equals(password)) {
            throw new RuntimeException(
                    "Invalid email or password"
            );
        }

        return admin;
    }

    @Override
    public Admin getAdminById(Long adminId) {
        return adminRepo.findById(adminId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Admin not found with id: " + adminId
                        ));
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        Admin existingAdmin = getAdminById(admin.getId());

        if (admin.getEmail() != null
                && !existingAdmin.getEmail().equals(admin.getEmail())) {
            if (adminRepo.findByEmail(admin.getEmail()).isPresent()) {
                throw new RuntimeException(
                        "Email is already registered"
                );
            }

            existingAdmin.setEmail(admin.getEmail());
        }

        if (admin.getFullName() != null) {
            existingAdmin.setFullName(admin.getFullName());
        }

        if (admin.getOrganisationName() != null) {
            existingAdmin.setOrganisationName(
                    admin.getOrganisationName()
            );
        }

        if (admin.getAddress() != null) {
            existingAdmin.setAddress(admin.getAddress());
        }

        if (admin.getPassword() != null
                && !admin.getPassword().isBlank()) {
            existingAdmin.setPassword(admin.getPassword());
        }

        return adminRepo.save(existingAdmin);
    }

    @Override
    public Products createProduct(Long adminId, Products product) {
        Admin admin = getAdminById(adminId);
        product.setListedBy(admin);
        return productRepo.save(product);
    }

    @Override
    public Products getProductById(Long adminId, Long productId) {
        getAdminById(adminId);

        Products product = productRepo.findById(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product not found with id: " + productId
                        ));

        if (product.getListedBy() == null
                || !product.getListedBy().getId().equals(adminId)) {
            throw new RuntimeException(
                    "This product does not belong to this admin"
            );
        }

        return product;
    }

    @Override
    public List<Products> getAllProducts(Long adminId) {
        getAdminById(adminId);
        return productRepo.findAllByListedBy_Id(adminId);
    }
}