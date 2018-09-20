package com.oodles.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProfitBook {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long bookID;
	private Double EarnedAmount;
	public Long getBookID() {
		return bookID;
	}
	public void setBookID(Long bookID) {
		this.bookID = bookID;
	}
	public Double getEarnedAmount() {
		return EarnedAmount;
	}
	public void setEarnedAmount(Double earnedAmount) {
		EarnedAmount = earnedAmount;
	}
	
}
