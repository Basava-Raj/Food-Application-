package com.foodApplication.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.dao.foodApplication.impl.UserDAOImpl;
import com.dao.foodApplication.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	

	private UserDAOImpl userDAO;
	
	
	
	@Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl(); // Initialize DAO implementation
    }

	


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    
	    String email = req.getParameter("email");
	    String password = req.getParameter("password");
	    
	    try {
	        User user = userDAO.getUserByEmail(email);
	        ArrayList<User> userDetail = userDAO.fetchall();
	        
	        if (user != null) 
	        {
	            if (password.equals(user.getPassword())) 
	            {
	                Cookie emailCookie = new Cookie("email", email);
	                resp.addCookie(emailCookie);
	                
	                session.setAttribute("name", user.getUserName());
	                session.setAttribute("email", user.getEmail());
	                session.setAttribute("address", user.getAddress());
	                session.setAttribute("userId",user.getUserId());
	                System.out.println(user.getUserId());
	                session.setAttribute("loggedUser", userDetail);
	                System.out.println(userDetail);
	                
	                
	                req.getRequestDispatcher("GetRestaurant").forward(req, resp);
	            } 
	            else 
	            {
	                resp.getWriter().println("<h1>Password Incorrect</h1>");
	            }
	        } 
	        else 
	        {
	            resp.getWriter().println("<h1>No such email has been registered.</h1>");
	        }
	    } catch (Exception e) 
	    {
	        e.printStackTrace();
	        resp.getWriter().println("<h1>An error occurred while processing your request.</h1>");
	    }
	}

	
}
