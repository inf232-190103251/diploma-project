package com.company.restaurant.MessageSenders;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {

    private final TwilioConfiguration twilioConfiguration;
    private final static Logger LOGGER= LoggerFactory.getLogger(TwilioInitializer.class);

    @Autowired
    public TwilioInitializer(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
        Twilio.init(
                twilioConfiguration.getAccountSid(),
                twilioConfiguration.getToken()
        );
        LOGGER.info("Twilio is initialized with s_id -> "+twilioConfiguration.getAccountSid());
    }

    public TwilioConfiguration getTwilioConfiguration() {
        return twilioConfiguration;
    }
}
