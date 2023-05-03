package com.company.restaurant.MessageSenders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfiguration {

    @Value("${twilio.account_sid}")
    private String accountSid;
    @Value("${twilio.auth_token}")
    private String token;
    @Value("${twilio.trial_number}")
    private String trailNumber;

    public String getAccountSid() {
        return accountSid;
    }

    public void setAccountSid(String accountSid) {
        this.accountSid = accountSid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTrailNumber() {
        return trailNumber;
    }

    public void setTrailNumber(String trailNumber) {
        this.trailNumber = trailNumber;
    }
}
