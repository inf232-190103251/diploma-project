package com.company.restaurant.MessageSenders;

import com.company.restaurant.DTO.Code;
import com.company.restaurant.DTO.SmsRequest;
import com.company.restaurant.DTO.UsernameAndPasswordDTO;
import com.company.restaurant.MessageSenders.interfaces.SmsSender;
import com.company.restaurant.Methods.PINGenerator;
import com.company.restaurant.Models.Verification;
import com.company.restaurant.Repositories.VerificationRepository;
import com.company.restaurant.Services.UsersDetailService;
import com.company.restaurant.Services.VerificationServiceImpl;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TwilioSmsSenderService implements SmsSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSenderService.class);

    public static final String VERIFICATION_CODE = "Online Ordering System {Verification code:";

    private final TwilioConfiguration twilioConfiguration;
    @Autowired
    private final VerificationServiceImpl verificationService;
    @Autowired
    private UsersDetailService usersDetailService;

    @Autowired
    public TwilioSmsSenderService(TwilioConfiguration twilioConfiguration, VerificationRepository verificationRepository, VerificationServiceImpl verificationService) {
        this.twilioConfiguration = twilioConfiguration;
        this.verificationService = verificationService;
    }

    @Override
    public void sendSms(com.company.restaurant.DTO.SmsRequest smsRequest) {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrailNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", smsRequest);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsRequest.getPhoneNumber() + "] is not a valid number"
            );
        }

    }

    @Override
    @Transactional
    public String sendVerificationCode(com.company.restaurant.DTO.PhoneNumber phoneNumber) {
        com.company.restaurant.DTO.SmsRequest smsRequest = null;
        if (isPhoneNumberValid(phoneNumber.getPhoneNumber())) {
            if (!verificationService.isExists(phoneNumber.getPhoneNumber())) {

                PhoneNumber to = new PhoneNumber(phoneNumber.getPhoneNumber());
                PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrailNumber());
                String pinCode = PINGenerator.generate();

                String message = VERIFICATION_CODE + pinCode + "}";
                smsRequest = new SmsRequest(phoneNumber.getPhoneNumber(), message);


                Verification verification = new Verification();
                verification.setPhoneNumber(phoneNumber.getPhoneNumber());
                verification.setPinCode(pinCode);
                verificationService.save(verification);

                MessageCreator creator = Message.creator(to, from, message);
//                Message message1 = creator.create();
                LOGGER.info("Send sms {}", smsRequest);
//                LOGGER.info(message1.getSid());
                return phoneNumber.getPhoneNumber();
            } else {
                return "auto-login";
            }
        } else {
            return "error";
        }
    }

    @Override
    public UsernameAndPasswordDTO checkVerificationCode(Code code) throws Exception {
        if (verificationService.isExistsByCode(code.getCode())) {
            Verification verification = verificationService.getByCode(code.getCode());
            UsernameAndPasswordDTO dto = usersDetailService.authWithoutPassword(verification.getPhoneNumber());
            verificationService.delete(verification);
            return dto;
        } else {
            throw new Exception("Data not found");
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }
}
