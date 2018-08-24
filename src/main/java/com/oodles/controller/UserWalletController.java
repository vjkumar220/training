package com.oodles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.oodles.domain.Users;
import com.oodles.domain.Wallet;
import com.oodles.repository.UserWalletRepository;
@Controller
public class UserWalletController {
	@Autowired
	private UserWalletRepository userwalletRepository;

    public UserWalletController(UserWalletRepository userwalletRepository) {
        this.userwalletRepository = userwalletRepository;
    }

    @GetMapping(value = "/all")
    public List<Wallet> getUserContact() {
        return userwalletRepository.findAll();
    }
}