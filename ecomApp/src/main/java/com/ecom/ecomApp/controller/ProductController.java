
package com.ecom.ecomApp.controller;

import com.ecom.ecomApp.model.Products;
import com.ecom.ecomApp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        return ResponseEntity.ok(
                productService.getAllProducts()
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Products> getProductById(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                productService.getProductById(productId)
        );
    }

    @PostMapping("/create/{adminId}")
    public ResponseEntity<Products> createProduct(
            @PathVariable Long adminId,
            @Valid @RequestBody Products product) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(adminId, product));
    }

    @PutMapping("/update/{adminId}/{productId}")
    public ResponseEntity<Products> updateProduct(
            @PathVariable Long adminId,
            @PathVariable Long productId,
            @Valid @RequestBody Products product) {

        return ResponseEntity.ok(
                productService.updateProduct(adminId, productId, product)
        );
    }

    @DeleteMapping("/delete/{adminId}/{productId}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long adminId,
            @PathVariable Long productId) {

        productService.deleteProduct(adminId, productId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Products>> getAllProductsByAdmin(
            @PathVariable Long adminId) {

        return ResponseEntity.ok(
                productService.getAllProductsByAdmin(adminId)
        );
    }

    @GetMapping("/{productId}/availability")
    public ResponseEntity<Boolean> isProductAvailable(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                productService.isProductAvailable(productId)
        );
    }
}
