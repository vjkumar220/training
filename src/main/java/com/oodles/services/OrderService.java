package com.oodles.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.models.Order;
import com.oodles.models.OrderDTO;
import com.oodles.models.OrderStatus;
import com.oodles.models.OrderType;
import com.oodles.models.User;
import com.oodles.repository.OrderRepository;
import com.oodles.repository.UserRepository;

@Service
public class OrderService {
	Logger logger=LoggerFactory.getLogger(OrderService.class);
@Autowired
private OrderRepository orderRepository;
@Autowired
private UserRepository userRepository;

public Map<String, Object>createOrder(OrderDTO orderDTO) {
	logger.info("createOrder service entered");
	Map<String, Object> result = new HashMap<String, Object>();
	OrderType orderType=orderDTO.getOrderType();
	Long amount=orderDTO.getAmount();
	Long quantity=orderDTO.getCoinQuantity();
	String coinName=orderDTO.getCoinName();
	Long userId=orderDTO.getUserId();
		Optional<User>user=userRepository.findById(userId);
	if((amount>0) && (quantity>0))
	{
	if(user.isPresent())
	{
	
		User foundUser=user.get();
		Order newOrder=new Order();
		newOrder.setAmount(amount);
		newOrder.setCoinName(coinName);
		newOrder.setOrderType(orderType);
		newOrder.setCoinQuantity(quantity);
		 OrderStatus status= newOrder.getStatus();
		newOrder.setStatus(status.PENDING);
		newOrder.setUser(foundUser);
		orderRepository.save(newOrder);
		result.put("responseMessage", "success");
		logger.info("Create order service end");
}
	
}return result; }
}