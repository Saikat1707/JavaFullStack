package com.ecom.ecomApp.serviceImpl;

import com.ecom.ecomApp.dto.CustomerUpdateRequestDto;
import com.ecom.ecomApp.model.Address;
import com.ecom.ecomApp.model.Customers;
import com.ecom.ecomApp.repo.AddressRepo;
import com.ecom.ecomApp.repo.CustomerRepo;
import com.ecom.ecomApp.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final AddressRepo addressRepo;
    public CustomerServiceImpl(CustomerRepo customerRepo,AddressRepo addressRepo) {
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
    }


    @Override
    public Customers createCustomer(Customers customer) {

        if (customer == null || customer.getEmail() == null
                || customer.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        Customers existed = customerRepo.findByEmail(customer.getEmail());
        if (existed != null) {
            throw new RuntimeException("Customer already exists");
        }

        String email = customer.getEmail().trim().toLowerCase();
        customer.setEmail(email);

        return customerRepo.save(customer);
    }

    @Override
    public Customers loginCustomer(String email, String password) {
        Customers customer = customerRepo.findByEmail(email);
        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        if(!password.equals(customer.getPassword())){
            throw new RuntimeException("Password not match");
        }
        return customer;
    }

    @Override
    public Customers updateCustomer(Long customerId, CustomerUpdateRequestDto customer) {
        Customers existed = customerRepo.findById(customerId)
                .orElseThrow(
                        () -> new RuntimeException("Customer not found")
                );

        if(customer.getEmail() != null && !existed.getEmail().equals(customer.getEmail())){
            existed.setEmail(customer.getEmail());
        }

        if(customer.getFullName() != null &&
        !existed.getFullName().equals(customer.getFullName())){
            existed.setFullName(customer.getFullName());
        }

        if (customer.getDob() != null
                && !Objects.equals(
                customer.getDob(), existed.getDob())) {
            existed.setDob(customer.getDob());
        }

        customerRepo.save(existed);

        return existed;
    }

    @Override
    public Customers getCustomerById(Long customerId) {
        Customers customer = customerRepo.findById(customerId)
                .orElseThrow(
                        ()-> new RuntimeException("Customer not found")
                );
        return customer;
    }

    @Override
    @Transactional
    public Address createAddress(Long customerId, CustomerUpdateRequestDto address) {
        if(address == null){
            throw new IllegalArgumentException("Address is required");
        }
        Customers customer = getCustomerById(customerId);

        Address newAddress = new Address();
        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setState(address.getState());
        newAddress.setContactNumber(address.getContactNumber());
        newAddress.setPostalCode(address.getPostalCode());

        customer.setAddress(newAddress);
        customerRepo.save(customer);

        return addressRepo.save(newAddress);
    }

    @Override
    public Address updateAddress(Long customerId, CustomerUpdateRequestDto address) {

        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        Customers customer = customerRepo.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found with id: " + customerId));

        Address existingAddress = customer.getAddress();

        if (existingAddress == null) {
            existingAddress = new Address();
        }

        if (address.getCountry() != null
                && !address.getCountry().equals(existingAddress.getCountry())) {
            existingAddress.setCountry(address.getCountry());
        }

        if (address.getState() != null
                && !address.getState().equals(existingAddress.getState())) {
            existingAddress.setState(address.getState());
        }

        if (address.getCity() != null
                && !address.getCity().equals(existingAddress.getCity())) {
            existingAddress.setCity(address.getCity());
        }

        if (address.getDistrict() != null
                && !address.getDistrict().equals(existingAddress.getDistrict())) {
            existingAddress.setDistrict(address.getDistrict());
        }

        if (address.getPostalCode() != null
                && !address.getPostalCode().equals(existingAddress.getPostalCode())) {
            existingAddress.setPostalCode(address.getPostalCode());
        }

        if (address.getContactNumber() != null
                && !address.getContactNumber().equals(existingAddress.getContactNumber())) {
            existingAddress.setContactNumber(address.getContactNumber());
        }

        Address savedAddress = addressRepo.save(existingAddress);

        customer.setAddress(savedAddress);
        customerRepo.save(customer);

        return savedAddress;
    }
}
