package com.oodles.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
	@Size(min=2, message="Name should have atleast 2 characters")
    private String name;
	 @NotNull
	  @Pattern(regexp=".+@.+\\.[a-z]+", message="Invalid Mail")
       private String email;
	  @NotNull
	 @Pattern(regexp="^[6-9]\\d{9}$",message="Invalid Mobile Number")
    private String mobilenumber;
    @NotNull
	  @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message="The password should have contain minimum 1 lowercase, maximum 1 uppercase, 1 digit and 1 special character(Minimum Length=8)")
    
    private String password;
   @NotNull
    private String country;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getMobilenumber() {
	return mobilenumber;
}
public void setMobilenumber(String mobilenumber) {
	this.mobilenumber = mobilenumber;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}	
   
   
   
}
