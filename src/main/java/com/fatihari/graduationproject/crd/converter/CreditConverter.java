package com.fatihari.graduationproject.crd.converter;

import com.fatihari.graduationproject.crd.dto.CreditDTO;
import com.fatihari.graduationproject.crd.entity.CreditApplication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditConverter
{
	public CreditConverter INSTANCE = Mappers.getMapper(CreditConverter.class);
	
	@Mapping(source="user_account_id", target = "userAccount.id") // source => creditDTO, target=> CreditApplication
	@Mapping(source="user_account_identificationNumber", target = "userAccount.identificationNumber") // source => creditDTO, target=> CreditApplication
	@Mapping(source="user_account_dateOfBirth", target = "userAccount.dateOfBirth") // source => creditDTO, target=> CreditApplication
	CreditApplication convertFromCreditDTOtoCredit(CreditDTO creditDTO);
}
