package com.fatihari.graduationproject.usr.dao;

import com.fatihari.graduationproject.usr.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long>
{
    UserAccount findByIdentificationNumber(String identificationNumber);
}
