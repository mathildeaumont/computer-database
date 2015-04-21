package com.excilys.persistence;

import com.excilys.model.User;

public interface UserDao {

	public void createUser(User user);
	
	public User getUser(String login);
	
}
