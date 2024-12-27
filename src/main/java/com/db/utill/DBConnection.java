package com.db.utill;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	private static Connection con;

	public static Connection connect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3310/foodapplication", "root", "Basava@2002");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}

}
