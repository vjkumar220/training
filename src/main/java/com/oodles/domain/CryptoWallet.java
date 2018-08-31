package com.oodles.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class CryptoWallet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cryptoWalletId;
	private Long shadowBalance;
	@NotNull
	private Long balance;
	@NotNull
	private String coinName;
	@NotNull
	private String walletType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	//@JsonBackReference
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
