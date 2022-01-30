package com.fatihari.graduationproject.crd.dao;

import com.fatihari.graduationproject.crd.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface CreditRepository extends JpaRepository<CreditApplication, Long>
{
    CreditApplication findByUserAccount_Id(Long userId);
    CreditApplication findByUserAccount_IdentificationNumberAndUserAccount_DateOfBirth(String identificationNumber, Date dateOfBirth);
}
