package com.dao.foodApplication.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.dao.foodApplication.interfaces.MenuDAO;
import com.dao.foodApplication.model.Menu;
import com.dao.foodApplication.model.Restaurant;
import com.db.utill.DBConnection;

public class MenuDAOImpl implements MenuDAO {
	
	 ArrayList<Menu> menuList = new ArrayList<Menu>();
	
	private static final String INSERTQUERY = "INSERT INTO MENU(menuId,restaurantId,name,description,price,isAvailable,imgPath) VALUES(?,?,?,?,?,?,?)";
	private static final String FETCHALL = "SELECT * FROM MENU";
	private static final String FETCHONE = "SELECT * FROM MENU WHERE menuId = ?";
	private static final String UPDATE = "UPDATE MENU SET PRICE = ? WHERE menuId = ?";
	private static final String DELETE = "DELETE FROM MENU WHERE menuId = ?";
	private static final String FETCHMENUBYID = "SELECT * FROM MENU WHERE restaurantId = ?";
	
	static Connection con;
	static
	{
		con = DBConnection.connect();
	}


	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;

	private Menu menu;
	
	
	@Override
	public int insert(Menu m) {
		
		try
		{
			pstmt = con.prepareStatement(INSERTQUERY);
			pstmt.setInt(1, m.getMenuId());
			pstmt.setInt(2, m.getRestaurantId());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getDescription());
			pstmt.setInt(5, m.getPrice());
			pstmt.setBoolean(6, m.isAvailable());
			pstmt.setString(7, m.getImgPath());
			
			return pstmt.executeUpdate();
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public ArrayList<Menu> fetchAll() {
		
		try
		{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(FETCHALL);
			
			menuList = extractUserListFromResultSet(resultSet);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return menuList;
		
		
		
	}
	
	
	ArrayList<Menu> extractUserListFromResultSet(ResultSet resultSet)
	{
		try
		{
			while(resultSet.next())
			{
				menuList.add(
						new Menu(
									resultSet.getInt("menuId"),
									resultSet.getInt("restaurantId"),
									resultSet.getString("name"),
									resultSet.getString("description"),
									resultSet.getInt("price"),
									resultSet.getBoolean("isAvailable"),
									resultSet.getString("imgPath")
									
								)
						);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return menuList;
	}


	@Override
	public Menu fetchOne(int menuId) {
		try
		{
			pstmt = con.prepareStatement(FETCHONE);
			pstmt.setInt(1, menuId);
			resultSet = pstmt.executeQuery();
			
			menuList = extractUserListFromResultSet(resultSet);
			menu = menuList.get(0);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return menu;
	}


	@Override
	public int update(int menuId, int price) {
		
		try
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, price);
			pstmt.setInt(2, menuId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public int delete(int menuId) {
		
		try
		{
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, menuId);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public ArrayList<Menu> fetchMenyById(int restaurantId) {
		try 
		{
			pstmt = con.prepareStatement(FETCHMENUBYID);
			pstmt.setInt(1, restaurantId);
			resultSet = pstmt.executeQuery();
			menuList = extractUserListFromResultSet(resultSet);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return menuList;
	}


}
