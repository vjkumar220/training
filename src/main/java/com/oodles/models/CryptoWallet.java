package com.oodles.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "cryptowallet")

public class CryptoWallet {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long walletId;
	private Double balance;
	private Double shadowBalance;
	private String coinName;
	private String walletType;
	 @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
	 //@JsonBackReference
	 @JsonBackReference(value="user-cryptowallet")
	    private User user;
	 
	 //mapping with deposit 
	 @OneToMany(cascade = CascadeType.ALL,
	           fetch = FetchType.EAGER,
	           mappedBy = "cryptowallet")
	  // @JsonManagedReference
	 //  @JsonBackReference(value="user-cryptowallet")
	   private Set<CryptoDeposit> cryptodeposit = new HashSet<>();
	   //mapping with withdraw
	 @OneToMany(cascade = CascadeType.ALL,
	           fetch = FetchType.EAGER,
	           mappedBy = "cryptowallet")
	   private Set<CryptoWithdraw> cryptowithdraw = new HashSet<>();
	 
public CryptoWallet()
{
}
public Long getWalletId() {
	return walletId;
}
public void setWalletId(Long walletId) {
	this.walletId = walletId;
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
public Set<CryptoDeposit> getCryptodeposit() {
	return cryptodeposit;
}
public void setCryptodeposit(Set<CryptoDeposit> cryptodeposit) {
	this.cryptodeposit = cryptodeposit;
}
public Set<CryptoWithdraw> getCryptowithdraw() {
	return cryptowithdraw;
}
public void setCryptowithdraw(Set<CryptoWithdraw> cryptowithdraw) {
	this.cryptowithdraw = cryptowithdraw;
}

}