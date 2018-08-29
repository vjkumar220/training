package com.oodles.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PersonForm {
	@NotNull
    @Size(min=2, max=30)
 
    private String name;

    @NotNull
    @Min(18)
    private Integer age;
    
    @NotNull
    @Pattern(regexp=".+@.+\\.[a-z]+", message="{my.message.key}")
    private String email;
    @NotNull
    @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message="The password should have contain minimum 1 lowercase, maximum 1 uppercase, 1 digit and 1 special character(Minimum Length=8)")
    private String password;
    @NotNull
@Pattern(regexp="^[6-9]\\d{9}$",message="Invalid Mobile Number")
    private String mobile;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String toString() {
        return "Person(Name: " + this.name + ", Age: " + this.email + ", Email: " + this.email + ", Password: " + this.password + ", Mobile: " + this.mobile + ")";
    }
}
