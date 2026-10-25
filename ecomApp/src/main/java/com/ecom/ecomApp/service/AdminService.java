package com.ecom.ecomApp.service;

import com.ecom.ecomApp.model.Address;
import com.ecom.ecomApp.model.Admin;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin loginAdmin(String email, String password);
    Admin updateAdmin(Admin admin);
    Admin getAdminById(Long adminId);

    Address createAddress(Long adminId,Address address);
    Address updateAddress(Long adminId,Address address);
}
