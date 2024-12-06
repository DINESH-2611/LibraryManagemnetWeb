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
<%@ page import="java.util.List" %>
<%@ page import="com.librarymanagement.model.Book" %>

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
    <h2>List of Books</h2>

    <!-- Display the message if no users are found -->
    <p><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Author</th>
            <th>Publisher</th>
            <th>Quantity</th>
            <th>Get Book</th>
        </tr>
        </thead>
        <tbody>
        <%
        // Retrieve the list of users from the request attribute
        List<Book> books = (List<Book>) request.getAttribute("books");
         String userId = request.getParameter("userId");
            if (books != null && !books.isEmpty()) {
            for (Book book : books) {
            %>
            <tr>
                <td><%= book.getId() %></td>
                <td><%= book.getName() %></td>
                <td><%= book.getAuthor() %></td>
                <td><%= book.getPublisher() %></td>
                <td><%= book.getQuantity() %></td>
                 <td>
                                    <!-- Create a "Get Book" button that will redirect to the IssueBook servlet with bookId and userId -->
                                    <form action="IssueBook" method="post">
                                        <input type="hidden" name="bookId" value="<%= book.getId() %>"/>
                                        <input type="hidden" name="userId" value="<%= userId %>"/>
                                        <button type="submit" class="btn btn-primary">Get Book</button>
                                    </form>
                                </td>
            </tr>
            <%
            }
            } else {
            %>
            <tr>
                <td colspan="4">No Books found.</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
