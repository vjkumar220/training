package com.oodles.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.BuyOrder;
import com.oodles.domain.BuyTransaction;
import com.oodles.domain.CryptoCurrency;
import com.oodles.domain.CryptoWallet;
import com.oodles.domain.FiatWallet;
import com.oodles.domain.ProfitTable;
import com.oodles.domain.SellOrder;
import com.oodles.domain.SellTransaction;
import com.oodles.enumeration.CryptoName;
import com.oodles.enumeration.OrderStatus;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.BuyTransactionRepository;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.ProfitTableRepository;
import com.oodles.repository.SellOrderRepository;
import com.oodles.repository.SellTransactionRepository;

@Service
public class OrderMatchingService {
	Logger log = LoggerFactory.getLogger(OrderMatchingService.class);

	@Autowired
	private BuyOrderRepository buyOrderRepository;
	@Autowired
	private SellOrderRepository sellOrderRepository;
	@Autowired
	private CryptoCurrencyRepository cryptoCurrencyRepository;
	@Autowired
	private CryptoWalletRepository cryptoWalletRepository;
	@Autowired
	private SellTransactionRepository sellTransactionRepository;
	@Autowired
	private BuyTransactionRepository buyTransactionRepository;
	@Autowired
	private FiatWalletRepository fiatWalletRepository;
	@Autowired
	private ProfitTableRepository profitTableRepository;

	public List<BuyOrder> buyList() {
		List<BuyOrder> result = buyOrderRepository.findAllByBuyOrderStatus(OrderStatus.PENDING);
		log.info("buy Order result");
		Collections.sort(result, new Comparator<BuyOrder>() {
			@Override
			public int compare(BuyOrder o1, BuyOrder o2) {
				return o1.getBuyPrice().compareTo(o2.getBuyPrice());
			}
		});
		log.info("buy order result after sorted");
		Collections.reverse(result);
		return result;
	}

	public List<SellOrder> sellList() {
		List<SellOrder> result = sellOrderRepository.findAllBySellOrderStatus(OrderStatus.PENDING);
		log.info("sell order result");
		Collections.sort(result, new Comparator<SellOrder>() {
			@Override
			public int compare(SellOrder o1, SellOrder o2) {
				return o1.getSellPrice().compareTo(o2.getSellPrice());
			}
		});
		log.info("sell order result after sorted");
		return result;
	}


	/**
	 * Order Matching
	 * 
	 * @return
	 */

	public String orderMatch() {
		List<SellOrder> list1 = sellOrderRepository.findAllBySellOrderStatus(OrderStatus.PENDING);
		log.info("sell order result");
		Collections.sort(list1, new Comparator<SellOrder>() {
			@Override
			public int compare(SellOrder o1, SellOrder o2) {
				return o1.getSellPrice().compareTo(o2.getSellPrice());
			}
		});
		Collections.reverse(list1);

		List<BuyOrder> list2 = buyOrderRepository.findAllByBuyOrderStatus(OrderStatus.PENDING);
		log.info("buy Order result");
		Collections.sort(list2, new Comparator<BuyOrder>() {
			@Override
			public int compare(BuyOrder o1, BuyOrder o2) {
				return o1.getBuyPrice().compareTo(o2.getBuyPrice());
			}
		});

		for (BuyOrder listBuyOrder : list2) {
			
			for (SellOrder listSellOrder : list1) {

				// Checking Buyer Coin Name and seller Coin Name
				if (listBuyOrder.getBuyCoinName().equals(listSellOrder.getSellCoinName())) {
					String coinName = listBuyOrder.getBuyCoinName();
					CryptoName coinNameValue = CryptoName.valueOf(coinName);
					CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findByCoinName(coinNameValue);
					log.info(" both the coinName matched");

					if (listBuyOrder.getBuyPrice().equals(listSellOrder.getSellPrice())) {
						Long sellId = listSellOrder.getSellOrderId();
						Long buyId = listBuyOrder.getBuyOrderId();
						log.info("sellId", sellId);
						log.info("buy id", buyId);
						Optional<SellOrder> sellOrderDetails = sellOrderRepository.findById(sellId);
						Optional<BuyOrder> buyOrderDetails = buyOrderRepository.findById(buyId);
						// Checking SellOrder and buyer Order is present or not
						
						if (sellOrderDetails.isPresent() && buyOrderDetails.isPresent()) {
							SellOrder sellOrder = sellOrderDetails.get();
							BuyOrder buyOrder = buyOrderDetails.get();
							// Seller Details
							Double desiredSellerPrice = sellOrder.getSellPrice();
							Double sellerCoinQuantity = sellOrder.getSellCoinQuantity();
							Double sellerGrossAmount = sellOrder.getOrderPrice();
							
							log.info("desired seller price" + desiredSellerPrice);
							log.info("seller coin quantiy" + sellerCoinQuantity);
							log.info("seller Gross Amount" + sellerGrossAmount);
							// Seller and buyer Id
							Long sellerId = sellOrder.getUser().getId();
							Long buyerId = buyOrder.getUser().getId();
							log.info("seller Id", sellerId);
							log.info("buyer Id", buyerId);
							// Seller Fiat Wallet details
							FiatWallet sellerFiatWallet = sellOrder.getUser().getFiatWallet();
							Double sellerFiatWalletShadowBalance = sellerFiatWallet.getShadowBalance();
							Double sellerFiatWalletBalance = sellerFiatWallet.getBalance();
							log.info("sellerFiatWalletShadowBalance" + sellerFiatWalletShadowBalance);
							log.info("sellerFiatWalletBalance" + sellerFiatWalletBalance);
							// Seller Crypto wallet details
							CryptoWallet sellerCryptoWallet = cryptoWalletRepository.findByCoinNameAndUserId(coinName,
									sellerId);
							Double sellerCryptoWalletBalance = sellerCryptoWallet.getBalance();
							log.info("sellerCryptoWalletBalance" + sellerCryptoWalletBalance);
							// Buyers Details
							Double desiredBuyPrice = buyOrder.getBuyPrice();
							Double buyCoinQuantity = buyOrder.getBuyCoinQuantity();
							Double fee = buyOrder.getFeeForBuyers();
							Double buyerNetAmount = (desiredBuyPrice * buyCoinQuantity);
							Double buyerGrossAmount = buyOrder.getOrderPrice();
							log.info("desiredBuyPrice" + desiredBuyPrice);
							log.info("buyCoinQuantity" + buyCoinQuantity);
							log.info("fee" + fee);
							log.info("buyerNetAmount" + buyerNetAmount);
							log.info("buyerGrossAmount" + buyerGrossAmount);
							// Buyer Fiat Wallet details
							FiatWallet buyerFiatWallet = buyOrder.getUser().getFiatWallet();
							Double buyerFiatShadowBalance = buyerFiatWallet.getShadowBalance();
							Double buyerFiatBalance = buyerFiatWallet.getBalance();
							log.info("buyerFiatShadowBalance" + buyerFiatShadowBalance);
							log.info("buyerFiatBalance" + buyerFiatBalance);
							// Buyer Crypto wallet details
							CryptoWallet buyerCryptoWallet = cryptoWalletRepository.findByCoinNameAndUserId(coinName,
									buyerId);
							Double buyerCryptoWalletBalance = buyerCryptoWallet.getBalance();
							Double buyerCryptoWalletShadowBalance = buyerCryptoWallet.getShadowBalance();
							log.info("buyerCryptoWalletBalance" + buyerCryptoWalletBalance);
							log.info("buyerCryptoWalletShadowBalance" + buyerCryptoWalletShadowBalance);
							
							if (buyOrder.getRemainingBuyCoinQuantity()
									.equals(sellOrder.getRemainingSellCoinQuantity())) {
								Double sellerNetAmount = (desiredSellerPrice * sellerCoinQuantity);
								// buyer fiat and crypto updted balance
								Double buyerUpdatedFiatBalance = (buyerFiatBalance - buyerGrossAmount);
								Double buyerUpdatedCryptoWalletBalance = (buyerCryptoWalletBalance
										+ sellerCoinQuantity);
								Double buyerUpdatedCryptoShadowBalance = (buyerCryptoWalletShadowBalance
										+ sellerCoinQuantity);
								// seller crypto and fiat updated balance
								Double sellerUpdatedFiatBalance = (sellerFiatWalletBalance + sellerNetAmount);
								Double sellerUpdatedShadowBalance = (sellerFiatWalletShadowBalance + sellerNetAmount);
								Double updatedSellerCryptoBalance = (sellerCryptoWalletBalance - sellerCoinQuantity);
								// Orders sell and buy
								sellOrder.setRemainingSellCoinQuantity(0.0);
								buyOrder.setRemainingBuyCoinQuantity(0.0);
								sellOrder.setSellOrderStatus(OrderStatus.COMPLETED);
								buyOrder.setBuyOrderStatus(OrderStatus.COMPLETED);
								// Buyer Fiat Wallet
								buyerFiatWallet.setBalance(buyerUpdatedFiatBalance);
								// Buyer crypto Wallet
								buyerCryptoWallet.setBalance(buyerUpdatedCryptoWalletBalance);
								buyerCryptoWallet.setShadowBalance(buyerUpdatedCryptoShadowBalance);
								// Seller Fiat wallet
								sellerFiatWallet.setBalance(sellerUpdatedFiatBalance);
								sellerFiatWallet.setShadowBalance(sellerUpdatedShadowBalance);
								// seller crypto wallet
								sellerCryptoWallet.setBalance(updatedSellerCryptoBalance);
								//profit for Admin
								Double profitAmount = ((buyCoinQuantity * desiredBuyPrice) - (sellerCoinQuantity * desiredSellerPrice));
								Double totalProfit = (profitAmount + fee);
								SellTransaction sellTransaction = new SellTransaction();
								sellTransaction.setBuyerId(buyerId);
								sellTransaction.setSellerId(sellerId);
								sellTransaction.setBuyOrder(buyOrder);
								sellTransaction.setCoinName(coinName);
								sellTransaction.setCoinQuantity(sellerCoinQuantity);
								sellTransaction.setExchangeRateSellDesiredPrice(desiredSellerPrice);
								sellTransaction.setGrossAmount(sellerGrossAmount);
								sellTransaction.setNetAmount(sellerNetAmount);
								sellTransaction.setSellOrder(sellOrder);
								sellTransaction.setTransactionStatus(OrderStatus.COMPLETED.toString());
								sellTransaction.setTransationFee(0.0);
								BuyTransaction buyTransaction = new BuyTransaction();
								buyTransaction.setBuyerId(buyerId);
								buyTransaction.setBuyOrder(buyOrder);
								buyTransaction.setCoinQuantity(buyCoinQuantity);
								buyTransaction.setCointype(coinName);
								buyTransaction.setExchangeRate(desiredBuyPrice);
								buyTransaction.setGrossAmount(buyerGrossAmount);
								buyTransaction.setNetAmount(buyerNetAmount);
								buyTransaction.setSellerId(sellerId);
								buyTransaction.setSellOrder(sellOrder);
								buyTransaction.setStatus(OrderStatus.COMPLETED.toString());
								buyTransaction.setTransationFee(fee);
								ProfitTable profitTable = new ProfitTable();
								profitTable.setBuyerId(buyerId);
								profitTable.setFees(fee);
								profitTable.setProfitAmount(profitAmount);
								profitTable.setSellerId(sellerId);
								profitTable.setTotalProfit(totalProfit);
								profitTableRepository.save(profitTable);
								sellOrderRepository.save(sellOrder);
								buyOrderRepository.save(buyOrder);
								buyTransactionRepository.save(buyTransaction);
								sellTransactionRepository.save(sellTransaction);
								cryptoWalletRepository.save(sellerCryptoWallet);
								fiatWalletRepository.save(sellerFiatWallet);
								cryptoWalletRepository.save(buyerCryptoWallet);
								fiatWalletRepository.save(buyerFiatWallet);
								return "Your exact order is matched";
							} 

						}

					}
				}

			}
		}
		return null;
	}
}
