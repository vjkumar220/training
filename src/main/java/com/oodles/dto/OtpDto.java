package com.oodles.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class OtpDto {
	 @NotNull
	 @Pattern(regexp="^[6-9]\\d{9}$",message="Invalid Mobile Number")
	private String mobileNumber;
	private String otp;
	
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
	

}
