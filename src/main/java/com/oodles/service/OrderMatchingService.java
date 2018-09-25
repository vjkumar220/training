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
					Double currencyFees = cryptoCurrency.getFees();

					if (listBuyOrder.getBuyPrice() >= listSellOrder.getSellPrice()) {
						Long sellId = listSellOrder.getSellOrderId();
						Long buyId = listBuyOrder.getBuyOrderId();

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
							Double sellerRemaningCoin = sellOrder.getRemainingSellCoinQuantity();

							// Seller and buyer Id
							Long sellerId = sellOrder.getUser().getId();
							Long buyerId = buyOrder.getUser().getId();

							// Seller Fiat Wallet details
							FiatWallet sellerFiatWallet = sellOrder.getUser().getFiatWallet();

							// Seller Crypto wallet details
							CryptoWallet sellerCryptoWallet = cryptoWalletRepository.findByCoinNameAndUserId(coinName,
									sellerId);

							// Buyers Details
							Double desiredBuyPrice = buyOrder.getBuyPrice();
							Double buyCoinQuantity = buyOrder.getBuyCoinQuantity();
							Double buyRemainingCoin = buyOrder.getRemainingBuyCoinQuantity();
							Double fee = buyOrder.getFeeForBuyers();
							Double buyerNetAmount = (desiredBuyPrice * buyCoinQuantity);
							Double buyerGrossAmount = buyOrder.getOrderPrice();

							// Buyer Fiat Wallet details
							FiatWallet buyerFiatWallet = buyOrder.getUser().getFiatWallet();

							// Buyer Crypto wallet details
							CryptoWallet buyerCryptoWallet = cryptoWalletRepository.findByCoinNameAndUserId(coinName,
									buyerId);

							if (listBuyOrder.getRemainingBuyCoinQuantity()
									.equals(listSellOrder.getRemainingSellCoinQuantity())) {
								Double sellerNetAmount = (desiredSellerPrice * sellerCoinQuantity);
								
								
								Double netAmountofSeller = (listSellOrder.getSellPrice() * listSellOrder.getRemainingSellCoinQuantity()); 
								Double netAmountofBuyer = (listBuyOrder.getBuyPrice() * listBuyOrder.getRemainingBuyCoinQuantity());
								Double feesForbuyer = (netAmountofBuyer * (currencyFees / 100));
								Double buyerCryptoBalance =(buyerCryptoWallet.getBalance());
								
								Double grossAmountOfBuyer = (netAmountofBuyer + feesForbuyer);
								
								
								sellOrder.setRemainingSellCoinQuantity(0.0);
								buyOrder.setRemainingBuyCoinQuantity(0.0);
								sellOrder.setSellOrderStatus(OrderStatus.COMPLETED);
								buyOrder.setBuyOrderStatus(OrderStatus.COMPLETED);
								// Buyer Fiat Wallet
								buyerFiatWallet.setBalance(buyerFiatWallet.getBalance() - grossAmountOfBuyer);
								buyerCryptoWallet.setBalance(buyerCryptoWallet.getBalance() + buyCoinQuantity);
								buyerCryptoWallet.setShadowBalance(buyerCryptoWallet.getShadowBalance() + buyCoinQuantity);
														
								sellerFiatWallet.setBalance(sellerFiatWallet.getBalance() + netAmountofSeller);
								sellerFiatWallet.setShadowBalance(sellerFiatWallet.getShadowBalance() + netAmountofSeller);
								sellerCryptoWallet.setBalance(sellerCryptoWallet.getBalance() - sellerCoinQuantity);
								
								Double profit = ((listBuyOrder.getBuyPrice()*listBuyOrder.getRemainingBuyCoinQuantity())-(listSellOrder.getSellPrice()*listSellOrder.getRemainingSellCoinQuantity()));
								SellTransaction sellTransaction = new SellTransaction();
								sellTransaction.setBuyerId(buyerId);
								sellTransaction.setSellerId(sellerId);
								sellTransaction.setBuyOrder(buyOrder);
								sellTransaction.setCoinName(coinName);
								sellTransaction.setCoinQuantity(listSellOrder.getRemainingSellCoinQuantity());
								sellTransaction.setExchangeRateSellDesiredPrice(listSellOrder.getSellPrice());
								sellTransaction.setGrossAmount(netAmountofSeller);
								sellTransaction.setNetAmount(netAmountofSeller);
								sellTransaction.setSellOrder(sellOrder);
								sellTransaction.setTransactionStatus(OrderStatus.COMPLETED.toString());
								sellTransaction.setTransationFee(0.0);
								
								BuyTransaction buyTransaction = new BuyTransaction();
								buyTransaction.setBuyerId(buyerId);
								buyTransaction.setBuyOrder(buyOrder);
								buyTransaction.setCoinQuantity(buyCoinQuantity);
								buyTransaction.setCointype(coinName);
								buyTransaction.setExchangeRate(desiredBuyPrice);
								buyTransaction.setGrossAmount(grossAmountOfBuyer);
								buyTransaction.setNetAmount(netAmountofBuyer);
								buyTransaction.setSellerId(sellerId);
								buyTransaction.setSellOrder(sellOrder);
								buyTransaction.setStatus(OrderStatus.COMPLETED.toString());
								buyTransaction.setTransationFee(feesForbuyer);
								
								ProfitTable profitTable = new ProfitTable();
								profitTable.setBuyerId(buyerId);
								profitTable.setFees(feesForbuyer);
								profitTable.setProfitAmount(profit);
								profitTable.setSellerId(sellerId);
								profitTable.setTotalProfit(profit + feesForbuyer);
								
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
							} else if (listBuyOrder.getRemainingBuyCoinQuantity() > listSellOrder
									.getRemainingSellCoinQuantity()) {

								Double coinQuantityDiffrence = (listBuyOrder.getRemainingBuyCoinQuantity()
										- listSellOrder.getRemainingSellCoinQuantity());
								Double buyOrderPrice = listBuyOrder.getBuyPrice();
								Double netAmountOfBuyer = (listBuyOrder.getBuyPrice() * listBuyOrder.getRemainingBuyCoinQuantity());
								Double feesOfBuyer =  (netAmountOfBuyer * (currencyFees / 100));
								Double grossAmountofBuyer = (feesOfBuyer + netAmountOfBuyer);

								buyOrder.setRemainingBuyCoinQuantity(coinQuantityDiffrence);
								buyOrderRepository.save(buyOrder);
								
								buyerFiatWallet.setBalance(buyerFiatWallet.getBalance() - grossAmountofBuyer);
								fiatWalletRepository.save(buyerFiatWallet);
								
								buyerCryptoWallet.setBalance(buyerCryptoWallet.getBalance() + listSellOrder
										.getRemainingSellCoinQuantity());
								buyerCryptoWallet
										.setShadowBalance(buyerCryptoWallet.getShadowBalance() + listSellOrder
												.getRemainingSellCoinQuantity());
								cryptoWalletRepository.save(buyerCryptoWallet);
								
								Double netAmountOfSeller = (listSellOrder.getSellPrice() * listSellOrder.getSellCoinQuantity());
								sellerFiatWallet.setBalance(sellerFiatWallet.getBalance() + netAmountOfSeller);
								sellerFiatWallet.setShadowBalance(sellerFiatWallet.getShadowBalance() + netAmountOfSeller);
								fiatWalletRepository.save(sellerFiatWallet);
								
								sellerCryptoWallet.setBalance(sellerCryptoWallet.getBalance()- listSellOrder
										.getRemainingSellCoinQuantity());
								cryptoWalletRepository.save(sellerCryptoWallet);
								
								Double profitAmount = ((listBuyOrder.getRemainingBuyCoinQuantity() * listBuyOrder.getBuyPrice())
										- (listSellOrder.getRemainingSellCoinQuantity() * listSellOrder.getSellPrice()));
								Double totalProfit = (profitAmount + feesOfBuyer);

								sellOrder.setRemainingSellCoinQuantity(0.0);
								buyOrder.setRemainingBuyCoinQuantity(coinQuantityDiffrence);
								sellOrder.setSellOrderStatus(OrderStatus.COMPLETED);
								buyOrder.setBuyOrderStatus(OrderStatus.PENDING);

								buyOrderRepository.save(buyOrder);
								sellOrderRepository.save(sellOrder);

								SellTransaction sellTransaction = new SellTransaction();
								sellTransaction.setBuyerId(buyerId);
								sellTransaction.setSellerId(sellerId);
								sellTransaction.setBuyOrder(buyOrder);
								sellTransaction.setCoinName(coinName);
								sellTransaction.setCoinQuantity(listSellOrder.getRemainingSellCoinQuantity());
								sellTransaction.setExchangeRateSellDesiredPrice(listSellOrder.getSellPrice());
								sellTransaction.setGrossAmount(listSellOrder.getSellPrice() * listSellOrder.getRemainingSellCoinQuantity());
								sellTransaction.setNetAmount(listSellOrder.getSellPrice() * listSellOrder.getRemainingSellCoinQuantity());
								sellTransaction.setSellOrder(sellOrder);
								sellTransaction.setTransactionStatus(OrderStatus.COMPLETED.toString());
								sellTransactionRepository.save(sellTransaction);

								BuyTransaction buyTransaction = new BuyTransaction();
								buyTransaction.setBuyerId(buyerId);
								buyTransaction.setBuyOrder(buyOrder);
								buyTransaction.setCoinQuantity(listBuyOrder.getRemainingBuyCoinQuantity());
								buyTransaction.setCointype(coinName);
								buyTransaction.setExchangeRate(listBuyOrder.getBuyPrice());
								buyTransaction.setGrossAmount(grossAmountofBuyer);
								buyTransaction.setNetAmount(netAmountOfBuyer);
								buyTransaction.setSellerId(sellerId);
								buyTransaction.setSellOrder(sellOrder);
								buyTransaction.setStatus(OrderStatus.COMPLETED.toString());
								buyTransaction.setTransationFee(feesOfBuyer);
								buyTransactionRepository.save(buyTransaction);

								ProfitTable profitTable = new ProfitTable();
								profitTable.setBuyerId(buyerId);
								profitTable.setFees((netAmountOfBuyer * (currencyFees / 100)));
								profitTable.setProfitAmount((listBuyOrder.getBuyPrice()*listBuyOrder.getRemainingBuyCoinQuantity())-(listSellOrder.getSellPrice()*listSellOrder.getRemainingSellCoinQuantity()));
								profitTable.setSellerId(sellerId);
								profitTable.setTotalProfit((netAmountOfBuyer * (currencyFees / 100))+(listBuyOrder.getBuyPrice()*listBuyOrder.getRemainingBuyCoinQuantity())-(listSellOrder.getSellPrice()*listSellOrder.getRemainingSellCoinQuantity()));
								profitTableRepository.save(profitTable);

								return "Your exact order is matched";

							} else if (listBuyOrder.getRemainingBuyCoinQuantity() < listSellOrder
									.getRemainingSellCoinQuantity()) {
								
								Double coinQuantityDiffrence = (listSellOrder.getRemainingSellCoinQuantity() - listBuyOrder.getRemainingBuyCoinQuantity());
								Double netAmountOfBuyer = (listBuyOrder.getBuyPrice() * listBuyOrder.getRemainingBuyCoinQuantity());
								Double feesOfBuyer =  (netAmountOfBuyer * (currencyFees / 100));
								Double grossAmountofBuyer = (feesOfBuyer + netAmountOfBuyer);
								
								buyerFiatWallet.setBalance(buyerFiatWallet.getBalance() - grossAmountofBuyer);
								fiatWalletRepository.save(buyerFiatWallet);
								
								buyerCryptoWallet.setBalance(buyerCryptoWallet.getBalance() + listBuyOrder.getRemainingBuyCoinQuantity());
								buyerCryptoWallet.setShadowBalance(buyerCryptoWallet.getShadowBalance() + listBuyOrder.getRemainingBuyCoinQuantity());
								cryptoWalletRepository.save(buyerCryptoWallet);
								
								Double sellOrderPrice = listSellOrder.getSellPrice();
								Double sellOrderCoinQuantity = listSellOrder.getRemainingSellCoinQuantity();
								Double netAmountOfSeller = (listSellOrder.getRemainingSellCoinQuantity() * listSellOrder.getSellPrice());
								
								sellerFiatWallet.setBalance(sellerFiatWallet.getBalance() + netAmountOfSeller);
								sellerFiatWallet.setShadowBalance(sellerFiatWallet.getShadowBalance() + netAmountOfSeller);
								fiatWalletRepository.save(sellerFiatWallet);
								
								sellerCryptoWallet.setBalance(sellerCryptoWallet.getBalance() - listSellOrder.getRemainingSellCoinQuantity());
								cryptoWalletRepository.save(sellerCryptoWallet);
								
								
								sellOrder.setRemainingSellCoinQuantity(coinQuantityDiffrence);
								buyOrder.setRemainingBuyCoinQuantity(0.0);
								sellOrder.setSellOrderStatus(OrderStatus.PENDING);
								buyOrder.setBuyOrderStatus(OrderStatus.COMPLETED);

								buyOrderRepository.save(buyOrder);
								sellOrderRepository.save(sellOrder);
								
								
								SellTransaction sellTransaction = new SellTransaction();
								sellTransaction.setBuyerId(buyerId);
								sellTransaction.setSellerId(sellerId);
								sellTransaction.setBuyOrder(buyOrder);
								sellTransaction.setCoinName(coinName);
								sellTransaction.setCoinQuantity(listSellOrder.getRemainingSellCoinQuantity());
								sellTransaction.setExchangeRateSellDesiredPrice(listSellOrder.getSellPrice());
								sellTransaction.setGrossAmount(listSellOrder.getSellPrice() * listSellOrder.getRemainingSellCoinQuantity());
								sellTransaction.setNetAmount(listSellOrder.getSellPrice() * listSellOrder.getRemainingSellCoinQuantity());
								sellTransaction.setSellOrder(sellOrder);
								sellTransaction.setTransactionStatus(OrderStatus.COMPLETED.toString());
								sellTransaction.setTransationFee(0.0);
								sellTransactionRepository.save(sellTransaction);

								BuyTransaction buyTransaction = new BuyTransaction();
								buyTransaction.setBuyerId(buyerId);
								buyTransaction.setBuyOrder(buyOrder);
								buyTransaction.setCoinQuantity(listBuyOrder.getRemainingBuyCoinQuantity());
								buyTransaction.setCointype(coinName);
								buyTransaction.setExchangeRate(desiredBuyPrice);
								buyTransaction.setGrossAmount(buyerGrossAmount);
								buyTransaction.setNetAmount(buyerNetAmount);
								buyTransaction.setSellerId(sellerId);
								buyTransaction.setSellOrder(sellOrder);
								buyTransaction.setStatus(OrderStatus.COMPLETED.toString());
								buyTransaction.setTransationFee(feesOfBuyer);
								buyTransactionRepository.save(buyTransaction);

								ProfitTable profitTable = new ProfitTable();
								profitTable.setBuyerId(buyerId);
								profitTable.setFees((netAmountOfBuyer * (currencyFees / 100)));
								profitTable.setProfitAmount((listBuyOrder.getBuyPrice()*listBuyOrder.getRemainingBuyCoinQuantity())-(listSellOrder.getSellPrice()*listSellOrder.getRemainingSellCoinQuantity()));
								profitTable.setSellerId(sellerId);
								profitTable.setTotalProfit((netAmountOfBuyer * (currencyFees / 100))+(listBuyOrder.getBuyPrice()*listBuyOrder.getRemainingBuyCoinQuantity())-(listSellOrder.getSellPrice()*listSellOrder.getRemainingSellCoinQuantity()));
								profitTableRepository.save(profitTable);
								return "Order is matched";	
							}

						}

					}
				}

			}
		}
		return "No order found to match";
	}

}
