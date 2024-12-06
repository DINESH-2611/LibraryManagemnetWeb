<%@ page import="java.util.List" %>
<%@ page import="com.librarymanagement.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Users</title>
    <link rel="stylesheet" href="bootstrap.min.css"/>
     <style>
            /* Style for table with borders between rows and columns */
            table {
                width: 100%;
                border-collapse: collapse; /* Ensures borders between cells are merged */
            }

            th, td {
                border: 1px solid #ddd; /* Light gray border for cells */
                padding: 8px; /* Add padding for better readability */
                text-align: left; /* Align text to the left */
            }

            th {
                background-color: #f2f2f2; /* Light gray background for headers */
            }

            tr:nth-child(1) {
                background-color: #f9f9f9; /* Alternate row color */
            }

            tr:hover {
                background-color: #f1f1f1; /* Row hover effect */
            }
        </style>
</head>
<body>
    <div class="container">
        <h2>List of Users</h2>

        <!-- Display the message if no users are found -->
        <p><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Book Count</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Retrieve the list of users from the request attribute
                    List<User> users = (List<User>) request.getAttribute("users");
                    if (users != null && !users.isEmpty()) {
                        for (User user : users) {
                %>
                <tr>
                    <td><%= user.getId() %></td>
                    <td><%= user.getName() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getMobile() %></td>
                    <td><%= user.getBookCount() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4">No users found.</td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
