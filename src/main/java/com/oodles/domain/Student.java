package com.oodles.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Student {
  @Id
  @GeneratedValue
  private Long id;
  @Size(min=2, message="Name should have atleast 2 characters")
  private String name;
  private String passportNumber;
  @NotNull
  @Min(18)
  private Integer age;
  
  @NotNull
  @Pattern(regexp=".+@.+\\.[a-z]+", message="{my.message.key}")
  private String email;
  @NotNull
  @Pattern(regexp="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message="The password should have contain minimum 1 lowercase, maximum 1 uppercase, 1 digit and 1 special character(Minimum Length=8)")
  private String password;
@Pattern(regexp="^[6-9]\\d{9}$",message="Invalid Mobile Number")
  private String mobile;

  public Student() {
    super();
  }
  public Student(Long id, String name, String passportNumber) {
    super();
    this.id = id;
    this.name = name;
    this.passportNumber = passportNumber;
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
  public String getPassportNumber() {
    return passportNumber;
  }
  public void setPassportNumber(String passportNumber) {
    this.passportNumber = passportNumber;
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
  
}