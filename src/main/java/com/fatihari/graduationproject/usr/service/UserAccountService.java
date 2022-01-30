package com.fatihari.graduationproject.usr.service;

import com.fatihari.graduationproject.usr.dao.UserAccountRepository;
import com.fatihari.graduationproject.usr.entity.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserAccountService implements IUserAccountService
{
	private UserAccountRepository userAccountRepository;
	
	@Autowired
	public UserAccountService(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}
	
	@Override
	public List<UserAccount> findAll()
	{
		return this.userAccountRepository.findAll();
	}
	
	@Override
	public UserAccount findById(Long userId) {
		Optional<UserAccount> result = this.userAccountRepository.findById(userId);
		UserAccount userAccount = null;
		
		if(result.isPresent())
			userAccount = result.get();
		else
			throw new RuntimeException("Did not find user id - " + userId);
		return userAccount;
	}

	@Override
	public UserAccount findByIdentificationNumber(String identificationNumber) {
		return this.userAccountRepository.findByIdentificationNumber(identificationNumber);
	}

	@Transactional
	@Override
	public UserAccount saveOrUpdate(UserAccount userAccount)
	{
		return this.userAccountRepository.save(userAccount);
	}

	@Transactional
	@Override
	public void deleteById(Long userId)
	{
		this.userAccountRepository.deleteById(userId);
	}


	
}
