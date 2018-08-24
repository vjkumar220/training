package com.student.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Withdraw {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long withdrawId;

	private String withdrawName;

	private String witdrawValue;

	@Temporal(TemporalType.TIMESTAMP)
	private Date withdrawAt;

	@ManyToMany(mappedBy = "withdraw")
	private Set<Deposit> deposit;

	public Withdraw() {
	}

	public Withdraw(String withdrawName, String withdrawValue) {
		this.withdrawName = withdrawName;
		this.witdrawValue = withdrawValue;
	}

	public Withdraw(String withdrawName, String withdrawValue, Set<Deposit> deposit) {
		this.withdrawName = withdrawName;
		this.witdrawValue = withdrawValue;
		this.deposit = deposit;
	}

	public Long getwithdrawId() {
		return withdrawId;
	}

	public void setwithdrawId(Long withdrawId) {
		this.withdrawId = withdrawId;
	}

	public String getWithdrawName() {
		return withdrawName;
	}

	public void setWithdrawName(String withdrawName) {
		this.withdrawName = withdrawName;
	}

	public String getWitdrawValue() {
		return witdrawValue;
	}

	public void setWitdrawValue(String witdrawValue) {
		this.witdrawValue = witdrawValue;
	}

	public Date getWithdrawAt() {
		return withdrawAt;
	}

	public void setWithdrawAt(Date withdrawAt) {
		this.withdrawAt = withdrawAt;
	}

}
