<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.foodApplication.pack.Cart" %>
<%@ page import="com.dao.foodApplication.model.Restaurant" %>
<%@ page import="com.dao.foodApplication.model.CartItem" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your Shopping Cart</title>
    <link rel="stylesheet" href="cart.css">
</head>
<body>
    <div class="cart-container">
        <h1 class="cart-title">My Cart</h1>
        <%
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null || cart.getItems().isEmpty()) {
        %>
            <p class="empty-cart-message">Your cart is empty!</p>
            <a class="back-to-menu" href="Menu.jsp">Go back to Menu</a>
        <%
            } else {
                Map<Integer, CartItem> items = cart.getItems();
        %>
            <table class="cart-table">
                <thead>
                    <tr>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (CartItem item : items.values()) {
                    %>
                    <tr>
                        <td><%= item.getName() %></td>
                        <td>₹<%= item.getPrice() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td>₹<%= item.getPrice() * item.getQuantity() %></td>
                        <td>
                            <form action="CartServlet" method="post" class="action-form">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                                <input type="number" name="quantity" min="1" value="<%= item.getQuantity() %>">
                                <button class="action-button" type="submit">Update</button>
                            </form>
                            <form action="CartServlet" method="post" class="action-form">
                                <input type="hidden" name="action" value="remove">
                                <input type="hidden" name="itemId" value="<%= item.getItemId() %>">
                                <button class="action-button remove-button" type="submit">Remove</button>
                            </form>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <div class="cart-summary">
                <h3>Total Amount: ₹<%
                    int total = 0;
                    for (CartItem item : items.values()) {
                        total += item.getPrice() * item.getQuantity();
                    }
                    out.print(total);
                %></h3>
                <form action="CartServlet" method="post" class="clear-cart-form">
                    <input type="hidden" name="action" value="clear">
                    <button class="clear-cart-button" type="submit">Clear Cart</button>
                </form>
            </div>
            <a href="GetMenu?restaurantId="></a>
            <a class="continue-shopping" href="Menu.jsp">Add More Items</a>
            <a class="continue-shopping" href="CheckOut.jsp">Check Out</a>
        <%
            }
        %>
    </div>
</body>
</html>
