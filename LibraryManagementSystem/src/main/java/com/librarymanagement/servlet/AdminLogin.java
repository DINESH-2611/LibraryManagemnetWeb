package com.librarymanagement.servlet;

import com.librarymanagement.Database.LibraryManagementDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve email and password from the request
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Check credentials using LibraryManagementDatabase
        if (LibraryManagementDatabase.getInstance().checkAdmin(email, password)) {
            // Store admin info in session to validate protected pages
            HttpSession session = req.getSession();
            session.setAttribute("admin", email);
            session.setAttribute("id","0");
//            LibraryManagementDatabase.getInstance().setAdminLogin(true);
//             Set cache-control headers to prevent caching the page
            resp.setHeader("Cache-Control", "no-store");  // No caching
//            resp.setHeader("Pragma", "no-cache");         // No caching
//            resp.setDateHeader("Expires", 0);             // Expire immediately

            // Redirect to admin page on successful login
            resp.sendRedirect("adminpage.jsp");
        } else {
            // Display error message and redirect back to login page
//            req.setAttribute("message", "Invalid email or password");
//            req.setAttribute("redirectUrl", "adminlogin.jsp");
//
//            // Forward to success.jsp
//            req.getRequestDispatcher("success.html").forward(req, resp);

            String message = "Invalid email or password!";
            String redirectUrl = "adminlogin.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
        }
    }
}
