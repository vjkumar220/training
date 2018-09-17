package com.oodles.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.BuyOrder;
import com.oodles.domain.CryptoCurrency;
import com.oodles.domain.SellOrder;
import com.oodles.domain.Transaction;
import com.oodles.enumeration.OrderStatus;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.SellOrderRepository;
import com.oodles.repository.TransactionRepository;

@Service
public class OrderMatchingService {

	@Autowired
	private BuyOrderRepository buyOrderRepository;

	@Autowired
	private SellOrderRepository sellOrderRepository;

	@Autowired
	private CryptoCurrencyRepository cryptoCurrencyRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	public List<BuyOrder> buyList() {
		List<BuyOrder> result = buyOrderRepository.findAllByBuyOrderStatus(OrderStatus.PENDING);
		System.out.println(result);
		Collections.sort(result, new Comparator<BuyOrder>() {
			@Override
			public int compare(BuyOrder o1, BuyOrder o2) {
				return o1.getBuyPrice().compareTo(o2.getBuyPrice());
			}
		});
		return result;
	}

	public List<SellOrder> sellList() {
		List<SellOrder> result = sellOrderRepository.findAllBySellOrderStatus(OrderStatus.PENDING);

		Collections.sort(result, new Comparator<SellOrder>() {
			@Override
			public int compare(SellOrder o1, SellOrder o2) {
				return o1.getSellPrice().compareTo(o2.getSellPrice());
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
			ArrayList<Object> appproved = new ArrayList<>();
			Iterator<BuyOrder> itrateBuyOrder = buyOrder.iterator();
			Iterator<SellOrder> itrateSellOrder = sellOrder.iterator();
			while (itrateBuyOrder.hasNext() && itrateSellOrder.hasNext()) {

				// Checking coin name

				if (itrateBuyOrder.next().getBuyCoinName() == itrateSellOrder.next().getSellCoinName()) {
					String coinName = itrateBuyOrder.next().getBuyCoinName();
					CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findByCoinName(coinName)	;				
					Double fees = cryptoCurrency.getFees();
					
					// Checking Coin price

					if (itrateBuyOrder.next().getBuyPrice() >= itrateSellOrder.next().getSellPrice()) {

						Long sellId = itrateSellOrder.next().getSellOrderId();
						Long buyId = itrateBuyOrder.next().getBuyOrderId();
						
						// Checking coin Quantity

						if (itrateBuyOrder.next().getBuyCoinQuantity() < itrateSellOrder.next().getSellCoinQuantity()) {

						} else if (itrateBuyOrder.next().getBuyCoinQuantity() < itrateSellOrder.next()
								.getSellCoinQuantity()) {

						} else if (itrateBuyOrder.next().getBuyCoinQuantity() == itrateSellOrder.next()
								.getSellCoinQuantity()) {
							Transaction transaction = new Transaction();

						}

					}

				}
			}
		}

		return null;

	}
}
