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

@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve email and password from the request
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        // Check user credentials
        int userId = LibraryManagementDatabase.getInstance().checkUser(email, password);

        if (userId != -1) {
            HttpSession session = req.getSession();
            session.setAttribute("user", email);
            session.setAttribute("id","1");
            session.setAttribute("userid",userId+"");
            LibraryManagementDatabase.getInstance().setAdminLogin(true);
//             Set cache-control headers to prevent caching the page
            resp.setHeader("Cache-Control", "no-store");  // No caching
//            resp.setHeader("Pragma", "no-cache");         // No caching
//            resp.setDateHeader("Expires", 0);             // Expire immediately
//            HttpSession session = req.getSession();
//            session.setAttribute("userId", userId);
            // Redirect to userpage.html with userId as a query parameter
            resp.sendRedirect("userpage.jsp?userId=" + userId);
        } else {
            // Print error message and redirect back to userlogin.html
//            req.setAttribute("message", "Invalid email or password");
//            req.setAttribute("redirectUrl", "userlogin.jsp");
//
//            // Forward to success.jsp
//            req.getRequestDispatcher("success.html").forward(req, resp);

            String message = "Invalid email or password!";
            String redirectUrl = "userlogin.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
        }
    }
}
