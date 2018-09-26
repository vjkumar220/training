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
public class CryptoWithdraw {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long transactionId;
	
	@NotNull
    private Double quantity;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "wallet_id", nullable = false)
	 @JsonBackReference(value="cryptowallet-withdraw")
	    private CryptoWallet cryptowallet;

	  @Column(name="timeStamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	   @Temporal(TemporalType.TIMESTAMP)
	private Date withdrawtime;
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public CryptoWallet getCryptowallet() {
		return cryptowallet;
	}

	public void setCryptowallet(CryptoWallet cryptowallet) {
		this.cryptowallet = cryptowallet;
	}

	public Date getWithdrawtime() {
		return withdrawtime;
	}

	public void setWithdrawtime(Date withdrawtime) {
		this.withdrawtime = withdrawtime;
	}

}
