<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
    <link rel="stylesheet" href="home.css"> <!-- Link to your CSS file -->
    <link rel="stylesheet" href="profile.css"> 
</head>
<body>

    <!-- Navigation Bar -->
    <nav class="navbar">
        <div class="logo">FoodApp</div>
        <ul class="nav-links">
            <li><a href="home.jsp">Home</a></li>
            <li><a href="home.jsp">Restaurants</a></li>
            <li><a href="orders.jsp">Orders</a></li>
            <li><a href="ProfileServlet">Profile</a></li>
            <li><a href="LogoutServlet">Logout</a></li>
        </ul>
    </nav>

    <!-- Profile Section -->
    <div class="profile-container">
        <h1>Your Profile</h1>
        <div class="profile-card">
            <p><strong>Name:</strong> <%= request.getAttribute("name") %></p>
            <p><strong>Email:</strong> <%= request.getAttribute("email") %></p>
            <p><strong>Address:</strong> <%= request.getAttribute("address") %></p>
        </div>
    </div>

</body>
</html>
