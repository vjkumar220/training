package com.oodles.services;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.enums.OrderStatus;
import com.oodles.models.BuyOrder;
import com.oodles.models.SellOrder;
import com.oodles.repository.BuyOrderRepository;
import com.oodles.repository.SellOrderRepository;

@Service
public class OrderMatchingService {
	private Logger logger = LoggerFactory.getLogger(OrderMatchingService.class);
	@Autowired
	private BuyOrderRepository buyOrderRepository;
	@Autowired
	private SellOrderRepository sellOrderRepository;
	
	/*public void ordermatching()
	{
		List<BuyOrder> result = buyOrderRepository.findbyStatus((OrderStatus.PENDING).toString());
	Collections.sort(result);
		List<SellOrder> results = sellOrderRepository.findbyStatus((OrderStatus.PENDING).toString());
		Collections.sort(results);
	*/
	
	/*public TreeMap<String,BuyOrder> retrieveAllBuyOrder() {
		TreeMap<String,BuyOrder> result =  buyOrderRepository.findbyStatus((OrderStatus.PENDING).toString());
		 Map sortedMap = sortByValues(result);
		 
		    // Get a set of the entries on the sorted map
		    Set set = sortedMap.entrySet();
		return result;
	}
	

	private Map sortByValues(TreeMap<String, BuyOrder> result) {
		// TODO Auto-generated method stub
		return null;
	}


	public TreeMap<String,SellOrder> retrieveAllSellOrder() {
		TreeMap<String,SellOrder> result = sellOrderRepository.findbyStatus((OrderStatus.PENDING).toString());
		
		return result;
	}
      
	*/
	
	//}	
}
