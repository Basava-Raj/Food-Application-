package com.dao.foodApplication.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.dao.foodApplication.interfaces.RestaurantDAO;
import com.dao.foodApplication.model.Restaurant;
import com.db.utill.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {
	
	static ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	
	
	private static final String INSERTQUERY = "INSERT INTO RESTAURANT(restaurantId,name,cuisineType,deliveryTime,address,ratings,isActive,imgPath) VALUES(?,?,?,?,?,?,?,?)";
	private static final String FETCHALL = "SELECT * FROM RESTAURANT";
	private static final String FETCHONE = "SELECT * FROM RESTAURANT WHERE restaurantId = ?";
	private static final String UPDATE = "UPDATE RESTAURANT SET isActive = ? WHERE restaurantId = ?";
	private static final String DELETE = "DELETE FROM RESTAURANT WHERE restaurantId = ?";
	static Connection con;
	static
	{
		con = DBConnection.connect();
	}

	private PreparedStatement pstmt;
	private Statement stmt;


	private ResultSet resultSet;


	private Restaurant restaurant;

	@Override
	public int insert(Restaurant r) {
		
		try
		{
			pstmt = con.prepareStatement(INSERTQUERY);
			pstmt.setInt(1, r.getRestaurantId());
			pstmt.setString(2, r.getName());
			pstmt.setString(3, r.getCuisineType());
			pstmt.setInt(4, r.getDeliveryTime());
			pstmt.setString(5, r.getAddress());
			pstmt.setFloat(6, r.getRatings());
			pstmt.setBoolean(7, r.isActive());
			pstmt.setString(8, r.getImgPath());
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return 0;
	}

	@Override
	public ArrayList<Restaurant> fetchAll() {
		
		try
		{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(FETCHALL);
			
			restaurantList = extractUserListFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return restaurantList;
		
	}
	
	ArrayList<Restaurant> extractUserListFromResultSet(ResultSet resultSet)
	{
		try
		{
			while(resultSet.next())
			{
				restaurantList.add(
						new Restaurant(
									resultSet.getInt("restaurantId"),
									resultSet.getString("name"),
									resultSet.getString("cuisineType"),
									resultSet.getInt("deliveryTime"),
									resultSet.getString("address"),
									resultSet.getFloat("ratings"),
									resultSet.getBoolean("isActive"),
									resultSet.getString("imgPath")
								)
						);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return restaurantList;
	}

	@Override
	public Restaurant fetchOne(int id) {
		
		try
		{
			pstmt = con.prepareStatement(FETCHONE);
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();
			
			restaurantList = extractUserListFromResultSet(resultSet);
			restaurant = restaurantList.get(0);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return restaurant;
	}

	@Override
	public int update(int restaurantId, boolean isActive) {
		
		try
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setBoolean(1, isActive);
			pstmt.setInt(2, restaurantId);
			
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delete(int restaurantId) {
		
		try
		{
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, restaurantId);
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

}
