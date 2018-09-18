package com.oodles.services;

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
import com.oodles.models.CryptoWallet;
import com.oodles.models.SellOrder;
import com.oodles.models.User;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatWalletRepository;
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
	@Autowired
	private FiatWalletRepository fiatWalletRepository;

	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;

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
			Iterator<BuyOrder> iterateBuyOrder = buyOrder.iterator();
			Iterator<SellOrder> iterateSellOrder = sellOrder.iterator();
			while (iterateBuyOrder.hasNext() && iterateSellOrder.hasNext()) {

				// Checking coin name

				if (iterateBuyOrder.next().getCoinName() == iterateSellOrder.next().getCoinName()) {
					String coinName = iterateBuyOrder.next().getCoinName();
					CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBycoinName(coinName);
					Long fees = cryptoCurrency.getFees();

					// Checking Coin price

					if (iterateBuyOrder.next().getBuyDesiredPrice() >= iterateSellOrder.next().getSellDesiredPrice()) {
                        /**
                        * seller and order id
                        */
						Long sellId = iterateSellOrder.next().getSellOrderId();
						Long buyId = iterateBuyOrder.next().getBuyOrderId();
						/**
						 * Buy and sell coin name
						 */
						String sellcoinName=iterateSellOrder.next().getCoinName();
						String buycoinName=iterateBuyOrder.next().getCoinName();                       User buyUserId=iterateBuyOrder.next().getUser();
                       
						User sellUserId=iterateSellOrder.next().getUser();
						/**
						 * Buyer and seller id
						 */
                        Long buyerUserId=buyUserId.getId();
						Long sellerUserId=sellUserId.getId();
						/**
						 * Buyer and seller fiat wallet id
						 */
						Long buyerFiatWalletid=buyUserId.getFiatwallet().getWalletId();             
						Long sellerFiatWalletid=sellUserId.getFiatwallet().getWalletId();
						
                       /**
                        * Buyer and seller crypto wallet id 
                        */
                        
						CryptoWallet buycwType=cryptoWalletRepository.findByCoinNameAndUserId(buycoinName, buyerUserId);
						
						Long buyercryptoWalletid=buycwType.getWalletId();
						
						CryptoWallet sellcwType=cryptoWalletRepository.findByCoinNameAndUserId(sellcoinName, sellerUserId);
						
						Long sellercryptoWalletid=sellcwType.getWalletId();
						/**
						 * Buy order values
						 */
						Double noOfCointobesell= iterateSellOrder.next().getRemainingCoin();
						Double desiredpriceforsell=iterateSellOrder.next().getSellDesiredPrice();
                        Double noOfCointobebuy=iterateBuyOrder.next().getRemainingCoin();
                        Double desiredpriceforbuy=iterateBuyOrder.next().getBuyDesiredPrice();
						Double amountforsell=(noOfCointobesell*desiredpriceforsell);
                        Double amountforbuy=(noOfCointobebuy*desiredpriceforbuy);
                        
                        Double grossamountbuy=(amountforbuy)*fees/100;
                        
                        
                        /**
						 * Checking coin Quantity if the coin is greater for buy then add coin in buyer
						 * wallet and deduct from seller account which is available and add that much
						 * money in seller fiat wallet from buyer fiat wallet and transaction is
						 * complete for seller and transaction is still pending for buyer
						 */

						if (iterateBuyOrder.next().getRemainingCoin() > iterateSellOrder.next().getRemainingCoin()) {

						}
						/**
						 * if the coin is smaller for sell then add coin in buyer wallet and deduct from
						 * seller account which is available and add that much money in seller fiat
						 * wallet from buyer fiat wallet and transaction is complete for buyer and
						 * transaction is still pending for seller
						 */

						else if (iterateBuyOrder.next().getRemainingCoin() < iterateSellOrder.next().getRemainingCoin()) {
                         
						}
						/**
						 * if the coin is same then add coin in buyer wallet and deduct from seller
						 * account and opposite in fiat wallet
						 */

						else if (iterateBuyOrder.next().getRemainingCoin() == iterateSellOrder.next().getRemainingCoin()) {
							
                            
						}

					}

				}
			}
		}

		return null;

	}
}