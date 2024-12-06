<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%
    // Set headers to prevent caching
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);

    // If the user is already logged in, redirect to admin page
    String email = (session != null) ? (String) session.getAttribute("user") : null;

    if (email != null) {
        response.sendRedirect("userpage.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Login</title>
    <link rel="stylesheet" href="bootstrap.min.css"/>
    <style>
        /* Center the form horizontally and position it at the top */
        .top-center-form {
            display: flex;
            justify-content: center; /* Center horizontally */
            margin-top: 50px; /* Space from the top of the screen */
        }
        .form-container {
            width: 300px; /* Set the width of the form */
        }
    </style>
</head>
<body>

<div class="container-fluid top-center-form">
    <div class="form-container">
        <h1 class="text-center">User Login</h1>
        <form action="UserLogin" method="post">
            <div class="form-group">
                <label for="email1">Email address</label>
                <input type="email" class="form-control" name="email" id="email1" placeholder="Email" autocomplete="off"/>
            </div>
            <div class="form-group">
                <label for="password1">Password</label>
                <input type="password" class="form-control" name="password" id="password1" placeholder="Password" autocomplete="off"/>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Login</button>
        </form>
    </div>
</div>

<!-- Script files -->
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>

</body>
</html>
