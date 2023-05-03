package com.company.restaurant.Services;

import com.company.restaurant.DTO.Code;
import com.company.restaurant.DTO.PhoneNumber;
import com.company.restaurant.DTO.SmsRequest;
import com.company.restaurant.DTO.UsernameAndPasswordDTO;
import com.company.restaurant.MessageSenders.TwilioSmsSenderService;
import com.company.restaurant.MessageSenders.interfaces.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsSenderService {
    private final SmsSender smsSender;

    @Autowired
    public SmsSenderService(TwilioSmsSenderService smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }

    public String sendVerificationCode(PhoneNumber phoneNumber) {
        return smsSender.sendVerificationCode(phoneNumber);
    }

    public UsernameAndPasswordDTO checkVerificationCode(Code code) throws Exception {
        return smsSender.checkVerificationCode(code);
    }

}
