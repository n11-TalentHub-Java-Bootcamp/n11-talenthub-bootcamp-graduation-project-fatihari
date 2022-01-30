package com.fatihari.graduationproject.crd.entity;

import com.fatihari.graduationproject.usr.entity.UserAccount;

import javax.persistence.*;

@Entity
@Table(name = "credit_application")
//@JsonIgnoreProperties({"userAccount"})
public class CreditApplication
{
	@SequenceGenerator(name = "generator", sequenceName = "credit_id_seq")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", nullable = false)
    private Long id;

	@Column(name = "credit_score")
	private int creditScore;

	@Column(length = 10 ,name = "credit_result") 	//Approval and Rejection
    private String creditResult;

    @Column(name = "credit_limit")
    private Long creditLimit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_account_id", foreignKey = @ForeignKey(name = "fk_credit_user_account_id"))
    private UserAccount userAccount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

	public String getCreditResult() {
		return creditResult;
	}

	public void setCreditResult(String creditResult) {
		this.creditResult = creditResult;
	}

	public Long getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Long creditLimit) {
		this.creditLimit = creditLimit;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String toString() {
		return "CreditApplication{" +
				"id=" + id +
				", creditScore=" + creditScore +
				", creditResult='" + creditResult + '\'' +
				", creditLimit=" + creditLimit +
				", userAccount=" + userAccount +
				'}';
	}
}
