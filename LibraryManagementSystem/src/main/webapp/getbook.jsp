<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%
    // Prevent caching to ensure a fresh page is served
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    String email2 = (String) session.getAttribute("user");
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
    <title>Get Book</title>
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
    <form id="bookForm" action="GetBook" method="post">
        <div class="form-group">
            <label for="name1">Book Name</label>
            <input type="text" class="form-control" name="name" id="name1" placeholder="Enter book name" required/>
        </div>

        <!-- Hidden field for action (GetBook or ReturnBook) -->
        <input type="hidden" name="action" id="action"/>

        <!-- Hidden field for userId -->
        <input type="hidden" name="userId" id="userId"/>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>

<script>
    // Get 'action' and 'userId' from URL query parameters
    function getQueryParameter(name) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(name);
    }

    // Retrieve the action (GetBook or ReturnBook) and userId from the URL
    const action = getQueryParameter('action');
    const userId = getQueryParameter('userId');

    // Set the hidden fields based on the URL parameters
    if (action) {
        document.getElementById('action').value = action; // Set the action field value
    }

    if (userId) {
        document.getElementById('userId').value = userId; // Set the userId field value
    }

    // Dynamically change the form's action based on the action URL parameter
    const form = document.getElementById('bookForm');
    if (action === 'ReturnBook') {
        form.action = "ReturnBook"; // Change form action to ReturnBook
    } else if (action === 'GetBook') {
        form.action = "GetBook"; // Default is GetBook
    } else {
        form.action = "GetBook"; // Fallback to GetBook if no action is specified
    }

</script>

</body>
</html>
