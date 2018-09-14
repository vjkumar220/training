package com.oodles.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.oodles.enumeration.CryptoName;

//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class CryptoWallet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cryptoWalletId;
	private Double shadowBalance;
	private Double balance;
	private String coinName;
	private String walletType;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id")
	@JsonBackReference(value = "for Crypto Wallet")
	private User user;

	@OneToMany(mappedBy = "cryptoWallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CryptoDeposit> cryptoDeposit;

	@OneToMany(mappedBy = "cryptoWallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<CryptoWithdraw> cryptoWithdraw;

	public Long getCryptoWalletId() {
		return cryptoWalletId;
	}

	public void setCryptoWalletId(Long cryptoWalletId) {
		this.cryptoWalletId = cryptoWalletId;
	}

	public Set<CryptoWithdraw> getCryptoWithdraw() {
		return cryptoWithdraw;
	}

	public void setCryptoWithdraw(Set<CryptoWithdraw> cryptoWithdraw) {
		this.cryptoWithdraw = cryptoWithdraw;
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

	public Set<CryptoDeposit> getCryptoDeposit() {
		return cryptoDeposit;
	}

	public void setCryptoDeposit(Set<CryptoDeposit> cryptoDeposit) {
		this.cryptoDeposit = cryptoDeposit;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getShadowBalance() {
		return shadowBalance;
	}

	public void setShadowBalance(Double shadowBalance) {
		this.shadowBalance = shadowBalance;
	}

	public String getCoinName() {
		return coinName;
	}

	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	
}
