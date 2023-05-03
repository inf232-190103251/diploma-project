package com.company.restaurant.Controllers;

import com.company.restaurant.DTO.Code;
import com.company.restaurant.DTO.PhoneNumber;
import com.company.restaurant.DTO.SmsRequest;
import com.company.restaurant.DTO.UsernameAndPasswordDTO;
import com.company.restaurant.Services.SmsSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("api/v1/sms")
public class PhoneNumberValidationController {

    private final SmsSenderService service;

    @Autowired
    public PhoneNumberValidationController(SmsSenderService service) {
        this.service = service;
    }


    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        service.sendSms(smsRequest);
    }

    @PostMapping("/code")
    public ResponseEntity<?> sendVerificationCode(@Valid @RequestBody PhoneNumber phoneNumber) {
        if (phoneNumber.getPhoneNumber().length() == 12) {
            if (service.sendVerificationCode(phoneNumber).equals(phoneNumber.getPhoneNumber())) {
                return ResponseEntity.status(204).body("Completed successfully check the phone");
            } else if (service.sendVerificationCode(phoneNumber).equals("error")) {
                return ResponseEntity.badRequest().body("Completed unsuccessfully,phone is not valid...");
            } else {
                return ResponseEntity.badRequest().body("Already sent...Check the phone");
            }
        } else {
            return ResponseEntity.badRequest().body("Completed unsuccessfully,bad credentials...");
        }
    }


    @PostMapping("/check")
    public ResponseEntity<?> checkVerificationCode(@Valid @RequestBody Code code) {
        if (code.getCode().length() == 4) {
            try {
                UsernameAndPasswordDTO dto = service.checkVerificationCode(code);
                return ResponseEntity.ok(dto);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Completed unsuccessfully,bad credentials...");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Principal principal) {
        return ResponseEntity.ok(principal);
    }

    private static class CustomToken {
        private final String token;

        private CustomToken(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }

}