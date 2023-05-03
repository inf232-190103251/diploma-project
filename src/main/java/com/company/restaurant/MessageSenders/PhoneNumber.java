package com.company.restaurant.MessageSenders;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PhoneNumber {
    @NotNull
    @NotBlank
    private String phoneNumber;

    public PhoneNumber(@NotNull @NotBlank @JsonProperty("phoneNumber") String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public PhoneNumber() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
