package com.oodles.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class EmailDto {

	@Email(message = "Enter Valid Email Id")
	private String email;
	@NotNull(message = "Enter the opt")
	@Min(value=4 , message = "Enter the valid otp number")
	@Max(value  = 4 , message ="Enter the valid otp number")
	private String otp;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
}
