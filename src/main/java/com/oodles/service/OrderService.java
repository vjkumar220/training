package com.oodles.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.BuyOrder;
import com.oodles.domain.CryptoWallet;
import com.oodles.domain.FiatWallet;
import com.oodles.domain.SellOrder;
import com.oodles.domain.User;
import com.oodles.dto.BuyOrderDto;
import com.oodles.dto.SellOrderDto;
import com.oodles.enumeration.CryptoName;
import com.oodles.enumeration.OrderStatus;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.SellOrderRepository;
import com.oodles.repository.UserRepository;

@Service
public class OrderService {

	Logger log = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private BuyOrderRepository buyOrderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;

	@Autowired
	private SellOrderRepository sellOrderRepository;

	@Autowired
	private FiatWalletRepository fiatWalletRepository;

	/**
	 * This function is working for placing buy order
	 */

	public String buyOrder(BuyOrderDto buyOrderDto) {
		CryptoName coinNameDto = buyOrderDto.getCoinName();
		Double coinQuantity = buyOrderDto.getCoinQuantity();
		Double buyPrice = buyOrderDto.getBuyDesiredPrice();
		Long userId = buyOrderDto.getUserId();
		Long fiatWalletId = buyOrderDto.getFiatWalletId();
		Optional<User> findUser = userRepository.findById(userId);
		if (findUser.isPresent()) {
			log.info("User is verified");
			User user = findUser.get();
			Optional<FiatWallet> findWallet = fiatWalletRepository.findById(fiatWalletId);
			if (findWallet.isPresent()) {
				log.info("wallet found");
				FiatWallet fiatWallet = findWallet.get();
				Double shadowBalance = fiatWallet.getShadowBalance();
				Double orderPrice = (buyPrice * coinQuantity);
				CryptoWallet findUserAndCoin = cryptoWalletRepository.findByCoinNameAndUserId(coinNameDto.toString(),
						userId);
				if (findUserAndCoin != null) {
					log.info("Coin and user find");
					if (shadowBalance >= orderPrice) {
						log.info(coinNameDto.toString());
						Double updateShadowBalance = (shadowBalance - orderPrice);
						BuyOrder buyOrder = new BuyOrder(OrderStatus.PENDING, buyPrice, coinNameDto.toString(),
								coinQuantity, orderPrice, user);
						fiatWallet.setShadowBalance(updateShadowBalance);
						fiatWalletRepository.save(fiatWallet);
						buyOrderRepository.save(buyOrder);
						return "Your Buy Order is Placed";
					}
					return "Your Account is not sufficent amount to place order";
				}
				return "Coin Not Found";
			}
			return "Fiat Wallet Not Found";
		}
		return "User Not Found";

	}
	
	/**
	 * This function is working for creating sell order
	 * @param sellOrderDto
	 * @return
	 */

	public String sellOrder(SellOrderDto sellOrderDto) {
		CryptoName coinName = sellOrderDto.getCoinName();
		Double coinQuantity = sellOrderDto.getCoinQuantity();
		Double sellPrice = sellOrderDto.getSellDesiredPrice();
		Long userId = sellOrderDto.getUserId();
		Long cryptoWalletId = sellOrderDto.getCryptoWalletId();
		Optional<User> findUser = userRepository.findById(userId);
		if (findUser.isPresent()) {
			User user = findUser.get();
			Optional<CryptoWallet> findWallet = cryptoWalletRepository.findById(cryptoWalletId);
			if (findWallet.isPresent()) {
				CryptoWallet cryptoWallet = findWallet.get();
				Double shadowBalance = cryptoWallet.getShadowBalance();
				CryptoWallet findUserAndCoin = cryptoWalletRepository.findByCoinNameAndUserId(coinName.toString(),
						userId);
				if (findUserAndCoin != null) {
					Double orderPrice = (sellPrice * coinQuantity);
					if(shadowBalance >= coinQuantity) {
						Double updatedShadowBalance = (shadowBalance - coinQuantity);
						SellOrder sellOrder = new SellOrder();
						sellOrder.setOrderPrice(orderPrice);
						sellOrder.setSellCoinName(coinName.toString());
						sellOrder.setSellCoinQuantity(coinQuantity);
						sellOrder.setSellOrderStatus(OrderStatus.PENDING);
						sellOrder.setSellPrice(sellPrice);
						sellOrder.setUser(user);
						cryptoWallet.setShadowBalance(updatedShadowBalance);
						cryptoWalletRepository.save(cryptoWallet);
						sellOrderRepository.save(sellOrder);
						return "Your sell Order is generated";
					}
					return "Low Balance in your crypto wallet ";
				}
				return "Coin not found";
			}
			return "Crypto Wallet is not found";
		}
		return "User Not Found";

	}
}
