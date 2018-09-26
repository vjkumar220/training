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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oodles.enums.DepositStatus;

@Entity
public class CryptoDeposit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;

	  @Column(name="timeStamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	   @Temporal(TemporalType.TIMESTAMP)
	private Date deposittime;
	private Double NumberOfCoin;
	 @Enumerated(EnumType.STRING)
		private DepositStatus Status;
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "wallet_id", nullable = false)
	 //@JsonBackReference
	 @JsonBackReference(value="cryptowallet-deposit")
	    
	 private CryptoWallet cryptowallet;
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	
	public Double getNumberOfCoin() {
		return NumberOfCoin;
	}
	public void setNumberOfCoin(Double numberOfCoin) {
		NumberOfCoin = numberOfCoin;
	}
	public DepositStatus getStatus() {
		return Status;
	}
	public void setStatus(DepositStatus status) {
		Status = status;
	}
	public CryptoWallet getCryptowallet() {
		return cryptowallet;
	}
	public void setCryptowallet(CryptoWallet cryptowallet) {
		this.cryptowallet = cryptowallet;
	}
	public Date getDeposittime() {
		return deposittime;
	}
	public void setDeposittime(Date deposittime) {
		this.deposittime = deposittime;
	}
	 
}
