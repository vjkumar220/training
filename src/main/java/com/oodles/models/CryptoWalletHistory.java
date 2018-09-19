package com.oodles.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class CryptoWalletHistory {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long walletHistoryId;
	private Date withdrawTime;
	private Date depositTime;
	@ManyToOne
	@JoinColumn(name="WalletId",nullable=true)
	private CryptoWallet cryptoWallet;
	public Long getWalletHistoryId() {
		return walletHistoryId;
	}
	public void setWalletHistoryId(Long walletHistoryId) {
		this.walletHistoryId = walletHistoryId;
	}
	public Date getWithdrawTime() {
		return withdrawTime;
	}
	public void setWithdrawTime(Date withdrawTime) {
		this.withdrawTime = withdrawTime;
	}
	public Date getDepositTime() {
		return depositTime;
	}
	public void setDepositTime(Date depositTime) {
		this.depositTime = depositTime;
	}
	public CryptoWallet getCryptoWallet() {
		return cryptoWallet;
	}
	public void setCryptoWallet(CryptoWallet cryptoWallet) {
		this.cryptoWallet = cryptoWallet;
	}
	
}
