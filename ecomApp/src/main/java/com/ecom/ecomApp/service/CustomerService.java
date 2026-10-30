package com.ecom.ecomApp.service;

import com.ecom.ecomApp.dto.CustomerUpdateRequestDto;
import com.ecom.ecomApp.model.Address;
import com.ecom.ecomApp.model.Customers;

public interface CustomerService {
    Customers createCustomer(Customers customer);
    Customers loginCustomer(String email, String password);
    Customers updateCustomer(Long customerId, CustomerUpdateRequestDto customer);
    Customers getCustomerById(Long customerId);

    Address createAddress(Long customerId,CustomerUpdateRequestDto address);
    Address updateAddress(Long customerId,CustomerUpdateRequestDto address);
}
