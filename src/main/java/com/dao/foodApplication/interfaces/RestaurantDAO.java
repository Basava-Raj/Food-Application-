package com.dao.foodApplication.interfaces;

import java.util.ArrayList;

import com.dao.foodApplication.model.Restaurant;

public interface RestaurantDAO {
	
	int insert(Restaurant r);
	ArrayList<Restaurant> fetchAll();
	Restaurant fetchOne(int id);
	
	int update(int restaurantId, boolean isActive);
	
	int delete(int restaurantId);

}
