package com.oodles.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
	
	@NotNull(message = "Enter the name")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	@Pattern(regexp = "[A-Za-z]+" , message =  "Only enter the characters in the name")
	private String name;
	@NotNull(message = "Enter the email")
	@Email(message = "Enter Valid Email Id")
	private String email;

	/**
	 * in this regexp checking atleast one upper and lower cases, number, special symbol
	 * char
	 */
	@NotNull(message = "Enter the atlest 8 character and Enter the atlest one upper case , lower case , special symbol and number ")
	@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = " Enter Password Valid password")
	private String Password;

	/**
	 * In this regexp we checking phone number length 10 and number starting with
	 * 6-9
	 */
	@NotNull(message = "Enter the phone number")
	@Pattern(regexp = "^[6-9]\\d{9}$")
	private String phoneNumber;
	@NotNull(message = "Enter the country")
	@Size(min = 2, message = "Enter the valid country name")
	@Pattern(regexp = "[A-Za-z]+" , message =  "Only enter the characters in the name")
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
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

}
