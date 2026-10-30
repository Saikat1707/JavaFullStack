package com.ecom.ecomApp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class CustomerUpdateRequestDto {
    private String fullName;
    private String email;
    private LocalDate dob;

    private String Country;
    private String State;
    private String City;
    private String District;
    private String postalCode;
    private String contactNumber;
}
