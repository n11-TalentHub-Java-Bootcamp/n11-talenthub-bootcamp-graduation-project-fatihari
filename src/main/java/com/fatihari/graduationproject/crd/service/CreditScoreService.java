package com.fatihari.graduationproject.crd.service;

import com.fatihari.graduationproject.usr.dao.UserAccountRepository;
import com.fatihari.graduationproject.usr.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
public class CreditScoreService implements ICreditScoreService{

    private UserAccountRepository userAccountRepository;

    @Autowired
    public CreditScoreService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccount findByUserId(Long userId) {
        Optional<UserAccount> result = this.userAccountRepository.findById(userId);
        UserAccount userAccount = null;
        if(result.isPresent())
            userAccount = result.get();
        else
            throw new RuntimeException("Did not find user id - " + userId);
        return userAccount;
    }

    @Override
    public int findCreditScore(Long userId)
    {
        // if age of user is equal to 20, so Credit Score is 20 x 25 = 500. (The coefficient is equal to 25.)
        return (Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(findByUserId(userId).getDateOfBirth().toString().substring(0,4))) * 25;
    }
}
