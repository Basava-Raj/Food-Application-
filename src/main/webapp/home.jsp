<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.dao.foodApplication.model.Restaurant" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Restaurant Details</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
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

    <!-- Welcome Section -->
    <h1>Welcome To Our TastyTribe..!</h1>

    <!-- Restaurant Cards -->
    <div class="container">
        <% 
            List<Restaurant> rList = (List<Restaurant>) session.getAttribute("restaurantList");
            if (rList != null) {
                for (Restaurant r : rList) {
        %>
            <div class="restaurant-card">
                <a href="<%= request.getContextPath() %>/GetMenu?restaurantId=<%= r.getRestaurantId() %>">
                    <img class="restaurant-image" src="<%= request.getContextPath() + r.getImgPath() %>" alt="<%= r.getName() %>">
                </a>
                <div class="restaurant-details">
                    <h2 class="restaurant-name"><%= r.getName()%></h2>
                    <div class="cuisine-delivery">
                        <span class="restaurant-info"><%= r.getCuisineType() %></span>
                        <span class="restaurant-info"><%= r.getDeliveryTime() %> mins</span>
                    </div>
                    <div class="restaurant-info"><%= r.getAddress() %></div>
                    <div>
                        <span class="rating"><%= r.getRatings() %> ★</span>
                        <span class="status <%= r.isActive() ? "active" : "" %>">
                            <%= r.isActive() ? "Open" : "Closed" %>
                        </span>
                    </div>
                </div>
            </div>
        <% 
                }
            } else {
        %>
            <p>No restaurants available.</p>
        <% } %>
    </div>

</body>
</html>
