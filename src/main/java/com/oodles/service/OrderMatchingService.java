package com.oodles.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.BuyOrder;
import com.oodles.domain.CryptoCurrency;
import com.oodles.domain.CryptoWallet;
import com.oodles.domain.FiatWallet;
import com.oodles.domain.SellOrder;
import com.oodles.domain.SellTransaction;
import com.oodles.domain.Transaction;
import com.oodles.enumeration.OrderStatus;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.CryptoWalletRepository;
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

	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;

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

	/**
	 * Order Matching
	 * 
	 * @return
	 */

	public List orderMatch() {
		/**
		 * Sorted List of sell order ascending
		 */

		List<BuyOrder> buyOrderList = buyOrderRepository.findAllByBuyOrderStatus(OrderStatus.PENDING);
		Collections.sort(buyOrderList, new Comparator<BuyOrder>() {
			@Override
			public int compare(BuyOrder o1, BuyOrder o2) {
				return o1.getBuyPrice().compareTo(o2.getBuyPrice());
			}
		});

		/**
		 * Sorted List of buy order decending
		 */

		List<SellOrder> sellOrderList = sellOrderRepository.findAllBySellOrderStatus(OrderStatus.PENDING);
		Collections.sort(sellOrderList, new Comparator<SellOrder>() {
			@Override
			public int compare(SellOrder o1, SellOrder o2) {
				return o1.getSellPrice().compareTo(o2.getSellPrice());
			}
		});
		Collections.reverse(sellOrderList);

		if (buyOrderList == null || sellOrderList == null) {

			return null;
		}

		else {
			ArrayList<Object> appproved = new ArrayList<>();
			Iterator<BuyOrder> itrateBuyOrder = buyOrderList.iterator();
			Iterator<SellOrder> itrateSellOrder = sellOrderList.iterator();
			while (itrateBuyOrder.hasNext() && itrateSellOrder.hasNext()) {

				// Checking coin name

				if (itrateBuyOrder.next().getBuyCoinName() == itrateSellOrder.next().getSellCoinName()) {
					String coinName = itrateBuyOrder.next().getBuyCoinName();
					CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findByCoinName(coinName);
					Double transactionFee = cryptoCurrency.getFees();

					// Checking Coin price

					if (itrateBuyOrder.next().getBuyPrice() == itrateSellOrder.next().getSellPrice()) {

						Long sellId = itrateSellOrder.next().getSellOrderId();
						Long buyId = itrateBuyOrder.next().getBuyOrderId();
						Optional<SellOrder> sellOrderDetails = sellOrderRepository.findById(sellId);
						Optional<BuyOrder> buyOrderDetails = buyOrderRepository.findById(buyId);
						if (sellOrderDetails.isPresent() && buyOrderDetails.isPresent()) {
							SellOrder sellOrder = sellOrderDetails.get();
							BuyOrder buyOrder = buyOrderDetails.get();

							// Seller Details
							Double desiredSellPrice = sellOrder.getSellPrice();
							Double sellCoinQuantity = sellOrder.getSellCoinQuantity();
							Double sellerGrossAmount = sellOrder.getOrderPrice();
							Double sellerNetAmount = (desiredSellPrice * sellCoinQuantity);

							// Seller Id
							Long sellerId = sellOrder.getUser().getId();

							// Seller Fiat Wallet details
							FiatWallet sellerFiatWallet = sellOrder.getUser().getFiatWallet();
							Double sellerFiatWalletShadowBalance = sellerFiatWallet.getShadowBalance();
							Double sellerFiatWalletBalance = sellerFiatWallet.getBalance();

							// Seller Crypto wallet details
							CryptoWallet sellerCryptoWallet = cryptoWalletRepository.findByCoinNameAndUserId(coinName,
									sellerId);

							// Buyers Details
							Double desiredBuyPrice = buyOrder.getBuyPrice();
							Double buyCoinQuantity = buyOrder.getBuyCoinQuantity();
							Double fee = buyOrder.getFeeForBuyers();
							Double buyerNetAmount = (desiredBuyPrice * desiredSellPrice);
							Double buyerGrossAmount = buyOrder.getOrderPrice();

							// Buyer Id
							Long buyerId = buyOrder.getUser().getId();

							// Buyer Fiat Wallet details
							FiatWallet buyerFiatWallet = buyOrder.getUser().getFiatWallet();
							Double buyerFiatShadowBalance = buyerFiatWallet.getShadowBalance();
							Double buyerFiatBalance = buyerFiatWallet.getBalance();

							// Buyer Crypto wallet details
							CryptoWallet buyerCryptoWallet = cryptoWalletRepository.findByCoinNameAndUserId(coinName,
									buyerId);

							/**
							 * Checking the seller coin and buyer coin quantity is equal
							 */
							if (itrateBuyOrder.next().getRemainingBuyCoinQuantity() == itrateSellOrder.next()
									.getRemainingSellCoinQuantity()) {
								
								SellTransaction sellTransaction = new SellTransaction();

							}
						}

					}

				}
			}
		}

		return null;

	}
}

// Checking coin Quantity
