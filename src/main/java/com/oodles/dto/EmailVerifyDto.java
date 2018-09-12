package com.oodles.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EmailVerifyDto {
	
	@Email(message = "Enter Valid Email Id")
	private String email;
	private String otp;
	private Long expirytime;
	@NotNull(message = "Enter the password")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&].{8,}$", message = " Enter Password Valid password")
	private String password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
