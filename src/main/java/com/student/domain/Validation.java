package com.student.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Pattern.Flag;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
public class Validation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Enter Name")
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	
	@NotBlank(message = "Enter Age")
	@Min (18)
	private String age;

	@NotBlank(message = "Enter Adhar Number")
	@Size(min = 15, max = 15, message = "Add Valid Adhar Number")
	private String adharNo;

	@NotBlank(message = "{Enter the Email Id}")
	@Email(message = "Enter Valid Email Id")
	private String email;

	@NotBlank(message = "Enter Password")
	@Pattern(regexp = "^(?=.*\\d).{4,8}$", flags = Flag.UNICODE_CASE)
	private String Password;
	

	public Validation() {
		super();
	}

	

	public Validation(Long id,
			@NotBlank(message = "Enter Name") @Size(min = 2, message = "Name should have atleast 2 characters") String name,
			@Digits(fraction = 0, integer = 0) @NotBlank(message = "Enter Age") @Min(18) String age,
			@NotBlank(message = "Enter Adhar Number") @Size(min = 15, max = 15, message = "Add Valid Adhar Number") String adharNo,
			@NotBlank(message = "Enter the Email Id") @Email(message = "Enter Valid Email Id") String email,
			@NotBlank(message = "Enter Password") @Pattern(regexp = "^(?=.*\\d).{4,8}$", flags = Flag.UNICODE_CASE) String password) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.adharNo = adharNo;
		this.email = email;
		Password = password;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
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

}
