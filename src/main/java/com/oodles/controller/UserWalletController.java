/*package com.oodles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.oodles.domain.Users;
import com.oodles.domain.Wallet;
import com.oodles.services.UserWalletService;
@Controller
public class UserWalletController {

	@Autowired
private UserWalletService userwalletservice;

    @GetMapping(value = "/all")
    public List<Wallet> getUserContact() {
    	List<Wallet> result =userwalletservice.one();
        return result;
        }
}
*/