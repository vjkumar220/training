package com.oodles.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class OtpDto {

	private String mobileNumber;
	@NotNull(message = "Enter the opt")
	@Min(value=4 , message = "Enter the valid otp number")
	@Max(value  = 4 , message ="Enter the valid otp number")
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
