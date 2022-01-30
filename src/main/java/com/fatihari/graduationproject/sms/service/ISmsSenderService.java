package com.fatihari.graduationproject.sms.service;

import com.fatihari.graduationproject.sms.entity.SmsInfo;

public interface ISmsSenderService
{
    void sendSms(SmsInfo smsInfo);
}
