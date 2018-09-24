package com.oodles.task;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oodles.domain.User;
import com.oodles.repository.UserRepository;
import com.oodles.service.OrderMatchingService;

@Service
@Transactional
public class OrderMatchingTask {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderMatchingService service;

	private long k;

	@Scheduled(cron = "${ordermatching.cron.expression}")
	public void sayHello() {
		String result = service.orderMatch();
		System.out.println(result);
		Optional<User> value = userRepository.findById(k++);
		if (value.isPresent()) {
			User user = value.get();
			System.out.println("Hey " + user.getName());
		} else
			System.out.println("nothing doing");

	}
}