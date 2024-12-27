package com.foodApplication.controller;

import java.io.IOException;

import com.dao.foodApplication.impl.MenuDAOImpl;
import com.dao.foodApplication.interfaces.MenuDAO;
import com.dao.foodApplication.model.CartItem;
import com.dao.foodApplication.model.Menu;
import com.foodApplication.pack.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // STEP 1: Creating Session to store cart
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // STEP 2: Check if the cart exists or not (if not, create one)
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // STEP 3: Fetch request parameters
        String action = req.getParameter("action");
        int itemId = Integer.parseInt(req.getParameter("itemId"));

        // STEP 4: Create a MenuDAO object (for fetching menu item details)
        MenuDAO menuDAO = null;
        try {
            menuDAO = new MenuDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            switch (action) {
                case "add": {
                    Menu menuItem = menuDAO.fetchOne(itemId);
                    if (menuItem != null) {
                        int quantity = Integer.parseInt(req.getParameter("quantity"));
                        CartItem cartItem = new CartItem(menuItem.getMenuId(), menuItem.getRestaurantId(),
                                menuItem.getName(), quantity, menuItem.getPrice());
                        cart.addItem(cartItem);
                    }
                    break;
                }
                case "update": {
                    int quantity = Integer.parseInt(req.getParameter("quantity"));
                    cart.updateItem(itemId, quantity);
                    break;
                }
                case "remove": {
                    cart.removeItem(itemId);
                    break;
                }
                case "clear": {
                    cart.clearCart();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Redirect to cart.jsp to view the updated cart
        resp.sendRedirect("cart.jsp");
    }
}
