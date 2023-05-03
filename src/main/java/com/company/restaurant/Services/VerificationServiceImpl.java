package com.company.restaurant.Services;

import com.company.restaurant.Models.Verification;
import com.company.restaurant.Repositories.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Size;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationServiceImpl {
    @Autowired
    private final VerificationRepository verificationRepository;

    public VerificationServiceImpl(VerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    Verification findByPhoneNumber(String phoneNumber) {
        return verificationRepository.findByPhoneNumber(phoneNumber);
    }

    Verification findByPhoneNumberAndPinCode(String phoneNumber, @Size(min = 4, max = 4, message = "The PIN Code must be exactly 4 ") String pinCode) {
        return verificationRepository.findByPhoneNumberAndPinCode(phoneNumber, pinCode);
    }

    public void save(Verification verification) {
        long day = TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
        verification.setExpirationTime(new Date(System.currentTimeMillis() + day));
        this.verificationRepository.save(verification);
    }

    public void saveState(Verification verification) {
        verificationRepository.save(verification);
    }

    public void delete(Verification verification) {
        verificationRepository.delete(verification);
    }

    public Verification getByCode(String code) {
        return verificationRepository.getByPinCode(code);
    }

    public boolean isExists(String phoneNumber) {
        return verificationRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean isExistsByCode(String code) {
        return verificationRepository.existsByPinCode(code);
    }
}
