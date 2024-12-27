package com.dao.foodApplication.interfaces;

import java.util.ArrayList;

import com.dao.foodApplication.model.User;

public interface UserDAO {
	int insert(User u);
	
	ArrayList<User> fetchall();
	
	User fetchOne(int i);
	
	int update(int id, String address);
	
	int delete(int id);
	
	User getUserByEmail(String email);

}
