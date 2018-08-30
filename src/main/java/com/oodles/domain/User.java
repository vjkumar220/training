package com.oodles.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@Entity
	public class User implements Serializable {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		@NotNull
		@Size(min = 2, message = "Name should have atleast 2 characters")
		private String name;
		@NotNull
		@Email(message = "Enter Valid Email Id")
		private String email;
		@NotNull
		@NotBlank(message = "Enter Password")
		// in this regexp checking atleast one upper and lower cases, number, special char 
		@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&].{8,}$", message=" Enter Password Valid password")
		private String Password;
		// In this regexp we checking phone number length 10 and number starting with 6-9
		@NotNull
		@Pattern(regexp ="^[6-9]\\d{9}$")
		private String phoneNumber;
		@NotNull
		@Size(min=2 , message="Enter the valid country name")
		private String country;
	//setters and getters
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
