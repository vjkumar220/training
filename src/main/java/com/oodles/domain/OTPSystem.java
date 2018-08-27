package com.oodles.domain;

public class OTPSystem {
private String mobilenumber;
private String otp;
private Long expirytime;
public String getMobilenumber() {
	return mobilenumber;
}
public void setMobilenumber(String mobilenumber) {
	this.mobilenumber = mobilenumber;
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
