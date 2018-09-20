package com.oodles.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oodles.enums.DepositStatus;

@Entity
public class FiatDeposit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	@NotNull
	private String walletType;
	
	@NotNull
    private Double amount;
	
	 @Enumerated(EnumType.STRING)
	private DepositStatus Status;
	 

	  @Column(name="timeStamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	   @Temporal(TemporalType.TIMESTAMP)
	private Date deposittime;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	 @JsonBackReference(value="user-deposit")
	    private User user;
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	

	

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public DepositStatus getStatus() {
		return Status;
	}

	public void setStatus(DepositStatus status) {
		Status = status;
	}

	public Date getDeposittime() {
		return deposittime;
	}

	public void setDeposittime(Date deposittime) {
		this.deposittime = deposittime;
	}

	

}
