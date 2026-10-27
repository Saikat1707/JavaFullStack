package com.ecom.ecomApp.repo;

import com.ecom.ecomApp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepo extends JpaRepository<Admin,Long> {
    Admin findByEmail(String email);
}
