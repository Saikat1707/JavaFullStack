package com.ecom.ecomApp.serviceImpl;

import com.ecom.ecomApp.model.Admin;
import com.ecom.ecomApp.model.Products;
import com.ecom.ecomApp.repo.AdminRepo;
import com.ecom.ecomApp.repo.ProductRepo;
import com.ecom.ecomApp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final AdminRepo adminRepo;

    public ProductServiceImpl(ProductRepo productRepo,
                              AdminRepo adminRepo){
        this.productRepo = productRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Products getProductById(Long productId) {
        Products product = productRepo.findById(productId)
                .orElseThrow(
                        ()-> new RuntimeException("Product not found!")
                );
        return product;
    }

    @Override
    public Products createProduct(Long adminId, Products product) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(
                        ()-> new RuntimeException("Admin not found!")
                );

        product.setListedBy(admin);
        return productRepo.save(product);
    }

    @Override
    public Products updateProduct(Long adminId, Long productId, Products product) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(
                        ()-> new RuntimeException("Admin not found!")
                );
        Products existingProduct = getProductById(productId);

        if (existingProduct.getListedBy() == null ||
                !Objects.equals(
                        existingProduct.getListedBy().getId(),
                        admin.getId())) {
            throw new RuntimeException(
                    "You are not authorized to perform this action!");
        }

        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }

        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }

        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }

        if (product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }

        if (product.getTotalStock() != null) {
            existingProduct.setTotalStock(product.getTotalStock());
        }

        return productRepo.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long adminId, Long productId) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(
                        ()-> new RuntimeException("Admin not found!")
                );
        Products existingProduct = getProductById(productId);

        if (existingProduct.getListedBy() == null ||
                !Objects.equals(
                        existingProduct.getListedBy().getId(),
                        admin.getId()
                )) {
            throw new RuntimeException(
                    "You are not authorized to perform this action!"
            );
        }

        productRepo.delete(existingProduct);

    }

    @Override
    public List<Products> getAllProductsByAdmin(Long adminId) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(
                        ()-> new RuntimeException("Admin not found!")
                );
        return productRepo.findAllByListedBy_Id(admin.getId());
    }

    @Override
    public boolean isProductAvailable(Long productId) {
        Products product = getProductById(productId);
        return product.getTotalStock() != null
                && product.getTotalStock() > 0;
    }
}
