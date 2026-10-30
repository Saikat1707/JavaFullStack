
package com.ecom.ecomApp.controller;

import com.ecom.ecomApp.dto.CustomerUpdateRequestDto;
import com.ecom.ecomApp.dto.LoginReqDto;
import com.ecom.ecomApp.model.Address;
import com.ecom.ecomApp.model.Admin;
import com.ecom.ecomApp.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> getAdminById(
            @PathVariable Long adminId) {

        Admin admin = adminService.getAdminById(adminId);

        return ResponseEntity.ok(admin);
    }

    @PostMapping("/create")
    public ResponseEntity<Admin> createAdmin(
            @Valid @RequestBody Admin admin) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.createAdmin(admin));
    }

    @PostMapping("/login")
    public ResponseEntity<Admin> loginAdmin(
            @RequestBody LoginReqDto loginReqDto) {

        return ResponseEntity.ok(
                adminService.loginAdmin(
                        loginReqDto.getEmail(),
                        loginReqDto.getPassword()
                )
        );
    }

    @PutMapping("/update/{adminId}")
    public ResponseEntity<Admin> updateAdmin(
            @RequestBody CustomerUpdateRequestDto dto,
            @PathVariable Long adminId) {

        return ResponseEntity.ok(
                adminService.updateAdmin(adminId, dto)
        );
    }

    @PostMapping("/create/address/{adminId}")
    public ResponseEntity<Address> createAddress(
            @PathVariable Long adminId,
            @RequestBody CustomerUpdateRequestDto address) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminService.createAddress(adminId, address));
    }

    @PutMapping("/update/address/{adminId}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Long adminId,
            @RequestBody CustomerUpdateRequestDto address) {

        return ResponseEntity.ok(
                adminService.updateAddress(adminId, address)
        );
    }
}
