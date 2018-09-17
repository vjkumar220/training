package com.oodles.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.enums.OrderStatus;
import com.oodles.models.BuyOrder;
import com.oodles.models.CryptoCurrency;
import com.oodles.models.SellOrder;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.SellOrderRepository;

@Service
public class OrderMatchingService {
	private Logger logger = LoggerFactory.getLogger(OrderMatchingService.class);
	@Autowired
	private BuyOrderRepository buyOrderRepository;
	@Autowired
	private SellOrderRepository sellOrderRepository;
	@Autowired
	private CryptoCurrencyRepository cryptoCurrencyRepository;

	public List<BuyOrder> buyList() {
		List<BuyOrder> result = buyOrderRepository.findAllByStatus(OrderStatus.PENDING);
		System.out.println(result);
		Collections.sort(result, new Comparator<BuyOrder>() {
			@Override
			public int compare(BuyOrder o1, BuyOrder o2) {
				return o1.getBuyDesiredPrice().compareTo(o2.getBuyDesiredPrice());
			}
		});
		return result;
	}

	public List<SellOrder> sellList() {
		List<SellOrder> result = sellOrderRepository.findAllByStatus(OrderStatus.PENDING);

		for (SellOrder sellOrder : result) {
			System.out.println("List" + sellOrder);
		}

		Collections.sort(result, new Comparator<SellOrder>() {
			@Override
			public int compare(SellOrder o1, SellOrder o2) {
				return o1.getSellDesiredPrice().compareTo(o2.getSellDesiredPrice());
			}
		});
		Collections.reverse(result);

		return result;
	}

	public List orderMatch() {
		List<BuyOrder> buyOrder = new OrderMatchingService().buyList();

		List<SellOrder> sellOrder = new OrderMatchingService().sellList();
		Double coinPrice;
		Double grossAmount;

		if (buyOrder == null || sellOrder == null) {

			return null;
		} else {
			// ArrayList<Object> appproved = new ArrayList<>();
			Iterator<BuyOrder> itrateBuyOrder = buyOrder.iterator();
			Iterator<SellOrder> itrateSellOrder = sellOrder.iterator();
			while (itrateBuyOrder.hasNext() && itrateSellOrder.hasNext()) {

				// Checking coin name

				if (itrateBuyOrder.next().getCoinName() == itrateSellOrder.next().getCoinName()) {
					String coinName = itrateBuyOrder.next().getCoinName();
					CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBycoinName(coinName);
					Long fees = cryptoCurrency.getFees();

					// Checking Coin price

					if (itrateBuyOrder.next().getBuyDesiredPrice() >= itrateSellOrder.next().getSellDesiredPrice()) {

						Long sellId = itrateSellOrder.next().getSellOrderId();
						Long buyId = itrateBuyOrder.next().getBuyOrderId();

						/**
						 * Checking coin Quantity if the coin is greater for buy then add coin in buyer
						 * wallet and deduct from seller account which is available and add that much
						 * money in seller fiat wallet from buyer fiat wallet and transaction is
						 * complete for seller and transaction is still pending for buyer
						 */

						if (itrateBuyOrder.next().getCoinQuantity() > itrateSellOrder.next().getCoinQuantity()) {

						}
						/**
						 * if the coin is smaller for sell then add coin in buyer wallet and deduct from
						 * seller account which is available and add that much money in seller fiat
						 * wallet from buyer fiat wallet and transaction is complete for buyer and
						 * transaction is still pending for seller
						 */

						else if (itrateBuyOrder.next().getCoinQuantity() < itrateSellOrder.next().getCoinQuantity()) {
                         
						}
						/**
						 * if the coin is same then add coin in buyer wallet and deduct from seller
						 * account and opposite in fiat wallet
						 */

						else if (itrateBuyOrder.next().getCoinQuantity() == itrateSellOrder.next().getCoinQuantity()) {
							// Transaction transaction = new

						}

					}

				}
			}
		}

		return null;

	}
}