package com.oodles;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oodles.models.User;

public class PasswordValidationTest {
	private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testInvalidPassword() {
        User userRegistration = new User();
        userRegistration.setName("Shubham");
        userRegistration.setCountry("India");
        userRegistration.setEmail("info@memorynotfound.com");
        userRegistration.setMobilenumber("9876543212");
        userRegistration.setPassword("pasW@123");
       
      

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(userRegistration);

        Assert.assertEquals(constraintViolations.size(), 2);
    }

    @Test
    public void testValidPasswords() {
        User userRegistration = new User();
        userRegistration.setName("Shubham");
        userRegistration.setCountry("India");
        userRegistration.setEmail("info@memorynotfound.com");
        userRegistration.setMobilenumber("9876543212");
        userRegistration.setPassword("pasW@123");

        Set<ConstraintViolation<User>> constraintViolations = validator.validate(userRegistration);

        Assert.assertEquals(constraintViolations.size(), 0);
    }
}
