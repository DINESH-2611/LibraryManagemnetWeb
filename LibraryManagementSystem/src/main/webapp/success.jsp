<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Success</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            background-color: #f9f9f9;
            height: 100vh;
        }
        .message-container {
            position: absolute;
            top: 20px; /* Distance from the top */
            left: 50%; /* Center horizontally */
            transform: translateX(-50%); /* Adjust to align perfectly in the center */
            text-align: center;
            background-color: #e8f5e9;
            color: #2e7d32;
            padding: 20px;
            border: 1px solid #c8e6c9;
            border-radius: 5px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: fit-content; /* Auto-adjust width based on content */
        }
        .message-container h1 {
            margin: 0;
            font-size: 1.5rem;
        }
        .message-container p {
            font-size: 1rem;
            margin-top: 10px;
        }
    </style>
    <script>
        // Redirect to the specified URL after a short delay
        setTimeout(function() {
            window.location.href = '<%= request.getAttribute("redirectUrl") %>';
        }, 1000);
    </script>
</head>
<body>
    <div class="message-container">
        <h1><%= request.getAttribute("message") %></h1>
        <p>You will be redirected shortly...</p>
    </div>
</body>
</html>
