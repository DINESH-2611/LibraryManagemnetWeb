package com.librarymanagement.servlet;

import com.librarymanagement.Database.LibraryManagementDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set headers to prevent caching
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", -1);

        // Retrieve form data from the request
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String mobile = req.getParameter("mobile");

        // Validate the input (optional but recommended)
//        if (name == null || email == null || password == null || mobile == null ||
//                name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty() || mobile.trim().isEmpty()) {
//            req.setAttribute("message", "All fields are required!");
//            req.setAttribute("redirectUrl", "adduser.jsp");
//            req.getRequestDispatcher("error.jsp").forward(req, resp);
//            return;
//        }

        // Add the user to the database
       if( LibraryManagementDatabase.getInstance().adduser(name, email, password, mobile)){
//           req.setAttribute("message", "User added successfully!");
//           req.setAttribute("redirectUrl", "adminpage.jsp");
//           // Forward the request to success.jsp
//           req.getRequestDispatcher("success.html").forward(req, resp);

           String message = "User added successfully!";
           String redirectUrl = "adminpage.jsp"; // Redirect URL for the admin page

           // Encode parameters to handle special characters
           String encodedMessage = URLEncoder.encode(message, "UTF-8");
           String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
           resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
       }else {
//           req.setAttribute("message", "Email or Phone No already Exist!");
//           req.setAttribute("redirectUrl", "adduser.jsp");
//           // Forward the request to success.html
//           req.getRequestDispatcher("success.html").forward(req, resp);

           String message = "Email or Phone No already Exist!";
           String redirectUrl = "adduser.jsp"; // Redirect URL for the admin page

           // Encode parameters to handle special characters
           String encodedMessage = URLEncoder.encode(message, "UTF-8");
           String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
           resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
       }

//        if (isAdded) {
//            // Set success message and redirect to admin page
//            req.setAttribute("message", "User added successfully!");
//            req.setAttribute("redirectUrl", "adminpage.jsp");
//        } else {
//            // Handle potential issues like duplicate email
//            req.setAttribute("message", "Failed to add user. Email might already exist.");
//            req.setAttribute("redirectUrl", "adduser.jsp");
//        }
//        req.setAttribute("message", "User added successfully!");
//        req.setAttribute("redirectUrl", "adminpage.jsp");
//        // Forward the request to success.jsp
//        req.getRequestDispatcher("success.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Set headers to prevent caching for GET requests (like going back to adduser.jsp)
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", -1);

        // Redirect back to the add user form
        req.getRequestDispatcher("adduser.jsp").forward(req, resp);
    }
}
