package com.enotes.service;

import com.enotes.entities.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public boolean existEmailCheck(String email);
}
