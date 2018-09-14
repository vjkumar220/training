package com.oodles.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oodles.enumeration.DepositStatus;

@Entity
public class CryptoDeposit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cryptoDepositId;

	private String coinType;

	private Double numberOfCoin;

	@Enumerated(EnumType.STRING)
	private DepositStatus depositStatus;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cryptoWallet_id")
	@JsonBackReference(value = "for Crypto Deposit")
	private CryptoWallet cryptoWallet;

	public Long getCryptoDepositId() {
		return cryptoDepositId;
	}

	public void setCryptoDepositId(Long cryptoDepositId) {
		this.cryptoDepositId = cryptoDepositId;
	}

	public String getCoinType() {
		return coinType;
	}

	public void setCoinType(String coinType) {
		this.coinType = coinType;
	}

	public Double getNumberOfCoin() {
		return numberOfCoin;
	}

	public void setNumberOfCoin(Double numberOfCoin) {
		this.numberOfCoin = numberOfCoin;
	}

	public DepositStatus getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(DepositStatus depositStatus) {
		this.depositStatus = depositStatus;
	}

	public CryptoWallet getCryptoWallet() {
		return cryptoWallet;
	}

	public void setCryptoWallet(CryptoWallet cryptoWallet) {
		this.cryptoWallet = cryptoWallet;
	}

}
