package com.fatihari.graduationproject.usr.entity;

import javax.persistence.*;
import java.util.Date;


/* "user" is a reserved word in postgreSql and it is generally not a good idea to use reserved words for identifiers (tables or columns). 
 * Therefore, "user_account" is preferred as the table name.
 */
@Entity
@Table(name = "user_account") 
public class UserAccount 
{
	@SequenceGenerator(name = "generator", sequenceName = "user_account_id_seq")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    private Long id;

	@Column(length = 10, name = "identification_number", nullable = false) // For security reasons, the ID number will be 10 digits instead of 11 digits.
	private String identificationNumber;

	@Column(length = 50, name = "first_name", nullable = false)
	private String firstName;
	
	@Column(length = 50, name = "last_name", nullable = false)
	private String lastName;
	
    @Column(name = "monthly_income", nullable = false)
    private Long monthlyIncome;

	@Column(length = 15, name = "phone", nullable = false)
	private String phone;

	@Column(name = "date_of_birth", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Long monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "UserAccount{" +
				"id=" + id +
				", identificationNumber='" + identificationNumber + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", monthlyIncome=" + monthlyIncome +
				", phone='" + phone + '\'' +
				", dateOfBirth=" + dateOfBirth +
				'}';
	}
}
