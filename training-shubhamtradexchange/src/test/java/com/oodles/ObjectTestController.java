/*package com.oodles;

import org.apache.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.oodles.models.User;

public class ObjectTestController {
	


		 static final String URL_CREATE_USER = "http://localhost:8080/v1/signup";
		 
		    public static void main(String[] args) {
		 
		       // User newUser = new User("Jesus","j@c.com","7896543212", "Christ$5", "USA");
		    	
		    	 User newUser = new User("Mary","m@India.com","9876543212", "JesusCt@12", "Japan");
		    	
		        RestTemplate restTemplate = new RestTemplate();
		 
		        
		        // Data attached to the request.
		        HttpEntity <User>requestBody = new HttpEntity<>(newUser);
		 
		        // Send request with POST method.
		        ResponseEntity<User> result 
		             = restTemplate.postForEntity(URL_CREATE_USER, requestBody, User.class);
		 
		        System.out.println("Status code:" + result.getStatusCode());
		 
		        // Code = 200.
		        if (result.getStatusCode() == HttpStatus.OK) {
		            User u=result.getBody();
		            System.out.println("(Client Side) User Id: "+ u.getId());
		            System.out.println("(Client Side) User Name: "+ u.getName());
		            System.out.println("(Client Side) User mobilenumber: "+ u.getMobilenumber());
		            System.out.println("(Client Side) User email: "+ u.getEmail());
		            System.out.println("(Client Side) User Country "+ u.getCountry());
		        }
		 
		    }
		 
		}



*/