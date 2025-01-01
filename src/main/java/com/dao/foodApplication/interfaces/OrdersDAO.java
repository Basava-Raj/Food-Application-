package com.dao.foodApplication.interfaces;

import java.util.ArrayList;

import com.dao.foodApplication.model.Orders;

public interface OrdersDAO {
	
	int insert(Orders o);
	ArrayList<Orders> fetchAll();
	Orders fetchOne(int orderId);
	
	int update(int orderId, String paymentMode);
	
	int delete(int orderId);

}
