package com.oodles.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Student {
  @Id
  @GeneratedValue
  private Long id;
  @Size(min=2, message="Name should have atleast 2 characters")
  private String name;
  private String passportNumber;
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
}