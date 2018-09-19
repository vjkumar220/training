package com.oodles.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class FiatWallet implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long fiatWalletId;
	private Double shadowBalance;
	@NotNull
	private Double balance;
	@NotNull
	private String coinName;
	@NotNull
	private String walletType;
	
	@OneToMany(mappedBy = "fiatWallet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<FiatWithdraw> fiatWithdraw;
	
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
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Set<FiatWithdraw> getFiatWithdraw() {
		return fiatWithdraw;
	}
	public void setFiatWithdraw(Set<FiatWithdraw> fiatWithdraw) {
		this.fiatWithdraw = fiatWithdraw;
	}
	public Double getShadowBalance() {
		return shadowBalance;
	}
	public void setShadowBalance(Double shadowBalance) {
		this.shadowBalance = shadowBalance;
	}
	

}
