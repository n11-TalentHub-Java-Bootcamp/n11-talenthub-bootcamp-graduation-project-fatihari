package com.fatihari.graduationproject.crd.dto;

import javax.persistence.Column;
import java.util.Date;

public class CreditDTO
{
	private Long id;
    private Long user_account_id;
	private String user_account_identificationNumber;
	private Date user_account_dateOfBirth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUser_account_id() {
		return user_account_id;
	}
	public void setUser_account_id(Long user_account_id) {
		this.user_account_id = user_account_id;
	}

	public String getUser_account_identificationNumber() {
		return user_account_identificationNumber;
	}

	public void setUser_account_identificationNumber(String user_account_identificationNumber) {
		this.user_account_identificationNumber = user_account_identificationNumber;
	}

	public Date getUser_account_dateOfBirth() {
		return user_account_dateOfBirth;
	}

	public void setUser_account_dateOfBirth(Date user_account_dateOfBirth) {
		this.user_account_dateOfBirth = user_account_dateOfBirth;
	}
}
