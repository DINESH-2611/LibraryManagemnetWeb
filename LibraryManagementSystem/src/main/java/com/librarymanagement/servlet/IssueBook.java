package com.librarymanagement.servlet;

import com.librarymanagement.Database.LibraryManagementDatabase;
import com.librarymanagement.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;

@WebServlet("/IssueBook")
public class IssueBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data from the request
        HttpSession session = req.getSession();
        int bookId = Integer.parseInt(req.getParameter("bookId"));
//        int userId =Integer.parseInt( req.getParameter("userId"));
        int userId=Integer.parseInt((String) session.getAttribute("userid"));
        User user=LibraryManagementDatabase.getInstance().issueBook(userId,bookId);
        System.out.println(user);
        req.setAttribute("message", "Book isuued successfully!");
        req.setAttribute("redirectUrl", "userpage.jsp");

        // Forward to success.jsp
        req.getRequestDispatcher("success.html").forward(req, resp);

        String message = "Invalid email or password!";
        String redirectUrl = "adminlogin.jsp"; // Redirect URL for the admin page

        // Encode parameters to handle special characters
        String encodedMessage = URLEncoder.encode(message, "UTF-8");
        String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
        resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
    }
}
