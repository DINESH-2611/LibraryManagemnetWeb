package com.librarymanagement.servlet;

import com.librarymanagement.Database.LibraryManagementDatabase;
import com.librarymanagement.model.Book;
import com.librarymanagement.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

// Map the servlet to "/AddUser"
@WebServlet("/ReturnBook")
public class ReturnBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data from the request
        HttpSession session=req.getSession();
        int userId=Integer.parseInt((String) (session.getAttribute("userid")));
        String bookName = req.getParameter("name");
        bookName = bookName.toUpperCase();
//        String userId = req.getParameter("userId");
        System.out.println(bookName+"name");
        System.out.println(userId+"user");
        if(LibraryManagementDatabase.getInstance().userBookCount(userId)){
            String message = "You have no book to return!";
            String redirectUrl = "userpage.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
            return;
        }
        if(LibraryManagementDatabase.getInstance().validUserBook(userId,bookName)){
//            req.setAttribute("message", "Book returned successfully!");
//            req.setAttribute("redirectUrl", "userpage.jsp");
//
//            // Forward to success.jsp
//            req.getRequestDispatcher("success.html").forward(req, resp);

            String message = "Book returned successfully!";
            String redirectUrl = "userpage.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
        }else{
//            req.setAttribute("message", "Book name doesn't match!");
//            req.setAttribute("redirectUrl", "userpage.jsp");
//
//            // Forward to success.jsp
//            req.getRequestDispatcher("success.html").forward(req, resp);

            String message = "Book name doesn't match!";
            String redirectUrl = "userpage.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
        }

//            System.out.println(book);
    }
}


