package com.foodApplication.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.dao.foodApplication.impl.MenuDAOImpl;
import com.dao.foodApplication.interfaces.MenuDAO;
import com.dao.foodApplication.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/GetMenu")
public class MenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get restaurantId from request parameter
        String restaurantIdParam = req.getParameter("restaurantId");
        
        if (restaurantIdParam != null) {
            try {
                int restaurantId = Integer.parseInt(restaurantIdParam);

                // Fetch menu list for the restaurant
                MenuDAO menuDAO = new MenuDAOImpl();
                ArrayList<Menu> menuList = menuDAO.fetchMenyById(restaurantId);

                // Set menuList in session or request
                HttpSession session = req.getSession();
                session.setAttribute("menuList", menuList);

                // Forward to menu.jsp
                req.getRequestDispatcher("Menu.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid restaurant ID");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Restaurant ID is required");
        }
    }
}
