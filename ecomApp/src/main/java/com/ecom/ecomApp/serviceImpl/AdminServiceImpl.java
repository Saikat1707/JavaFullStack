package com.ecom.ecomApp.serviceImpl;

import com.ecom.ecomApp.dto.CustomerUpdateRequestDto;
import com.ecom.ecomApp.model.Address;
import com.ecom.ecomApp.model.Admin;
import com.ecom.ecomApp.repo.AddressRepo;
import com.ecom.ecomApp.repo.AdminRepo;
import com.ecom.ecomApp.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;
    private final AddressRepo addressRepo;

    public AdminServiceImpl(AdminRepo adminRepo,AddressRepo addressRepo){
        this.adminRepo = adminRepo;
        this.addressRepo = addressRepo;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        if (admin == null || admin.getEmail() == null
                || admin.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is required");
        }

        Admin existed = adminRepo.findByEmail(admin.getEmail());
        if (existed != null) {
            throw new RuntimeException("Customer already exists");
        }

        String email = admin.getEmail().trim().toLowerCase();
        admin.setEmail(email);

        return adminRepo.save(admin);
    }

    @Override
    public Admin loginAdmin(String email, String password) {
        Admin admin = adminRepo.findByEmail(email);
        if (admin == null) {
            throw new RuntimeException("Admin not found");
        }
        if(!password.equals(admin.getPassword())){
            throw new RuntimeException("Password not match");
        }
        return admin;
    }

    @Override
    public Admin updateAdmin(Long adminId, CustomerUpdateRequestDto admin) {
        Admin existed = adminRepo.findById(adminId)
                .orElseThrow(
                        () -> new RuntimeException("Admin not found")
                );

        if(admin.getEmail() != null && !existed.getEmail().equals(admin.getEmail())){
            existed.setEmail(admin.getEmail());
        }

        if(admin.getFullName() != null &&
                !existed.getFullName().equals(admin.getFullName())){
            existed.setFullName(admin.getFullName());
        }

        if (admin.getDob() != null
                && !Objects.equals(
                admin.getDob(), existed.getDob())) {
            existed.setDob(admin.getDob());
        }

        adminRepo.save(existed);

        return existed;
    }

    @Override
    public Admin getAdminById(Long adminId) {
        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Admin Not Found"));
        return admin;
    }

    @Override
    @Transactional
    public Address createAddress(Long adminId, CustomerUpdateRequestDto address) {
        if(address == null){
            throw new IllegalArgumentException("Address is required");
        }
        Admin admin = getAdminById(adminId);

        Address newAddress = new Address();
        newAddress.setCity(address.getCity());
        newAddress.setCountry(address.getCountry());
        newAddress.setState(address.getState());
        newAddress.setContactNumber(address.getContactNumber());
        newAddress.setPostalCode(address.getPostalCode());

        admin.setAddress(newAddress);
        adminRepo.save(admin);

        return addressRepo.save(newAddress);
    }

    @Override
    public Address updateAddress(Long adminId, CustomerUpdateRequestDto address) {
        if (address == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() ->
                        new RuntimeException("admin not found with id: " + adminId));

        Address existingAddress = admin.getAddress();

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

        admin.setAddress(savedAddress);
        adminRepo.save(admin);

        return savedAddress;
    }
}
