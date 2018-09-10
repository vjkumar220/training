package com.oodles.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class FiatDeposit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long TransactionId;
	@NotNull
	private String walletType;
	
	@NotNull
    private Long amount;
	 @Enumerated(EnumType.STRING)
	private DepositStatus Status;
	 @OneToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "user_id", nullable = false)
		//@JsonBackReference
		@JsonBackReference(value="user-fiatdeposit")
	 private User user;
	public Long getTransactionId() {
		return TransactionId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setTransactionId(Long transactionId) {
		TransactionId = transactionId;
	}

	public String getWalletType() {
		return walletType;
	}

	public void setWalletType(String walletType) {
		this.walletType = walletType;
	}

	

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public DepositStatus getStatus() {
		return Status;
	}

	public void setStatus(DepositStatus status) {
		Status = status;
	}

	

}
