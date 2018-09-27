package com.oodles.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.CryptoCurrency;
import com.oodles.models.SellOrder;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.SellOrderRepository;

@Service
public class AdminSupplyService {
	@Autowired
	private CryptoCurrencyRepository cryptoCurrencyRepository;
		@Autowired
		private SellOrderRepository sellOrderRepository;
	public Map<String, Object> SupplyUpdation() {
		Map<String, Object> result = new HashMap<String, Object>();
		Long userId=(long) 2;
		
		SellOrder newWalletType = sellOrderRepository.findByUserId(userId);
		String coinName=newWalletType.getCoinName();
		Double remainingcoin=newWalletType.getRemainingCoin();
		CryptoCurrency cryptoname = cryptoCurrencyRepository.findBycoinName(coinName);
        if(cryptoname!=null)
        {
        	cryptoname.setInitialSupply(remainingcoin);
		cryptoCurrencyRepository.save(cryptoname);
        }
		return result;
	}
}
