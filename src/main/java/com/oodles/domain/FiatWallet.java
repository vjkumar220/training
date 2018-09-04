package com.oodles.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class FiatWallet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long fiatWalletId;
	private Long shadowBalance;
	@NotNull
	private Long balance;
	@NotNull
	private String coinName;
	@NotNull
	private String walletType;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
	@JsonBackReference(value = "for fiat wallet")
	private User user;
	
	public Long getFiatWalletId() {
		return fiatWalletId;
	}
	public void setFiatWalletId(Long fiatWalletId) {
		this.fiatWalletId = fiatWalletId;
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
	
	//setters getters

	
}
