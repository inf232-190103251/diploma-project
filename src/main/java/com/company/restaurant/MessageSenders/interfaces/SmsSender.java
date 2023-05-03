package com.company.restaurant.MessageSenders.interfaces;

import com.company.restaurant.DTO.Code;
import com.company.restaurant.DTO.PhoneNumber;
import com.company.restaurant.DTO.SmsRequest;
import com.company.restaurant.DTO.UsernameAndPasswordDTO;

public interface SmsSender {

    void sendSms(SmsRequest smsRequest);

    String sendVerificationCode(PhoneNumber phoneNumber);

    UsernameAndPasswordDTO checkVerificationCode(Code code) throws Exception;
}
