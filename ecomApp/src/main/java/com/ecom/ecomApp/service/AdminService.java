package com.ecom.ecomApp.service;

import com.ecom.ecomApp.dto.CustomerUpdateRequestDto;
import com.ecom.ecomApp.model.Address;
import com.ecom.ecomApp.model.Admin;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin loginAdmin(String email, String password);
    Admin updateAdmin(Long adminId, CustomerUpdateRequestDto admin);
    Admin getAdminById(Long adminId);

    Address createAddress(Long adminId,CustomerUpdateRequestDto address);
    Address updateAddress(Long adminId,CustomerUpdateRequestDto address);
}
