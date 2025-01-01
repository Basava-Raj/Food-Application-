package com.foodApplication.controller;

import java.io.IOException;

import com.dao.foodApplication.impl.OrdersDAOImpl;
import com.dao.foodApplication.interfaces.OrdersDAO;
import com.dao.foodApplication.model.Orders;
import com.foodApplication.pack.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	
	
	
	private OrdersDAO orderDAO;
	@Override
		public void init()  {
			try
			{
				orderDAO	= new OrdersDAOImpl();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("CheckOutServlet is initiated");
		HttpSession session = req.getSession();
	 
		Cart cart = (Cart) session.getAttribute("cart");
		   

		Integer user = (Integer) session.getAttribute("loggedUser");
	

		System.out.println("Cart: " + cart);
		System.out.println("UserId: " + user);
	  
		if(cart!= null && user != null && !cart.getItems().isEmpty())
		{
//			Extract checkout from data
			String paymentmethod = req.getParameter("paymentmethod");
			
//			Create and populate the order object
			Orders order = new Orders();
			order.setUserId(user);
			
			System.out.println(session.getAttribute("restaurantId"));
			System.out.println(Integer.parseInt(String)session.getAttribute("restaurantId"));
		}
		  
		  
		  
	}
	

}
