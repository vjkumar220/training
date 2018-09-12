package com.oodles.domain.withdraw;

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
import com.oodles.domain.wallet.FiatWallet;

@Entity
public class FiatWithdraw {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long withdrawId;

	@NotNull
	@Min(value = 0, message = " Enter amount is greater than zero")
	private Double amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date withdrawOn;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fiatWallet_id")
	@JsonBackReference(value = "for fiat Deposit")
	private FiatWallet fiatWallet;

	public Long getWithdrawId() {
		return withdrawId;
	}

	public void setWithdrawId(Long withdrawId) {
		this.withdrawId = withdrawId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getWithdrawOn() {
		return withdrawOn;
	}

	public void setWithdrawOn(Date withdrawOn) {
		this.withdrawOn = withdrawOn;
	}

	public FiatWallet getFiatWallet() {
		return fiatWallet;
	}

	public void setFiatWallet(FiatWallet fiatWallet) {
		this.fiatWallet = fiatWallet;
	}

}
