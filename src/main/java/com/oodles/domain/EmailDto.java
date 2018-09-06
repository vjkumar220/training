package com.oodles.domain;

import javax.validation.constraints.Email;

public class EmailDto {

	@Email(message = "Enter Valid Email Id")
	private String email;
	private String otp;
	private Long expirytime;
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
	public Long getExpirytime() {
		return expirytime;
	}
	public void setExpirytime(Long expirytime) {
		this.expirytime = expirytime;
	}
	
}
