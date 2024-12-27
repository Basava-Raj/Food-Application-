package com.foodApplication.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.dao.foodApplication.impl.RestaurantDAOImpl;
import com.dao.foodApplication.interfaces.RestaurantDAO;
import com.dao.foodApplication.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GetRestaurant")
public class GetRestaurant extends HttpServlet {
    
    private ArrayList<Restaurant> restaurantList;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        // Safe cookie check to avoid NullPointerException
        if (req.getCookies() != null && req.getCookies().length > 0 && req.getCookies()[0].getValue() != null) {
            RestaurantDAO rdaoi = new RestaurantDAOImpl();
            
            restaurantList = rdaoi.fetchAll();
            
            HttpSession session = req.getSession();
            
            session.setAttribute("restaurantList", restaurantList);
            resp.sendRedirect("home.jsp");
        } else {
            resp.sendRedirect("index.html");
        }
    }
}
