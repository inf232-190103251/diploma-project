package com.company.restaurant.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class PhoneNumber {

    private String phoneNumber;

    public PhoneNumber(@JsonProperty("phoneNumber") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
