package com.fatihari.graduationproject.usr.service;

import com.fatihari.graduationproject.usr.entity.UserAccount;
import java.util.List;

public interface IUserAccountService 
{
	public List<UserAccount> findAll();
	public UserAccount findById(Long id);
	public UserAccount findByIdentificationNumber(String identificationNumber);
	public UserAccount saveOrUpdate(UserAccount userAccount);
	public void deleteById(Long userId);
}
