package com.oodles.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class CryptoWallet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cryptoWalletId;
	private Long shadowBalance;
	private Long balance;
	private String coinName;
	private String walletType;

	@ManyToOne // (fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "for Crypto Wallet")

	private User user;

	public Long getCryptoWalletId() {
		return cryptoWalletId;
	}

	public void setCryptoWalletId(Long cryptoWalletId) {
		this.cryptoWalletId = cryptoWalletId;
	}

	public Long getShadowBalance() {
		return shadowBalance;
	}

	public void setShadowBalance(Long shadowBalance) {
		this.shadowBalance = shadowBalance;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}

	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
