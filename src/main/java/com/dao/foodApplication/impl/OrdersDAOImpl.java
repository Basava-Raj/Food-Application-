package com.dao.foodApplication.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.dao.foodApplication.interfaces.OrdersDAO;
import com.dao.foodApplication.model.Orders;
import com.dao.foodApplication.model.Restaurant;
import com.db.utill.DBConnection;

public class OrdersDAOImpl implements OrdersDAO {
	
	static ArrayList<Orders> ordersList = new ArrayList<Orders>();
	
	
	private static final String INSERTQUERY = "INSERT INTO ORDERS(orderId,userId,restaurantId,totalAmount,status,paymentMode) VALUES(?,?,?,?,?,?)";
	private static final String FETCHALL = "SELECT * FROM ORDERS";
	private static final String FETCHONE = "SELECT * FROM ORDERS WHERE ORDERId = ?";
	private static final String UPDATE = "UPDATE ORDERS SET PAYMENTMODE = ? WHERE ORDERId = ?";
	private static final String DELETE = "DELETE FROM ORDERS WHERE ORDERId = ?";
	
	static Connection con;
	static
	{
		con = DBConnection.connect();
	}
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;


	private Orders order;



	@Override
	public int insert(Orders o) {
		
		try
		{
			pstmt = con.prepareStatement(INSERTQUERY, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, o.getOrderId());
			pstmt.setInt(2, o.getUserId());
			pstmt.setInt(3, o.getRestaurantId());
			pstmt.setDouble(4, o.getTotalAmount());
			pstmt.setString(5, o.getStatus());
			pstmt.setString(6, o.getPaymentMode());
			
			int affectedArrows = pstmt.executeUpdate();
			
			ResultSet res1 = pstmt.getGeneratedKeys();
			int orderId = 0;
			while(res1.next())
			{
				orderId = res1.getInt(1);
			}
			return orderId;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	@Override
	public ArrayList<Orders> fetchAll() {

		try
		{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(FETCHALL);
			ordersList = extractUserListFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ordersList;
	}
	
	
	ArrayList<Orders> extractUserListFromResultSet(ResultSet resultSet)
	{
		try
		{
			while(resultSet.next())
			{
				ordersList.add(
						new Orders(
									resultSet.getInt("orderId"),
									resultSet.getInt("userId"),
									resultSet.getInt("restaurantId"),
									resultSet.getFloat("totalAmount"),
									resultSet.getString("status"),
									resultSet.getString("paymentMode")
								)
						);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return ordersList;
	}
	@Override
	public Orders fetchOne(int orderId) {
		try
		{
			pstmt = con.prepareStatement(FETCHONE);
			pstmt.setInt(1, orderId);
			
			resultSet = pstmt.executeQuery();
			
			ordersList = extractUserListFromResultSet(resultSet);
			order = ordersList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return order;
	}
	@Override
	public int update(int orderId, String paymentMode) {
		
		try
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, paymentMode);
			pstmt.setInt(2, orderId);
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public int delete(int orderId) {
		
		try
		{
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, orderId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}


}
