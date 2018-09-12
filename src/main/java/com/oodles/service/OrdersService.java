/*package com.oodles.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.currency.CryptoCurrency;
import com.oodles.domain.order.Orders;
import com.oodles.domain.user.User;
import com.oodles.dto.OrdersDto;
import com.oodles.enumeration.OrderStatus;
import com.oodles.enumeration.OrderType;
import com.oodles.repository.CurrencyRepository;
import com.oodles.repository.OrdersRepository;
import com.oodles.repository.UserRepository;

@Service
public class OrdersService {
	Logger logger = LoggerFactory.getLogger(OrdersService.class);
	//@Autowired
	//private OrdersRepository ordersRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CurrencyRepository currencyRepository;

	private Map response = new HashMap<>();

	public Map createOrder(OrdersDto orders) {
		OrderType orderType = orders.getOrderType();
		String coinName = orders.getCoinName();
		Double coinQuantity = orders.getCoinQuantity();
		Double price = orders.getPrice();
		Long userId = orders.getUserId();
		System.out.println(coinName + " " + coinQuantity + " " + " " + price + " " + userId);
		Optional<User> user = userRepository.findById(userId);
		CryptoCurrency cryptoCurrency = currencyRepository.findByCoinName(coinName);
		String cryptoCurrencyName = cryptoCurrency.getCoinName();
		if (coinName != null && coinQuantity > 0 && price > 0 && userId != null) {
			if (user.isPresent()) {
				if (cryptoCurrencyName != null && coinName.equalsIgnoreCase(cryptoCurrencyName)) {
					Orders newOrder = new Orders();
					newOrder.setOrderType(orderType);
					newOrder.setCoinName(cryptoCurrencyName);
					newOrder.setCoinQuantity(coinQuantity);
					newOrder.setOrderStatus(OrderStatus.PENDING);
					newOrder.setPrice(price);
					newOrder.setUser(user.get());
					ordersRepository.save(newOrder);
					response.put("sucess", "Your Order is Placed");
					return response;
				}
				response.put("error", "Currency Not Found");
				return response;
			}
			response.put("error", "User not found");
			return response;
		}
		response.put("error", "Enter valid entries");
		return response;

	}
}
*/