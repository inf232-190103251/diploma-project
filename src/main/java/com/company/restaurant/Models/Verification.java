package com.company.restaurant.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private long id;

    @Column(name = "phone_number",unique = true, nullable = false)
    public String phoneNumber;

    @Size(min = 4, max = 4, message = "The PIN Code must be exactly 4 ")
    @Column(name = "pin_code", length = 4)
    public String pinCode;

    @NotNull
    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified=false;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiration_time")
    public Date expirationTime;

    public Verification() {
    }

    public Verification(String phoneNumber, @Size(min = 4, max = 4) String pinCode, Date expirationTime) {
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
        this.expirationTime = expirationTime;
    }

    public long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }
}
