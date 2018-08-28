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
	    
	    
	    @Pattern(regexp=".+@.+\\.[a-z]+", message="{my.message.key}")
	    private String email;


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

		public String toString() {
	        return "Person(Name: " + this.name + ", Age: " + this.email + ", Email: " + this.email + ")";
	    }
	}
