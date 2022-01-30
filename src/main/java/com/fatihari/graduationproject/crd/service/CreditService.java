package com.fatihari.graduationproject.crd.service;

import com.fatihari.graduationproject.crd.dao.CreditRepository;
import com.fatihari.graduationproject.crd.entity.CreditApplication;
import com.fatihari.graduationproject.exc.NotFoundException;
import com.fatihari.graduationproject.usr.dao.UserAccountRepository;
import com.fatihari.graduationproject.usr.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CreditService implements ICreditService{
    private final int CREDIT_LIMIT_MULTIPLIER = 4;
    private UserAccountRepository userAccountRepository;
    private ICreditScoreService iCreditScoreService;
    private CreditRepository creditRepository;

    @Autowired
    public CreditService(UserAccountRepository userAccountRepository, ICreditScoreService iCreditScoreService, CreditRepository creditRepository) {
        this.userAccountRepository = userAccountRepository;
        this.iCreditScoreService = iCreditScoreService;
        this.creditRepository = creditRepository;
    }

    @Override
    public CreditApplication findByIdentificationNumberAndDateOfBirth(String identificationNumber, Date dateOfBirth)
    {
        CreditApplication creditApplication = this.creditRepository.findByUserAccount_IdentificationNumberAndUserAccount_DateOfBirth(identificationNumber, dateOfBirth);
        if(creditApplication==null)
            throw new NotFoundException("Please check your identification number or date of birth.");
        return creditApplication;
    }

    @Override
    public List<CreditApplication> findAll() {
        return this.creditRepository.findAll();
    }

    @Transactional
    @Override
    public CreditApplication save(CreditApplication creditApplication)
    {
        creditApplication.setCreditResult(findCreditResult(creditApplication.getUserAccount().getId())); // setter injection
        creditApplication.setCreditLimit(findCreditLimit(creditApplication.getUserAccount().getId())); // setter injection
        creditApplication.setCreditScore(this.iCreditScoreService.findCreditScore(creditApplication.getUserAccount().getId())); // setter injection
        return this.creditRepository.save(creditApplication);
    }

    @Override
    public String findCreditResult(Long userId) {
        Optional<UserAccount> result = this.userAccountRepository.findById(userId);
        UserAccount userAccount = null;
        if(result.isPresent())
            userAccount = result.get();
        else
            throw new RuntimeException("Did not find user id - " + userId);
        if (iCreditScoreService.findCreditScore(userId) < 500)
        {
            return "Rejection";
        }
        else
            return "Approval";
    }

    @Override
    public Long findCreditLimit(Long userId)
    {
        if (findCreditResult(userId).equals("Rejection"))
            return 0L;
        else
        {
            if(this.iCreditScoreService.findCreditScore(userId)<1000 &&
                    this.iCreditScoreService.findCreditScore(userId)>=500 &&
                        this.iCreditScoreService.findByUserId(userId).getMonthlyIncome()<5000L)
                return 10000L;
            else if(this.iCreditScoreService.findCreditScore(userId)<1000 &&
                        this.iCreditScoreService.findCreditScore(userId)>=500 &&
                            this.iCreditScoreService.findByUserId(userId).getMonthlyIncome()>=5000L &&
                                this.iCreditScoreService.findByUserId(userId).getMonthlyIncome()<10000L)
                return 20000L;
            else if(this.iCreditScoreService.findCreditScore(userId)<1000 &&
                        this.iCreditScoreService.findCreditScore(userId)>=500 &&
                            this.iCreditScoreService.findByUserId(userId).getMonthlyIncome()>=10000L)
                return this.iCreditScoreService.findByUserId(userId).getMonthlyIncome() * CREDIT_LIMIT_MULTIPLIER / 2;
            else
                return this.iCreditScoreService.findByUserId(userId).getMonthlyIncome() * CREDIT_LIMIT_MULTIPLIER;
        }
    }

    @Override
    public CreditApplication findById(Long id) {
        Optional<CreditApplication> result = this.creditRepository.findById(id);
        CreditApplication creditApplication = null;

        if(result.isPresent())
            creditApplication = result.get();
        else
            throw new RuntimeException("Did not find id - " + id);
        return creditApplication;
    }
    @Override
    public CreditApplication findByUserId(Long userId) {
        return this.creditRepository.findByUserAccount_Id(userId);
    }

    @Override
    public void deleteById(Long id)
    {
        this.creditRepository.deleteById(id);
    }

    @Override
    public void deleteByUserId(Long userId)
    {
        CreditApplication creditApplication = findByUserId(userId);
        this.creditRepository.delete(creditApplication);
    }
}
