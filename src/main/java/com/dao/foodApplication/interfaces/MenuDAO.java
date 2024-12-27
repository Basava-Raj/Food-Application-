package com.dao.foodApplication.interfaces;

import java.util.ArrayList;

import com.dao.foodApplication.model.Menu;

public interface MenuDAO {
	
	int insert(Menu m);
	ArrayList<Menu> fetchAll();
	Menu fetchOne(int menuId);
	int update(int menuId, int price);
	int delete(int menuId);
	ArrayList<Menu> fetchMenyById(int restaurantId);

}
