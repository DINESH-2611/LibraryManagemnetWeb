<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>e-Library</title>
    <link rel="stylesheet" href="bootstrap.min.css"/>
    <style>
        /* Centering the title at the top */
        h1 {
            text-align: center;
            margin-top: 20px;
        }

        /* Centering the buttons horizontally under the title */
        .center-buttons {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 30px; /* Space between title and buttons */
        }

        .center-buttons form {
            margin: 0 10px; /* Space between forms */
        }

        .center-buttons button {
            width: 150px; /* Button width */
        }
    </style>
</head>
<body>

<%
    // Check if there is an active session with either user or admin logged in
    if (session.getAttribute("user") != null || session.getAttribute("admin") != null) {
        // If the session exists, redirect to the current page where user/admin is logged in
        response.sendRedirect(request.getRequestURI());
        return;
    }
%>

<!-- Welcome message centered at the top -->
<h1>Welcome to Library</h1>

<!-- Centered buttons under the title -->
<div class="container-fluid center-buttons">
    <!-- Admin Button -->
    <form action="adminlogin.jsp" method="get">
        <button type="submit" class="btn btn-primary btn-lg">Admin</button>
    </form>

    <!-- User Button -->
    <form action="userlogin.jsp" method="get">
        <button type="submit" class="btn btn-primary btn-lg">User</button>
    </form>
</div>

<!-- Script files -->
<script>
    // Prevent going back to the previous page after logout
    window.onload = function() {
        if (performance.navigation.type === 2) { // Type 2 indicates back/forward navigation
            // Redirect to ensure session validation
            window.location.href = "index.jsp";
        }
    }
</script>
<script src="jquery.min.js"></script>
<script src="bootstrap.min.js"></script>

</body>
</html>

