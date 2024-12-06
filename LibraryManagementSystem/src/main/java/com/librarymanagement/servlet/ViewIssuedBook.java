package com.librarymanagement.servlet;

import com.librarymanagement.Database.LibraryManagementDatabase;
import com.librarymanagement.model.Book;
import com.librarymanagement.model.IssueBook;
import com.librarymanagement.model.User; // Assuming User class exists

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet("/ViewIssuedBook")
public class ViewIssuedBook extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get all users from the database
        List<IssueBook> books = LibraryManagementDatabase.getInstance().getAllIssuedBooks();
//        System.out.println(users.size());
//        for (User user:users)
//            System.out.println(user);
        if(books.size()==0){
//            req.setAttribute("message", "No Book is issued yet!");
//            req.setAttribute("redirectUrl", "adminpage.jsp");
//
//            // Forward to success.jsp
//            req.getRequestDispatcher("success.html").forward(req, resp);
            String message = "No Book is issued yet!";
            String redirectUrl = "adminpage.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);

        }        // Set the users list as an attribute to be used in the JSP page
        else {
            req.setAttribute("books", books);

            // Forward the request to viewuser.jsp
            RequestDispatcher dispatcher = req.getRequestDispatcher("/viewissuebook.jsp");
            dispatcher.forward(req, resp);
        }
    }
}



