
package com.foodApplication.controller;

import java.io.IOException;

import com.dao.foodApplication.impl.UserDAOImpl;
import com.dao.foodApplication.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/FirstServlet")
public class CollectData extends HttpServlet
{
	

	private UserDAOImpl udaoi;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		String password = req.getParameter("pswd");
		
		if(password.equals(req.getParameter("cPswd")))
		{
			String userName = req.getParameter("userName");
			String email = req.getParameter("email");
//			String cPassword = req.getParameter("cPswd");
			String address = req.getParameter("address");
			
			udaoi = new UserDAOImpl();
			udaoi.insert( new User(userName,email,password,address));
			
			resp.sendRedirect("index.html");
		}
		else
		{
			resp.getWriter().println("Password missmatch");
		}
	}
		
		
}

