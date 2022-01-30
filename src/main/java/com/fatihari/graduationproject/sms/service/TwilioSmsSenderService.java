package com.fatihari.graduationproject.sms.service;

import com.fatihari.graduationproject.sms.configuration.TwilioConfiguration;
import com.fatihari.graduationproject.sms.entity.SmsInfo;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSmsSenderService implements ISmsSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSenderService.class);

    private final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSenderService(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsInfo smsInfo) {
        if (isPhoneNumberValid(smsInfo.getPhoneNumber()))
        {
            PhoneNumber to = new PhoneNumber(smsInfo.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = smsInfo.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", smsInfo);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + smsInfo.getPhoneNumber() + "] is not a valid number."
            );
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        // TODO: Implement phone number validator
        return true;
    }
}