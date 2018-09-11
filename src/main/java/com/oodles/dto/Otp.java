package com.oodles.dto;

public class Otp {

	private String mobileNumber;
	private String otp;
	private Long expirytime;
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public Long getExpirytime() {
		return expirytime;
	}
	public void setExpirytime(Long expirytime) {
		this.expirytime = expirytime;
	}
}
