/*package com.oodles.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oodles.domain.User;
import com.oodles.repository.UserDAO;

import antlr.collections.List;

@Service
@Transactional
public class UserServiceImpl  implements UserService {
	 
	    @Autowired
	    private UserDAO userDAO;
	 
	    @Override
	    @Transactional
	    public void addUser(User user) {
	        userDAO.addUser(user);
	    }
	 
	    @Override
	    @Transactional
	    public List<User> getAllUser() {
	        return userDAO.getAllUser();
	    }
	 
	    @Override
	    @Transactional
	    public void deleteUser(Integer userId) {
	       userDAO.deleteUser(userId);
	    }
	 
	    public User getUser(int userid) {
	        return userDAO.getUser(userid);
	    }
	 
	    public User updateUser(User user) {
	        
	        return userDAO.updateUser(user);
	    }
	 
	    public void setUserDAO(UserDAO userDAO) {
	        this.userDAO = userDAO;
	    }
	 
	}
*/
