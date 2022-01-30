package com.fatihari.graduationproject.usr.rest;

import com.fatihari.graduationproject.usr.entity.UserAccount;
import com.fatihari.graduationproject.exc.NotFoundException;
import com.fatihari.graduationproject.usr.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/")
public class UserAccountRestController {
	
	@Autowired
	private IUserAccountService iUserAccountService;

		
	//	quick and dirty: inject user service (use constructor injection)
	@Autowired
	public UserAccountRestController(IUserAccountService iUserAccountService)
	{
		this.iUserAccountService = iUserAccountService;
	}
	
	//  pojo to json
	//	expose "api/users/" and return list of users

	@GetMapping("")
	public List<UserAccount> findAll()
	{
		return iUserAccountService.findAll();
	}

	//	add mapping for GET api/users/{userId}
	@GetMapping("{userId}") //userId parameter
	public UserAccount findById(@PathVariable Long userId)
	{
		UserAccount userAccount = iUserAccountService.findById(userId);
		if(userAccount == null)
			throw new NotFoundException("user id is not found - " + userId);
		return userAccount;
	}
	
	//	add mapping for POST "api/users" - add new user
	@PostMapping("")
	public String save(@RequestBody UserAccount userAccount)
	{
		//	check if the user exists
		UserAccount userDB = this.iUserAccountService.findByIdentificationNumber(userAccount.getIdentificationNumber());

		if(userDB != null)
		{
			return "A user with the specified identification number already exists!";
		}
		else
		{
			//	also just in case they pass id in JSON ... set id to 0
			//	this is to force a save of new item, instead of update.
			userAccount.setId(0L);
			this.iUserAccountService.saveOrUpdate(userAccount);
			return "The user has been successfully saved.";
		}
	}

	//	add mapping for DELETE api/users/{userId} - delete user
	@DeleteMapping({"{userId}"})
	public String delete(@PathVariable Long userId)
	{
		UserAccount userAccount = iUserAccountService.findById(userId);
		if(userAccount == null) //throw exception if null
		{
			throw new NotFoundException("UserId " + userId +  " do not match. ");
		}
		iUserAccountService.deleteById(userId);
		
		return "User with userId '" + userId + "' has been deleted.";
	}

	@PutMapping("")
	public UserAccount updateProduct(@RequestBody UserAccount userAccount)
	{		
		//	also just in case they pass id in JSON ... set id to 0
		//	this is to force a save of new item, instead of update.
		iUserAccountService.saveOrUpdate(userAccount);
		return userAccount;
	}

}
