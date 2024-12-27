 package com.dao.foodApplication.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.dao.foodApplication.interfaces.UserDAO;
import com.dao.foodApplication.model.User;
import com.db.utill.DBConnection;

public class UserDAOImpl implements UserDAO {
	
	static ArrayList<User> userList = new ArrayList<User>();
	
	private static final String INSERTQUERY = "INSERT INTO USER(userName,email,password,confirmPassword,address) VALUES(?,?,?,?,?)";
	private static final String FETCHALL = "SELECT * FROM USER";
	private static final String FETCHONE = "SELECT * FROM USER WHERE userId = ?";
	private static final String UPDATE = "UPDATE USER SET address = ? WHERE UserId = ?";
	private static final String DELETE = "DELETE FROM USER WHERE UserId = ?";
	private static final String CHECKEMAIL =	"SELECT * FROM USER WHERE email = ?";
	static Connection con;
	static 
	{ 
		con = DBConnection.connect();
	}
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet resultSet;

	private User user;
	@Override
	public int insert(User u) {
		
		try
		{
			pstmt = con.prepareStatement(INSERTQUERY);
//			pstmt.setInt(1, u.getUserId());
			pstmt.setString(1, u.getUserName());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getConfirmPassword());
			pstmt.setString(5, u.getAddress());
			
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	@Override
	public ArrayList<User> fetchall() {
		
		try
		{
			stmt = con.createStatement();
			resultSet = stmt.executeQuery(FETCHALL);
			
			userList  = extractUserListFromResultSet(resultSet);
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return userList;

		
		
	}


	@Override
	public User fetchOne(int i) {
		
		try
		{
			pstmt = con.prepareStatement(FETCHONE);
			pstmt.setInt(1, i);
			resultSet = pstmt.executeQuery();
			
			userList = extractUserListFromResultSet(resultSet);
			user = userList.get(0);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;
	
	}
	
	
	ArrayList<User> extractUserListFromResultSet(ResultSet resultSet)
	{
		try
		{
			while(resultSet.next())
			{
				userList.add(
						new User(
										
								resultSet.getString("userName"),
								resultSet.getString("email"),
								resultSet.getString("password"),
								resultSet.getString("address")
								
								
								
								)
						
						
						);
				
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return userList;
	}


	@Override
	public int update(int id, String address) {
		
		try
		{
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, address);
			pstmt.setInt(2, id);
			return pstmt.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}


	@Override
	public int delete(int id) {
		
		try
		{
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


		return 0;
	}



    @Override
    public User getUserByEmail(String email) {
        User user = null; // Local user variable to avoid issues with static data
        try {
            pstmt = con.prepareStatement(CHECKEMAIL);
            pstmt.setString(1, email);
            resultSet = pstmt.executeQuery();
            
            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("userName"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("address")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user; // Returns null if no user found.
    }
	
	
	

}
