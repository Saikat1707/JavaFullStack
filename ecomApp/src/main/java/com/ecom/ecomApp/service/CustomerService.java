package com.ecom.ecomApp.service;

import com.ecom.ecomApp.model.Address;
import com.ecom.ecomApp.model.Customers;

public interface CustomerService {
    Customers createCustomer(Customers customer);
    Customers loginCustomer(String email, String password);
    Customers updateCustomer(Long customerId,Customers customer);
    Customers getCustomerById(Long customerId);

    Address createAddress(Long customerId,Address address);
    Address updateAddress(Long customerId,Address address);
}
