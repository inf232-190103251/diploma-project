package com.company.restaurant.Repositories;

import com.company.restaurant.Models.Verification;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.Size;

public interface VerificationRepository extends CrudRepository<Verification, Long> {
    Verification findByPhoneNumber(String phoneNumber);

    Verification findByPhoneNumberAndPinCode(String phoneNumber, @Size(min = 4, max = 4, message = "The PIN Code must be exactly 4 ") String pinCode);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPinCode(@Size(min = 4, max = 4, message = "The PIN Code must be exactly 4 ") String pinCode);

    Verification getByPinCode(@Size(min = 4, max = 4, message = "The PIN Code must be exactly 4 ") String pinCode);

}
