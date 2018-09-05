package com.oodles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.oodles.services.EmailService;

@Controller
public class VerifyEmailController {
@Autowired
private EmailService emailService;


}
