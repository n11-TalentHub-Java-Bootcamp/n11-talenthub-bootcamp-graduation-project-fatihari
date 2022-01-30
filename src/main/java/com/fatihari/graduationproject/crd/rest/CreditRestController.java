package com.fatihari.graduationproject.crd.rest;

import com.fatihari.graduationproject.crd.converter.CreditConverter;
import com.fatihari.graduationproject.crd.service.ICreditScoreService;
import com.fatihari.graduationproject.crd.dto.CreditDTO;
import com.fatihari.graduationproject.crd.entity.CreditApplication;
import com.fatihari.graduationproject.crd.service.ICreditService;
import com.fatihari.graduationproject.exc.NotFoundException;
import com.fatihari.graduationproject.sms.entity.SmsInfo;
import com.fatihari.graduationproject.sms.service.ISmsService;
import com.fatihari.graduationproject.usr.entity.UserAccount;
import com.fatihari.graduationproject.usr.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/*
* NOTE
* By convention, resource names should use exclusively lowercase letters.
* Similarly, dashes (-) are conventionally used in place of underscores (_).
* "credit-applications" is used below as an example for this note.
* And, no abridging! => It is not used as "credit-apps".
* */

@RestController
@RequestMapping("/api/v1/credit-applications/")
public class CreditRestController {

	@Autowired
	private IUserAccountService iUserAccountService;

	@Autowired
	private ICreditScoreService iCreditScoreService;

	@Autowired
	private ICreditService iCreditService;

	@Autowired
	private ISmsService iSmsService;

	//	quick and dirty: inject user service (use constructor injection)
	@Autowired
	public CreditRestController(IUserAccountService iUserAccountService, ICreditScoreService iCreditScoreService, ICreditService iCreditService, ISmsService iSmsService)
	{
		this.iUserAccountService = iUserAccountService;
		this.iCreditScoreService = iCreditScoreService;
		this.iCreditService = iCreditService;
		this.iSmsService = iSmsService;
	}

	@GetMapping(path = {"{identificationNumber}/{dateOfBirth}"})
	public CreditApplication find(@PathVariable("identificationNumber") String identificationNumber, @PathVariable("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth)
	{
		return this.iCreditService.findByIdentificationNumberAndDateOfBirth(identificationNumber, dateOfBirth);
	}

	@PostMapping("")
	public String save(@RequestBody CreditDTO creditDTO)
	{
		CreditApplication creditApplication = CreditConverter.INSTANCE.convertFromCreditDTOtoCredit(creditDTO);
		CreditApplication creditDB = this.iCreditService.findByUserId(creditApplication.getUserAccount().getId());
		UserAccount userDB = this.iUserAccountService.findById(creditApplication.getUserAccount().getId());

		if(creditDB != null)
		{
			return "Your credit application has already been submitted!";
		}
		else
		{
			creditApplication.setId(0L);
			this.iCreditService.save(creditApplication);

			String message = "Hi " + userDB.getFirstName() + " " + userDB.getLastName()
					+",\n\nYour Credit Result: " + this.iCreditService.findCreditResult(creditApplication.getUserAccount().getId())
					+ "\nYour Credit Limit: " + this.iCreditService.findCreditLimit(creditApplication.getUserAccount().getId())+ "TL";

			// After the user's credit application is saved, the application information is sent to the phone as a message.
			SmsInfo smsInfo = new SmsInfo(userDB.getPhone(), message);
			this.iSmsService.sendSms(smsInfo);

			// Confirmation status information and limit message are returned from the endpoint.
			return message;
		}
	}
	@PutMapping("")
	public String update(@RequestBody CreditDTO creditDTO)
	{
		CreditApplication creditApplication = CreditConverter.INSTANCE.convertFromCreditDTOtoCredit(creditDTO);
		CreditApplication creditDB = this.iCreditService.findByUserId(creditApplication.getUserAccount().getId());
		if(creditDB == null)
		{
			return "The user has no credit application! Please apply first. ";
		}
		else{
			this.iCreditService.save(creditApplication);
			return  "Your credit application has been updated."
					+ "\nCredit Result: " + this.iCreditService.findCreditResult(creditApplication.getUserAccount().getId())
					+ "\nCredit Limit: " + this.iCreditService.findCreditLimit(creditApplication.getUserAccount().getId());
		}

	}


	@GetMapping("admins")
	public List<CreditApplication> findAll(){
		return this.iCreditService.findAll();
	}

	@DeleteMapping("admins/{id}")
	public String deleteById(@PathVariable Long id)
	{
		CreditApplication creditApplication = iCreditService.findById(id);
		if(creditApplication == null) //throw exception if null
		{
			throw new NotFoundException("id: " + id +  " do not match. ");
		}
		iCreditService.deleteById(id);

		return "Credit application with id '" + id + "' has been deleted.";
	}

	@DeleteMapping("admins/users/{userId}")
	public String deleteByUserId(@PathVariable Long userId)
	{
		CreditApplication creditApplication = iCreditService.findByUserId(userId);
		if(creditApplication == null) //throw exception if null
		{
			throw new NotFoundException("UserId: " + userId +  " do not match.");
		}
		iCreditService.deleteByUserId(userId);

		return "Credit application with userId '" + userId + "' has been deleted.";
	}
}
