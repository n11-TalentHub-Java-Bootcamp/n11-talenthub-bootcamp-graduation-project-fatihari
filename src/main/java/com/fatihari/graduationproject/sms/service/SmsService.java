package com.fatihari.graduationproject.sms.service;

import com.fatihari.graduationproject.sms.entity.SmsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SmsService implements ISmsService
{
    private final ISmsSenderService smsSender;

    @Autowired
    public SmsService(@Qualifier("twilio") TwilioSmsSenderService smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsInfo smsInfo) {
        smsSender.sendSms(smsInfo);
    }
}
