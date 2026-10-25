package com.ecom.ecomApp.controller;

import com.ecom.ecomApp.model.Products;
import com.ecom.ecomApp.service.PublicService;
import com.ecom.ecomApp.serviceImpl.PublicServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PublicController {
    private final PublicService publicService;
    PublicController(PublicServiceImpl publicService) {
        this.publicService = publicService;
    }

    @GetMapping("/products")
    public List<Products> getAllProducts(){
        return publicService.viewAllProduct();
    }

    @GetMapping("/products/{productId}")
    public Products getProductById(
            @PathVariable Long productId
    ){
        return publicService.viewProductById(productId);
    }

}
