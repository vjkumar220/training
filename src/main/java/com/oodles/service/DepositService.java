package com.oodles.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.FiatDeposit;
import com.oodles.domain.OrderStatus;
import com.oodles.domain.User;
import com.oodles.dto.FiatDepositDto;
import com.oodles.repository.FiatDepositRepository;
import com.oodles.repository.UserRepository;

@Service
public class DepositService {
	Logger log = LoggerFactory.getLogger(DepositService.class);
	@Autowired
	FiatDepositRepository fiatDepositRepository;
	
	@Autowired
	UserRepository userRepository;
	
	private  Map result= new HashMap<>();
	
	//Fiat deposit request generate 
	public Map fiatDeposit(FiatDepositDto depositDto) {
		log.info("In deposit service");
//		Long userId = depositDto.getUserId();
//		log.info("user value", userId);
//		Double amount  = depositDto.getAmount();
		
		String userId = depositDto.getUserId();
		String amount1 = depositDto.getAmount();
		log.info("user amount ",amount1);
		Optional<User> findUser = userRepository.findById(Long.parseLong(userId));
		log.info("user find", findUser);
		if(findUser.isPresent()) {
			log.info("in if deposit service");
			FiatDeposit deposit = new FiatDeposit();
			deposit.setAmount(Double.parseDouble(amount1));
			deposit.setUser(findUser.get());
			deposit.setDepositStatus(OrderStatus.PENDING);
			fiatDepositRepository.save(deposit);
			log.info("request save");
			result.put("success", "Your request genrated");
			return result;
		}
		log.info("req not sAVED");
		result.put("error", "No user found");
		return result;
	}

}
