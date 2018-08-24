package com.oodles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.oodles.domain.Order;
import com.oodles.domain.Users;
import com.oodles.services.UserOrderService;

@Controller
public class UserOrderController {
	@Autowired
private UserOrderService userorderservice;

    @GetMapping(value = "/all")
    public List<Order> getUserContact() {
    	List<Order> result =userorderservice.one();
        return result;
        }
}

	
	
	
	
	
	