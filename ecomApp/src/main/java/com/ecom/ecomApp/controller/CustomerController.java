package com.ecom.ecomApp.controller;

import com.ecom.ecomApp.dto.CustomerUpdateRequestDto;
import com.ecom.ecomApp.dto.LoginReqDto;
import com.ecom.ecomApp.model.Address;
import com.ecom.ecomApp.model.Customers;
import com.ecom.ecomApp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cust")
public class CustomerController {
    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customers> getCustomerById(
            @PathVariable Long customerId) {
        Customers customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/create")
    public ResponseEntity<Customers> createCustomer(
           @Valid @RequestBody Customers customer
    ){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerService.createCustomer(customer));
    }

    @PostMapping("/login")
    public ResponseEntity<Customers> loginCustomer(
            @RequestBody LoginReqDto loginReqDto
            ){
        return ResponseEntity.ok()
                .body(customerService.loginCustomer(
                        loginReqDto.getEmail(),loginReqDto.getPassword())
                );
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customers> updateCustomer(
            @RequestBody CustomerUpdateRequestDto dto,
            @PathVariable Long customerId
            ){
        return ResponseEntity.ok().body(customerService.updateCustomer(customerId, dto));
    }

    @PostMapping("/create/address/{customerId}")
    public ResponseEntity<Address> createAddress(
            @PathVariable Long customerId,
            @RequestBody CustomerUpdateRequestDto address
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                customerService.createAddress(customerId,address)
        );
    }

    @PutMapping("/update/address/{customerId}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Long customerId,
            @RequestBody CustomerUpdateRequestDto address
    ){
        return ResponseEntity.ok().body(customerService.updateAddress(customerId, address));
    }

}
