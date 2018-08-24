package com.oodles.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.Users;
import com.oodles.domain.Wallet;
import com.oodles.repository.UserWalletRepository;

@Service
public class UserWalletService {
	public static final Logger logger = LoggerFactory.getLogger(UserWalletService.class);
	@Autowired
	private UserWalletRepository userwalletRepository;
	public List<Wallet> one()
	{
		List<Wallet> cus=userwalletRepository.findAll();
		return cus;
	}
}
