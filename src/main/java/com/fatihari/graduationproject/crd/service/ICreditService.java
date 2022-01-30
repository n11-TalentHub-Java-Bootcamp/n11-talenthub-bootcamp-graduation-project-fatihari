package com.fatihari.graduationproject.crd.service;

import com.fatihari.graduationproject.crd.entity.CreditApplication;

import java.util.Date;
import java.util.List;

public interface ICreditService
{
    public List<CreditApplication> findAll();
    public CreditApplication save(CreditApplication creditApplication);
    public String findCreditResult(Long userId);
    public Long findCreditLimit(Long userId);
    public CreditApplication findById(Long id);
    public CreditApplication findByUserId(Long userId);
    public CreditApplication findByIdentificationNumberAndDateOfBirth(String identificationNumber, Date dateOfBirth);
    void deleteById(Long id);
    public void deleteByUserId(Long userId);

}
