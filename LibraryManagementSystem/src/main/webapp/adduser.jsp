<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    // Prevent caching to ensure a fresh page is served
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    String email2 = (String) session.getAttribute("admin");
            // If email is null (session is invalid), redirect to the homepage
            if (email2 == null) {
                response.sendRedirect("index.html");
                return;
            }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
    <link rel="stylesheet" href="bootstrap.min.css"/>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start; /* Align form to the top */
            height: 100vh; /* Full viewport height */
            background-color: #f8f9fa; /* Light background for contrast */
        }
        .form-container {
            margin-top: 20px; /* Space from the top */
            width: 300px; /* Set a consistent width for the form */
        }
        .form-container .form-group {
            margin-bottom: 15px;
        }
        .form-container .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-container .form-group input {
            width: 100%;
            padding: 8px;
            font-size: 14px;
        }
        .form-container button {
            display: block;
            margin: 20px auto 0; /* Center the button horizontally */
        }
    </style>
</head>
<body>
<div class="form-container">
    <h3 class="text-center">Add User</h3>
    <form id="addUserForm" action="AddUser" method="post">
        <div class="form-group">
            <label for="name1">Name</label>
            <input type="text" class="form-control" name="name" id="name1" placeholder="Name" autocomplete="off" required/>
        </div>
        <div class="form-group">
            <label for="email1">Email</label>
            <input type="email" class="form-control" name="email" id="email1" placeholder="Email"  autocomplete="off" required/>
        </div>
        <div class="form-group">
            <label for="password1">Password</label>
            <input type="password" class="form-control" name="password" id="password1" placeholder="Password" autocomplete="off"  required/>
        </div>
        <div class="form-group">
            <label for="mobile1">Phone No</label>
            <input type="tel" class="form-control" name="mobile" id="mobile1" placeholder="Mobile" pattern="[0-9]{10}" autocomplete="off" required/>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>
</body>
</html>
