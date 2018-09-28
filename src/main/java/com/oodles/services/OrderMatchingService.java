package com.oodles.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.enums.OrderStatus;
import com.oodles.enums.TransactionStatus;
import com.oodles.models.BuyOrder;
import com.oodles.models.BuyTransaction;
import com.oodles.models.CryptoCurrency;
import com.oodles.models.CryptoWallet;
import com.oodles.models.FiatWallet;
import com.oodles.models.ProfitBook;
import com.oodles.models.SellOrder;
import com.oodles.models.SellTransaction;
import com.oodles.models.User;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.BuyTransactionRepository;
import com.oodles.repository.CryptoCurrencyRepository;
import com.oodles.repository.CryptoWalletRepository;
import com.oodles.repository.FiatWalletRepository;
import com.oodles.repository.ProfitBookRepository;
import com.oodles.repository.SellOrderRepository;
import com.oodles.repository.SellTransactionRepository;
import com.oodles.repository.UserRepository;

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
	@Autowired
	private BuyTransactionRepository buyTransactionRepository;
	@Autowired
	private SellTransactionRepository sellTransactionRepository;
    @Autowired
    private ProfitBookRepository profitBookRepository;
    @Autowired
	private UserRepository userRepository;
	public List<BuyOrder> buyList() {
		List<BuyOrder> result = buyOrderRepository.findAllByStatus(OrderStatus.PENDING);

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

	public Map<String, Object> orderMatch() {
		Map<String, Object> result = new HashMap<String, Object>();
		List<BuyOrder> buyOrderList = buyOrderRepository.findAllByStatus(OrderStatus.PENDING);
		Collections.sort(buyOrderList, new Comparator<BuyOrder>() {
			@Override
			public int compare(BuyOrder o1, BuyOrder o2) {
				return o1.getBuyDesiredPrice().compareTo(o2.getBuyDesiredPrice());
			}
		});

		List<SellOrder> sellOrderList = sellOrderRepository.findAllByStatus(OrderStatus.PENDING);
		Collections.sort(sellOrderList, new Comparator<SellOrder>() {
			@Override
			public int compare(SellOrder o1, SellOrder o2) {
				return o1.getSellDesiredPrice().compareTo(o2.getSellDesiredPrice());
			}
		});
		Collections.reverse(sellOrderList);

		if (buyOrderList == null || sellOrderList == null) {

			return null;
		}

		else {
			logger.info("buy" + buyOrderList);
			logger.info("sell" + sellOrderList);
			/*
			 * Iterator<BuyOrder> iterateBuyOrder = buyOrderList.iterator();
			 * Iterator<SellOrder> iterateSellOrder = sellOrderList.iterator(); while
			 * (iterateBuyOrder.hasNext() || iterateSellOrder.hasNext()) {
			 */
			for (BuyOrder buylistentry : buyOrderList) {
				for (SellOrder selllistentry : sellOrderList) {

					logger.info("buy and order iterate");

					// Checking coin name

					if ((buylistentry.getCoinName()).equalsIgnoreCase(selllistentry.getCoinName())) {
						logger.info("coin match");

						if (buylistentry.getBuyDesiredPrice() >= selllistentry.getSellDesiredPrice()) {
							// logger.info("price equal and greater");
							// buyer fiat balance
							String coinName = buylistentry.getCoinName();
							CryptoCurrency cryptoCurrency = cryptoCurrencyRepository.findBycoinName(coinName);
							Long fees = cryptoCurrency.getFees();

							/**
							 * seller and order id
							 */
							Long sellId = selllistentry.getSellOrderId();
							Long buyId = buylistentry.getBuyOrderId();
							/**
							 * Buy and sell coin name
							 */
							String sellcoinName = selllistentry.getCoinName();
							String buycoinName = buylistentry.getCoinName();
							User buyUserId = buylistentry.getUser();

							User sellUserId = selllistentry.getUser();
							/**
							 * Buyer and seller id
							 */
							Long buyerUserId = buyUserId.getId();
							Long sellerUserId = sellUserId.getId();
							/**
							 * Buyer and seller fiat wallet id
							 */
							Long buyerFiatWalletid = buyUserId.getFiatwallet().getWalletId();
							Long sellerFiatWalletid = sellUserId.getFiatwallet().getWalletId();
							FiatWallet buyfwType = fiatWalletRepository.findByWalletId(buyerFiatWalletid);
							FiatWallet sellfwType = fiatWalletRepository.findByWalletId(sellerFiatWalletid);
							/**
							 * Buyer and seller crypto wallet id
							 */

							CryptoWallet buycwType = cryptoWalletRepository.findByCoinNameAndUserId(buycoinName,
									buyerUserId);

							Long buyercryptoWalletid = buycwType.getWalletId();

							CryptoWallet sellcwType = cryptoWalletRepository.findByCoinNameAndUserId(sellcoinName,
									sellerUserId);

							Long sellercryptoWalletid = sellcwType.getWalletId();
							/**
							 * Buy order values
							 */
							Double noOfCointobesell = selllistentry.getRemainingCoin();
							Double desiredpriceforsell = selllistentry.getSellDesiredPrice();
							Double noOfCointobebuy = buylistentry.getRemainingCoin();
							Double desiredpriceforbuy = buylistentry.getBuyDesiredPrice();
							Double amountforsell = (noOfCointobesell * desiredpriceforsell);
							Double amountforbuy = (noOfCointobebuy * desiredpriceforbuy);
							Double grossamountbuy = ((amountforbuy) * fees / 100) + amountforbuy;
							Double remainingcoinofbuy = buylistentry.getRemainingCoin();
							Double remainingcoinofsell = selllistentry.getRemainingCoin();
							// Checking Coin price
							BuyOrder buy = buyOrderRepository.findByBuyOrderId(buyId);
							SellOrder sell = sellOrderRepository.findBySellOrderId(sellId);
							
							
							
							
							
							
							
							if (buylistentry.getRemainingCoin() > selllistentry.getRemainingCoin()) {
                                if(buyerUserId!=sellerUserId)
                                {
								// buyer fiat balance
								Double currentfiatbalanceofbuyer = buyUserId.getFiatwallet().getBalance();
								Double buyamount = (desiredpriceforbuy * noOfCointobesell);
								Double buygrossamount = ((buyamount) * fees / 100) + (buyamount);
								Double updatedbuyerFiatBalance = currentfiatbalanceofbuyer - buygrossamount;
								buyfwType.setBalance(updatedbuyerFiatBalance);
								fiatWalletRepository.save(buyfwType);
								// buyer cryptowallet balance
								Double currentcryptobalanceofbuyer = buycwType.getBalance();
								Double updatedbuyerCryptoBalance = currentcryptobalanceofbuyer + noOfCointobesell;
								buycwType.setBalance(updatedbuyerCryptoBalance);
								Double currentcryptoShadowbalanceofbuyer = buycwType.getShadowBalance();
								Double updatedbuyerCryptoShadowBalance = currentcryptoShadowbalanceofbuyer
										+ noOfCointobesell;
								buycwType.setShadowBalance(updatedbuyerCryptoShadowBalance);

								cryptoWalletRepository.save(buycwType);
								// seller fiat balance
								Double currentfiatbalanceofseller = sellUserId.getFiatwallet().getBalance();
								Double sellamount = (desiredpriceforsell * noOfCointobesell);
								Double updatedsellerFiatBalance = currentfiatbalanceofseller + sellamount;
								sellfwType.setBalance(updatedsellerFiatBalance);

								Double currentfiatShadowbalanceofseller = sellUserId.getFiatwallet().getShadowBalance();
								Double updatedsellerFiatShadowBalance = currentfiatShadowbalanceofseller + sellamount;
								sellfwType.setShadowBalance(updatedsellerFiatShadowBalance);

								fiatWalletRepository.save(sellfwType);
								// seller crypto balance
								Double currentcryptobalanceofseller = sellcwType.getBalance();
								Double updatedsellerCryptoBalance = currentcryptobalanceofseller - noOfCointobesell;
								sellcwType.setBalance(updatedsellerCryptoBalance);
								cryptoWalletRepository.save(sellcwType);
								//profit
								Double profitAmount=buygrossamount-sellamount;
								// buy and order book maintain
								Double currentremainingcoinofbuy = buy.getRemainingCoin();
								Double updateremainingcoinofbuy = currentremainingcoinofbuy - noOfCointobesell;
								buy.setRemainingCoin(updateremainingcoinofbuy);
								buyOrderRepository.save(buy);
								Double currentremainingcoinofsell = sell.getRemainingCoin();
								Double updateremainingcoinofsell = currentremainingcoinofsell - noOfCointobesell;
								sell.setRemainingCoin(updateremainingcoinofsell);
								sell.setStatus(OrderStatus.COMPLETED);
								sellOrderRepository.save(sell);
								// Sell Transaction
								SellTransaction sellTransaction = new SellTransaction();
								sellTransaction.setCoinQuantity(noOfCointobesell);
								sellTransaction.setCoinType(sellcoinName);
								sellTransaction.setExchangeRateAsDesiredPrice(desiredpriceforsell);
								sellTransaction.setFees((long) 0);
								sellTransaction.setGrossAmount(sellamount);
								sellTransaction.setNetAmount(sellamount);
								sellTransaction.setStatus(TransactionStatus.COMPLETED);
								sellTransaction.setBuyOrder(buy);
								sellTransaction.setSellOrder(sell);
								sellTransaction.setBuyerId(buyerUserId);
								sellTransaction.setSellerId(sellerUserId);
								sellTransactionRepository.save(sellTransaction);
								BuyTransaction buyTransaction = new BuyTransaction();
								buyTransaction.setCoinQuantity(noOfCointobesell);
								buyTransaction.setCoinType(buycoinName);
								buyTransaction.setExchangeRateAsDesiredPrice(desiredpriceforbuy);
								buyTransaction.setFees(fees);
								buyTransaction.setGrossAmount(buygrossamount);
								buyTransaction.setNetAmount(amountforbuy);
								buyTransaction.setStatus(TransactionStatus.COMPLETED);
								buyTransaction.setBuyOrder(buy);
								buyTransaction.setSellOrder(sell);
								buyTransaction.setBuyerId(buyerUserId);
								buyTransaction.setSellerId(sellerUserId);
								buyTransactionRepository.save(buyTransaction);

								ProfitBook equalcoinprofit=new ProfitBook();
								equalcoinprofit.setBuyerId(buyerUserId);
								equalcoinprofit.setFees(fees);
								equalcoinprofit.setProfitAmount(profitAmount);
								equalcoinprofit.setSellerId(sellerUserId);
								profitBookRepository.save(equalcoinprofit);
								//Admin wallet update
								 Long userId=(long) 2;
									Optional<User> user = userRepository.findById(userId);
									if (user.isPresent()) {
										User foundUser = user.get();
										FiatWallet newWalletsType = fiatWalletRepository.findByUser(foundUser);
										Double currentadminbalance=newWalletsType.getBalance();
										Double currentadminshadowbalance = newWalletsType.getShadowBalance();
																		
								if(buyerUserId==userId)
								{
									newWalletsType.setBalance(currentadminbalance+((buyamount) * fees / 100));
									newWalletsType.setShadowBalance(currentadminshadowbalance+((buyamount) * fees / 100));
									fiatWalletRepository.save(newWalletsType);
								}
								else
								{

									newWalletsType.setBalance(currentadminbalance+profitAmount);
									newWalletsType.setShadowBalance(currentadminshadowbalance+profitAmount);
									fiatWalletRepository.save(newWalletsType);
								}}
									
								logger.info("for same coin quantity saved");
								result.put("responseMessage", "success");
                                }
							}

							else if (buylistentry.getRemainingCoin() < selllistentry.getRemainingCoin()) {
								if(buyerUserId!=sellerUserId)
								{
								// buyer fiat balance
								Double currentfiatbalanceofbuyer = buyUserId.getFiatwallet().getBalance();
								Double buyamount = (desiredpriceforbuy * noOfCointobebuy);
								Double buygrossamount = ((buyamount) * fees / 100) + (buyamount);
								Double updatedbuyerFiatBalance = currentfiatbalanceofbuyer - buygrossamount;
								buyfwType.setBalance(updatedbuyerFiatBalance);
								fiatWalletRepository.save(buyfwType);
								// buyer cryptowallet balance
								Double currentcryptobalanceofbuyer = buycwType.getBalance();
								Double updatedbuyerCryptoBalance = currentcryptobalanceofbuyer + noOfCointobebuy;
								Double currentcryptoShadowbalanceofbuyer = buycwType.getShadowBalance();
								Double updatedbuyerCryptoShadowBalance = currentcryptoShadowbalanceofbuyer
										+ noOfCointobebuy;
								buycwType.setBalance(updatedbuyerCryptoBalance);
								buycwType.setShadowBalance(updatedbuyerCryptoShadowBalance);
								cryptoWalletRepository.save(buycwType);
								// seller fiat balance
								Double currentfiatbalanceofseller = sellUserId.getFiatwallet().getBalance();
								Double sellamount = (desiredpriceforsell * noOfCointobebuy);
								Double updatedsellerFiatBalance = currentfiatbalanceofseller + sellamount;
								sellfwType.setBalance(updatedsellerFiatBalance);
								Double currentfiatShadowbalanceofseller = sellUserId.getFiatwallet().getShadowBalance();
								Double updatedsellerFiatShadowBalance = currentfiatShadowbalanceofseller + sellamount;
								sellfwType.setShadowBalance(updatedsellerFiatShadowBalance);
								fiatWalletRepository.save(sellfwType);
								// seller crypto balance
								Double currentcryptobalanceofseller = sellcwType.getBalance();
								Double updatedsellerCryptoBalance = currentcryptobalanceofseller - noOfCointobebuy;
								sellcwType.setBalance(updatedsellerCryptoBalance);
								cryptoWalletRepository.save(sellcwType);
								//Profit
								Double profitAmount=buygrossamount-sellamount;
								// buy and order book maintain
								Double currentremainingcoinofbuy = buy.getRemainingCoin();
								Double updateremainingcoinofbuy = currentremainingcoinofbuy - noOfCointobebuy;
								buy.setRemainingCoin(updateremainingcoinofbuy);
								buy.setStatus(OrderStatus.COMPLETED);
								buyOrderRepository.save(buy);
								Double currentremainingcoinofsell = sell.getRemainingCoin();
								Double updateremainingcoinofsell = currentremainingcoinofsell - noOfCointobebuy;
								sell.setRemainingCoin(updateremainingcoinofsell);

								sellOrderRepository.save(sell);
								// Sell Transaction
								SellTransaction sellTransaction = new SellTransaction();
								sellTransaction.setCoinQuantity(noOfCointobebuy);
								sellTransaction.setCoinType(sellcoinName);
								sellTransaction.setExchangeRateAsDesiredPrice(desiredpriceforsell);
								sellTransaction.setFees((long) 0);
								sellTransaction.setGrossAmount(sellamount);
								sellTransaction.setNetAmount(sellamount);
								sellTransaction.setStatus(TransactionStatus.COMPLETED);
								sellTransaction.setBuyOrder(buy);
								sellTransaction.setSellOrder(sell);
								sellTransaction.setBuyerId(buyerUserId);
								sellTransaction.setSellerId(sellerUserId);
								sellTransactionRepository.save(sellTransaction);
								BuyTransaction buyTransaction = new BuyTransaction();
								buyTransaction.setCoinQuantity(noOfCointobebuy);
								buyTransaction.setCoinType(buycoinName);
								buyTransaction.setExchangeRateAsDesiredPrice(desiredpriceforbuy);
								buyTransaction.setFees(fees);
								buyTransaction.setGrossAmount(buygrossamount);
								buyTransaction.setNetAmount(amountforbuy);
								buyTransaction.setStatus(TransactionStatus.COMPLETED);
								buyTransaction.setBuyOrder(buy);
								buyTransaction.setSellOrder(sell);
								buyTransaction.setBuyerId(buyerUserId);
								buyTransaction.setSellerId(sellerUserId);
								buyTransactionRepository.save(buyTransaction);

								ProfitBook equalcoinprofit=new ProfitBook();
								equalcoinprofit.setBuyerId(buyerUserId);
								equalcoinprofit.setFees(fees);
								equalcoinprofit.setProfitAmount(profitAmount);
								equalcoinprofit.setSellerId(sellerUserId);
								profitBookRepository.save(equalcoinprofit);
								//Admin wallet update
								 Long userId=(long) 2;
									Optional<User> user = userRepository.findById(userId);
									if (user.isPresent()) {
										User foundUser = user.get();
										FiatWallet newWalletsType = fiatWalletRepository.findByUser(foundUser);
										Double currentadminbalance=newWalletsType.getBalance();
										Double currentadminshadowbalance = newWalletsType.getShadowBalance();
																		
								if(buyerUserId==userId)
								{
									newWalletsType.setBalance(currentadminbalance+((buyamount) * fees / 100));
									newWalletsType.setShadowBalance(currentadminshadowbalance+((buyamount) * fees / 100));
									fiatWalletRepository.save(newWalletsType);
								}
								else
								{

									newWalletsType.setBalance(currentadminbalance+profitAmount);
									newWalletsType.setShadowBalance(currentadminshadowbalance+profitAmount);
									fiatWalletRepository.save(newWalletsType);
								}}
								
								logger.info("for same coin quantity saved");
								result.put("responseMessage", "success");
								}
							} else if ((buylistentry.getRemainingCoin()).equals(selllistentry.getRemainingCoin())) {
								// logger.info("coin quantity match");
                            if(buyerUserId!=sellerUserId)
                            {
								// buyer fiat balance
								Double currentfiatbalanceofbuyer = buyUserId.getFiatwallet().getBalance();
								Double updatedbuyerFiatBalance = currentfiatbalanceofbuyer - grossamountbuy;
								buyfwType.setBalance(updatedbuyerFiatBalance);
								fiatWalletRepository.save(buyfwType);
								// buyer cryptowallet balance
								Double currentcryptobalanceofbuyer = buycwType.getBalance();
								Double updatedbuyerCryptoBalance = currentcryptobalanceofbuyer + noOfCointobesell;
								// buyer shadow balance
								Double currentcryptoshadowbalanceofbuyer = buycwType.getShadowBalance();
								Double updatedbuyerCryptoshadowbalanceofbuyer = currentcryptoshadowbalanceofbuyer
										+ noOfCointobesell;
								buycwType.setShadowBalance(updatedbuyerCryptoshadowbalanceofbuyer);
								buycwType.setBalance(updatedbuyerCryptoBalance);
								cryptoWalletRepository.save(buycwType);
								// seller fiat balance
								Double currentfiatbalanceofseller = sellUserId.getFiatwallet().getBalance();
								Double updatedsellerFiatBalance = currentfiatbalanceofseller + amountforsell;
								// seller fiat shadow balance
								Double currentfiatShadowbalanceofseller = sellUserId.getFiatwallet().getShadowBalance();
								Double updatedsellerFiatShadowBalance = currentfiatShadowbalanceofseller
										+ amountforsell;
								sellfwType.setShadowBalance(updatedsellerFiatShadowBalance);
								
								sellfwType.setBalance(updatedsellerFiatBalance);
								fiatWalletRepository.save(sellfwType);
								// seller crypto balance
								Double currentcryptobalanceofseller = sellcwType.getBalance();
								Double updatedsellerCryptoBalance = currentcryptobalanceofseller - noOfCointobesell;
								sellcwType.setBalance(updatedsellerCryptoBalance);
								cryptoWalletRepository.save(sellcwType);
								//Profit
								Double profitAmount=grossamountbuy-amountforsell;
								
								// buy and order book maintain
								Double currentremainingcoinofbuy = buy.getRemainingCoin();
								Double updateremainingcoinofbuy = currentremainingcoinofbuy - noOfCointobesell;
								buy.setRemainingCoin(updateremainingcoinofbuy);
								OrderStatus status = buy.getStatus();
								buy.setStatus(status.COMPLETED);
								buyOrderRepository.save(buy);
								Double currentremainingcoinofsell = sell.getRemainingCoin();
								Double updateremainingcoinofsell = currentremainingcoinofsell - noOfCointobebuy;
								sell.setRemainingCoin(updateremainingcoinofsell);
								sell.setStatus(OrderStatus.COMPLETED);
								sellOrderRepository.save(sell);
								// Sell Transaction
								SellTransaction sellTransaction = new SellTransaction();
								sellTransaction.setCoinQuantity(noOfCointobesell);
								sellTransaction.setCoinType(sellcoinName);
								sellTransaction.setExchangeRateAsDesiredPrice(desiredpriceforsell);
								sellTransaction.setFees((long) 0);
								sellTransaction.setGrossAmount(amountforsell);
								sellTransaction.setNetAmount(amountforsell);
								sellTransaction.setStatus(TransactionStatus.COMPLETED);
								sellTransaction.setBuyOrder(buy);
								sellTransaction.setSellOrder(sell);
								sellTransaction.setBuyerId(buyerUserId);
								sellTransaction.setSellerId(sellerUserId);
								sellTransactionRepository.save(sellTransaction);
								BuyTransaction buyTransaction = new BuyTransaction();
								buyTransaction.setCoinQuantity(noOfCointobebuy);
								buyTransaction.setCoinType(buycoinName);
								buyTransaction.setExchangeRateAsDesiredPrice(desiredpriceforbuy);
								buyTransaction.setFees(fees);
								buyTransaction.setGrossAmount(grossamountbuy);
								buyTransaction.setNetAmount(amountforbuy);
								buyTransaction.setStatus(TransactionStatus.COMPLETED);
								buyTransaction.setBuyOrder(buy);
								buyTransaction.setSellOrder(sell);
								buyTransaction.setBuyerId(buyerUserId);
								buyTransaction.setSellerId(sellerUserId);
								buyTransactionRepository.save(buyTransaction);
								
								ProfitBook equalcoinprofit=new ProfitBook();
								equalcoinprofit.setBuyerId(buyerUserId);
								equalcoinprofit.setFees(fees);
								equalcoinprofit.setProfitAmount(profitAmount);
								equalcoinprofit.setSellerId(sellerUserId);
								profitBookRepository.save(equalcoinprofit);
								//Admin wallet update
								 Long userId=(long) 2;
									Optional<User> user = userRepository.findById(userId);
									if (user.isPresent()) {
										User foundUser = user.get();
										FiatWallet newWalletsType = fiatWalletRepository.findByUser(foundUser);
										Double currentadminbalance=newWalletsType.getBalance();
										Double currentadminshadowbalance = newWalletsType.getShadowBalance();
																		
								if(buyerUserId==userId)
								{
									newWalletsType.setBalance(currentadminbalance+((amountforbuy) * fees / 100));
									newWalletsType.setShadowBalance(currentadminshadowbalance+((amountforbuy) * fees / 100));
									fiatWalletRepository.save(newWalletsType);
								}
								else
								{

									newWalletsType.setBalance(currentadminbalance+profitAmount);
									newWalletsType.setShadowBalance(currentadminshadowbalance+profitAmount);
									fiatWalletRepository.save(newWalletsType);
								}}
								
								
								logger.info("for same coin quantity saved");
								result.put("responseMessage", "success");
							}}
						}

					}
				}
			}
		}
		return result;

	}
}
