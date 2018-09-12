package com.oodles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.DTO.BuyOrderDTO;
import com.oodles.DTO.MarketOrderDTO;
import com.oodles.DTO.OrderDTO;
import com.oodles.DTO.SellOrderDTO;
import com.oodles.models.BuyOrder;
import com.oodles.models.LimitOrder;
import com.oodles.models.MarketOrder;
import com.oodles.models.OrderStatus;
import com.oodles.models.OrderType;
import com.oodles.models.SellOrder;
import com.oodles.models.User;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.LimitOrderRepository;
import com.oodles.repository.MarketOrderRepository;
import com.oodles.repository.SellOrderRepository;
import com.oodles.repository.UserRepository;

@Service
public class OrderService {
	Logger logger = LoggerFactory.getLogger(OrderService.class);
	@Autowired
	private LimitOrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MarketOrderRepository marketOrderRepository;
	@Autowired
	private BuyOrderRepository buyOrderRepository;
	@Autowired
	private SellOrderRepository sellOrderRepository;

	public Map<String, Object> createLimitOrder(OrderDTO orderDTO) {
		logger.info("createOrder service entered");
		Map<String, Object> result = new HashMap<String, Object>();
		OrderType orderType = orderDTO.getOrderType();
		Long amount = orderDTO.getDesiredPrice();
		Long quantity = orderDTO.getCoinQuantity();
		String coinName = orderDTO.getCoinName();
		Long userId = orderDTO.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if ((amount > 0) && (quantity > 0)) {
			if (user.isPresent()) {

				User foundUser = user.get();
				LimitOrder newOrder = new LimitOrder();
				newOrder.setDesiredPrice(amount);
				;
				newOrder.setCoinName(coinName);
				newOrder.setOrderType(orderType);
				newOrder.setCoinQuantity(quantity);
				OrderStatus status = newOrder.getStatus();
				newOrder.setStatus(status.PENDING);
				newOrder.setUser(foundUser);
				orderRepository.save(newOrder);
				result.put("responseMessage", "success");
				logger.info("Create order service end");
				return result;
			}
			result.put("responseMessage", "User does not exist");
			return result;

		}
		result.put("responseMessage", "Enter amount or quantity more than zero");
		return result;
	}

	// Create Market Order
	public Map<String, Object> createMarketOrder(MarketOrderDTO marketOrderDTO) {
		logger.info("createOrder service entered");
		Map<String, Object> result = new HashMap<String, Object>();
		OrderType orderType = marketOrderDTO.getOrderType();
		Long quantity = marketOrderDTO.getCoinQuantity();
		String coinName = marketOrderDTO.getCoinName();
		Long userId = marketOrderDTO.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if ((quantity > 0)) {
			if (user.isPresent()) {

				User foundUser = user.get();
				MarketOrder newOrder = new MarketOrder();
				newOrder.setCoinName(coinName);
				newOrder.setOrderType(orderType);
				newOrder.setCoinQuantity(quantity);
				OrderStatus status = newOrder.getStatus();
				newOrder.setStatus(status.PENDING);
				newOrder.setUser(foundUser);
				marketOrderRepository.save(newOrder);
				result.put("responseMessage", "success");
				logger.info("Create order service end");
				return result;
			}
			result.put("responseMessage", "User does not exist");
			return result;

		}
		result.put("responseMessage", "Enter  quantity more than zero");
		return result;
	}
	// Create Buy Order

	public Map<String, Object> createBuyOrder(BuyOrderDTO buyOrderDTO) {
		logger.info("createOrder service entered");
		Map<String, Object> result = new HashMap<String, Object>();
		Long amount = buyOrderDTO.getDesiredPrice();

		Long quantity = buyOrderDTO.getCoinQuantity();
		String coinName = buyOrderDTO.getCoinName();
		Long userId = buyOrderDTO.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if ((quantity > 0)) {
			if (user.isPresent()) {

				User foundUser = user.get();
				BuyOrder newOrder = new BuyOrder();
				newOrder.setCoinName(coinName);
				newOrder.setDesiredPrice(amount);
				newOrder.setCoinQuantity(quantity);
				OrderStatus status = newOrder.getStatus();
				newOrder.setStatus(status.PENDING);
				newOrder.setUser(foundUser);
				buyOrderRepository.save(newOrder);
				result.put("responseMessage", "success");
				logger.info("Create order service end");
				return result;
			}
			result.put("responseMessage", "User does not exist");
			return result;

		}
		result.put("responseMessage", "Enter  quantity more than zero");
		return result;
	}

	// Create Sell Order

	public Map<String, Object> createSellOrder(SellOrderDTO sellOrderDTO) {
		logger.info("createOrder service entered");
		Map<String, Object> result = new HashMap<String, Object>();
		Long amount = sellOrderDTO.getDesiredPrice();
		Long quantity = sellOrderDTO.getCoinQuantity();
		String coinName = sellOrderDTO.getCoinName();
		Long userId = sellOrderDTO.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if ((quantity > 0)) {
			if (user.isPresent()) {

				User foundUser = user.get();
				SellOrder newOrder = new SellOrder();
				newOrder.setCoinName(coinName);
				newOrder.setDesiredPrice(amount);
				newOrder.setCoinQuantity(quantity);
				OrderStatus status = newOrder.getStatus();
				newOrder.setStatus(status.PENDING);
				newOrder.setUser(foundUser);
				sellOrderRepository.save(newOrder);
				result.put("responseMessage", "success");
				logger.info("Create order service end");
				return result;
			}
			result.put("responseMessage", "User does not exist");
			return result;

		}
		result.put("responseMessage", "Enter  quantity more than zero");
		return result;
	}
	//Get All Buy Order
	public List<BuyOrder> retrieveAllBuyOrder(){
		List<BuyOrder> result = buyOrderRepository.findAll();
		return result;
	}
	//Get All Sell Order
	
	public List<SellOrder> retrieveAllSellOrder(){
		List<SellOrder> result = sellOrderRepository.findAll();
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
