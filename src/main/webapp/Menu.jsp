<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.dao.foodApplication.model.Menu" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
    
    <link rel="stylesheet" type="text/css" href="Menu.css">
    <link rel="stylesheet" href="home.css">
</head>
<body>

	 <!-- Navigation Bar -->
    <nav class="navbar">
        <div class="logo">FoodApp</div>
        <ul class="nav-links">
            <li><a href="home.jsp">Home</a></li>
            <li><a href="home.jsp">Restaurants</a></li>
            <li><a href="orders.jsp">Orders</a></li>
            <li><a href=ProfileServlet>Profile</a></li>
            <li><a href="LogoutServlet">Logout</a></li>
        </ul>
    </nav>


    <h1>Menu</h1>
    <%
        ArrayList<Menu> menuList = (ArrayList<Menu>) session.getAttribute("menuList");
        if (menuList != null && !menuList.isEmpty()) {
    %>
        <div class="menu-container">
            <%
                for (Menu menu : menuList) {
            %>
            <div class="menu-card">
            
                <img src="<%= request.getContextPath() + menu.getImgPath() %>" alt="<%= menu.getName() %>">
                <h3><%= menu.getName() %></h3>
                <p><%= menu.getDescription() %></p>
                <p class="price">₹<%= menu.getPrice() %></p>
                <p class="availability <%= menu.isAvailable() ? "" : "not-available" %>">
                    <%= menu.isAvailable() ? "Available" : "Not Available" %>
                </p>
                
                <form action="CartServlet" method="post">
	                <input type="hidden" name="action" value="add">
	                <input type="hidden" name="itemId" value="<%= menu.getMenuId()%>">
	                <input type="hidden" name="quantity" value="1">
	                <button type="submit" class= "add-to-cart">Add to Cart</button>
                
            	</form>
            </div>
            <%
                }
            %>
        </div>
    <%
        } else {
    %>
        <p class="no-menu">No menu items available for this restaurant.</p>
    <%
        }
    %>
</body>
</html>
