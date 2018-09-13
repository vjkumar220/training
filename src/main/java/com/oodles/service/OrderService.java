package com.oodles.service;

import java.util.Map;
import java.util.Optional;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.order.BuyOrder;
import com.oodles.domain.order.SellOrder;
import com.oodles.domain.user.User;
import com.oodles.domain.wallet.CryptoWallet;
import com.oodles.dto.OrderDto;
import com.oodles.enumeration.CryptoName;
import com.oodles.enumeration.OrderStatus;
import com.oodles.enumeration.OrderType;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.SellOrderRepository;
import com.oodles.repository.UserRepository;

@Service
public class OrderService {
	@Autowired
	private BuyOrderRepository buyOrderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;
	
	@Autowired
	private SellOrderRepository sellOrderRepository;

	private Map result = null;

	// Buy Order request

	public String createBuyOrder(OrderDto buyOrderDto) {
		CryptoName coinName = buyOrderDto.getCoinName();
		Double coinQuantity = buyOrderDto.getCoinQuantity();
		Double price = buyOrderDto.getPrice();
		Long userIdDto = buyOrderDto.getUserId();
		Long walletIdDto = buyOrderDto.getWalletId();
		Optional<User> findUser = userRepository.findById(userIdDto);
		if (findUser.isPresent()) {
			User user = findUser.get();
			Long userId = user.getId();
			CryptoWallet findCurrency = cryptoWalletRepository.findByCryptoWalletIdAndUserId(walletIdDto, userIdDto);
			if (findCurrency != null) {
				BuyOrder buyOrder = new BuyOrder();
				buyOrder.setBuyCoinName(coinName);
				buyOrder.setBuyCoinQuantity(coinQuantity);
				buyOrder.setBuyOrderStatus(OrderStatus.PENDING);
				buyOrder.setBuyPrice(price);
				buyOrderRepository.save(buyOrder);
				return "Your Buy Order is genrated";
			}
			return "Coin Not Present";
		}
		return "User Not Found";
	}

	// Sell Order Request

/*	public String createSellOrder(OrderDto sellOrderDto) {
		String coinName = sellOrderDto.getCoinName();
		Double coinQuantity = sellOrderDto.getCoinQuantity();
		Double price = sellOrderDto.getPrice();
		Long userIdDto = sellOrderDto.getUserId();
		Optional<User> findUser = userRepository.findById(userIdDto);
		if (findUser.isPresent()) {
			User user = findUser.get();
			Long userId = user.getId();
			CryptoWallet findCurrency = cryptoWalletRepository.findByCoinNameAndUserId(coinName, userId);
			if (findCurrency != null) {
				SellOrder order = new SellOrder();
				order.setSellCoinName(coinName);
				order.setSellCoinQuantity(coinQuantity);
				order.setSellOrderStatus(OrderStatus.PENDING);
				order.setSellPrice(price);
				order.setUser(user);
				sellOrderRepository.save(order);
			}return "Coin Not Present";
		}return "User Not Found";
	}*/
}
