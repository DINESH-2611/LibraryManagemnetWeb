package com.librarymanagement.servlet;

import com.librarymanagement.Database.LibraryManagementDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String publisher = req.getParameter("publisher");
        String quantity = req.getParameter("quantity");

        // Add book to the database
        if(LibraryManagementDatabase.getInstance().addBook(name, author, publisher, quantity)) {

            // Set attributes for success.jsp
//            req.setAttribute("message", "Book added successfully!");
//            req.setAttribute("redirectUrl", "adminpage.jsp");
//
//            // Forward to success.jsp
//            req.getRequestDispatcher("success.jsp").forward(req, resp);

            String message = "Book added successfully!";
            String redirectUrl = "adminpage.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
        }else {
//            req.setAttribute("message", "Book already Exist!");
//            req.setAttribute("redirectUrl", "addbook.jsp");
//
//            // Forward to success.jsp
//            req.getRequestDispatcher("success.html").forward(req, resp);

            String message = "Book already Exist!";
            String redirectUrl = "addbook.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
        }
    }
}
