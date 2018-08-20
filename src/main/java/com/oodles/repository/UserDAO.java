package com.oodles.repository;



import java.util.List;

import com.oodles.domain.User;

public interface UserDAO {
	 
    public void addUser(User user);
 
    public List<User> getAllUser();
 
    public void deleteUser(Integer userId);
 
    public User updateUser(User user);
 
    public User getUser(int userid);
}
