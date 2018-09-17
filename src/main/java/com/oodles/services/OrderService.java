package com.oodles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.dto.BuyOrderDto;
import com.oodles.dto.MarketOrderDto;
import com.oodles.dto.OrderDto;
import com.oodles.dto.SellOrderDto;
import com.oodles.enums.OrderStatus;
import com.oodles.enums.OrderType;
import com.oodles.models.BuyOrder;
import com.oodles.models.CryptoCurrency;
import com.oodles.models.CryptoWallet;
import com.oodles.models.FiatWallet;
import com.oodles.models.LimitOrder;
import com.oodles.models.MarketOrder;
import com.oodles.models.SellOrder;
import com.oodles.models.User;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatWalletRepository;
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
	@Autowired
	private FiatWalletRepository fiatWalletRepository;
	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;
	@Autowired
	private CryptoCurrencyRepository cryptoCurrencyRepository;
	
	public Map<String, Object> createLimitOrder(OrderDto orderDTO) {
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

	/**
	 *  Create Market Order
	 * @param marketOrderDTO
	 * @return
	 */
	public Map<String, Object> createMarketOrder(MarketOrderDto marketOrderDTO) {
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
	/**
	 *  Create Buy Order
	 * @param buyOrderDTO
	 * @return
	 */

	public Map<String, Object> createBuyOrder(BuyOrderDto buyOrderDTO) {
		logger.info("createOrder service entered");
		Map<String, Object> result = new HashMap<String, Object>();
		Double amount = (buyOrderDTO.getDesiredPrice());
		Double quantity = buyOrderDTO.getCoinQuantity();
		String coinName = buyOrderDTO.getCoinName();
		Long userId = buyOrderDTO.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if ((quantity > 0)) {
			logger.info("Quantity" + quantity);
			if (user.isPresent()) {
				User foundUser = user.get();
				CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBycoinName(coinName);
				Long fees = cryptoCurrency.getFees();
				logger.info("fees "+fees);
				FiatWallet fwType = fiatWalletRepository.findByUserId(foundUser.getId());
             Double amountNeedToBeDeducted=(((quantity * amount)*(fees/100))+(quantity * amount));
             logger.info("amount "+amountNeedToBeDeducted);
				if (fwType.getShadowBalance() >= amountNeedToBeDeducted)

				{
					logger.info("amount checking done");
                    Double currentShadowBalance=fwType.getShadowBalance();
                    
                    Double updatedShadowBalance=currentShadowBalance-amountNeedToBeDeducted;
                    fwType.setShadowBalance(updatedShadowBalance);
                    fiatWalletRepository.save(fwType);
					BuyOrder newOrder = new BuyOrder();

					newOrder.setCoinName(coinName);
					newOrder.setBuyDesiredPrice(amount);
					newOrder.setCoinQuantity(quantity);
					newOrder.setRemainingCoin(quantity);
					OrderStatus status = newOrder.getStatus();
					newOrder.setStatus(status.PENDING);
					newOrder.setUser(foundUser);
					buyOrderRepository.save(newOrder);
					result.put("responseMessage", "success");
					logger.info("Create order service end");
					return result;
				}
				result.put("responseMessage", "Not sufficient amount");
				return result;
			}
			result.put("responseMessage", "User does not exist");
			return result;

		}
		result.put("responseMessage", "Enter quantity more than zero");
		return result;

	}

	/**
	 *  Create Sell Order
	 * @param sellOrderDTO
	 * @return
	 */

	public Map<String, Object> createSellOrder(SellOrderDto sellOrderDTO) {
		logger.info("createOrder service entered");
		Map<String, Object> result = new HashMap<String, Object>();
		Double amount = sellOrderDTO.getDesiredPrice();
		Double quantity = sellOrderDTO.getCoinQuantity();
		String coinName = sellOrderDTO.getCoinName();
		Long userId = sellOrderDTO.getUserId();
		Optional<User> user = userRepository.findById(userId);
		if ((quantity > 0)) {
			if (user.isPresent()) {

				User foundUser = user.get();
				CryptoWallet fwType = cryptoWalletRepository.findByCoinNameAndUserId(coinName, foundUser.getId());
				if (fwType.getShadowBalance() >= quantity)

				{
					 Double currentShadowBalance=fwType.getShadowBalance();
	                    Double updatedShadowBalance=currentShadowBalance-quantity;
	                    fwType.setShadowBalance(updatedShadowBalance);
	                    cryptoWalletRepository.save(fwType);
					SellOrder newOrder = new SellOrder();
					newOrder.setCoinName(coinName);
					newOrder.setSellDesiredPrice(amount);
					newOrder.setCoinQuantity(quantity);
					newOrder.setRemainingCoin(quantity);
					OrderStatus status = newOrder.getStatus();
					newOrder.setStatus(status.PENDING);
					newOrder.setUser(foundUser);
					sellOrderRepository.save(newOrder);
					result.put("responseMessage", "success");
					logger.info("Create order service end");
					return result;
				}
				result.put("responseMessage", "Not sufficient coin");
				return result;
			}
			result.put("responseMessage", "User does not exist");
			return result;

		}
		result.put("responseMessage", "Enter quantity more than zero");
		return result;
	}

	/**
	 *  Get All Buy Order
	 * @return
	 */
	public List<BuyOrder> retrieveAllBuyOrder() {
		List<BuyOrder> result = buyOrderRepository.findAll();
		return result;
	}
	/**
	 *  Get All Sell Order
	 * @return
	 */

	public List<SellOrder> retrieveAllSellOrder() {
		List<SellOrder> result = sellOrderRepository.findAll();
		return result;
	}

}
