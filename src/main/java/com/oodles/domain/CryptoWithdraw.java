package com.oodles.domain;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
@Entity
public class CryptoWithdraw {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long withdrawId;

	@NotNull
	@Min(value = 0, message = " Enter amount is greater than zero")
	private Double coinCointain;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date withdrawOn;
	
	@NotNull
	private  String coinName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cryptoWallet_id")
	@JsonBackReference(value = "for crypto Deposit")
	private CryptoWallet cryptoWallet;

	public Long getWithdrawId() {
		return withdrawId;
	}

	public void setWithdrawId(Long withdrawId) {
		this.withdrawId = withdrawId;
	}

	public Double getCoinCointain() {
		return coinCointain;
	}

	public void setCoinCointain(Double coinCointain) {
		this.coinCointain = coinCointain;
	}

	public Date getWithdrawOn() {
		return withdrawOn;
	}

	public void setWithdrawOn(Date withdrawOn) {
		this.withdrawOn = withdrawOn;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public CryptoWallet getCryptoWallet() {
		return cryptoWallet;
	}

	public void setCryptoWallet(CryptoWallet cryptoWallet) {
		this.cryptoWallet = cryptoWallet;
	}

}
