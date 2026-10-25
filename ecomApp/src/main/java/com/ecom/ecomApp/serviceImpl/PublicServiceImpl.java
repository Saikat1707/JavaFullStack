package com.ecom.ecomApp.serviceImpl;

import com.ecom.ecomApp.model.Products;
import com.ecom.ecomApp.repo.ProductRepo;
import com.ecom.ecomApp.service.PublicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicServiceImpl implements PublicService {

    private final ProductRepo productRepo;
    PublicServiceImpl(ProductRepo productRepo){
        this.productRepo = productRepo;
    }
    @Override
    public List<Products> viewAllProduct() {
        List<Products> productsList = productRepo.findAll();
        return productsList;
    }

    @Override
    public Products viewProductById(Long id) {
        Products product = productRepo.findById(id).get();
        return product;
    }
}
