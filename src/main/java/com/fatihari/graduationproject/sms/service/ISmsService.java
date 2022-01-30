package com.fatihari.graduationproject.sms.service;

import com.fatihari.graduationproject.sms.entity.SmsInfo;

public interface ISmsService
{
    void sendSms(SmsInfo smsInfo);
}
