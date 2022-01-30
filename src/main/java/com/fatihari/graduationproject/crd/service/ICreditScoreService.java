package com.fatihari.graduationproject.crd.service;

import com.fatihari.graduationproject.usr.entity.UserAccount;

public interface ICreditScoreService
{
    public UserAccount findByUserId(Long userId);
    public int findCreditScore(Long userId);
}
