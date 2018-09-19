package com.oodles.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class FiatWithdraw {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long transactionId;
	
	@NotNull
    private Double amount;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "wallet_id", nullable = false)
	 @JsonBackReference(value="wallet-withdraw")
	    private FiatWallet fiatWallet;

	  @Column(name="timeStamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	   @Temporal(TemporalType.TIMESTAMP)
	private Date withdrawtime;
	public FiatWallet getFiatWallet() {
		return fiatWallet;
	}

	public void setFiatWallet(FiatWallet fiatWallet) {
		this.fiatWallet = fiatWallet;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getWithdrawtime() {
		return withdrawtime;
	}

	public void setWithdrawtime(Date withdrawtime) {
		this.withdrawtime = withdrawtime;
	}

	

	
}
