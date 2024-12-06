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
@WebServlet("/GetBook")
public class GetBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve form data from the request
        String name = req.getParameter("name");
        name = name.toUpperCase();
        HttpSession session=req.getSession();
        int userId=Integer.parseInt((String) (session.getAttribute("userid")));
        if(LibraryManagementDatabase.getInstance().reachLimit(userId)){
            req.setAttribute("message", "You have reached the limit,You have to return the book then only you can get the book!");
            req.setAttribute("redirectUrl", "userpage.jsp");

            // Forward to success.jsp
            req.getRequestDispatcher("success.html").forward(req, resp);

            String message = "Invalid email or password!";
            String redirectUrl = "adminlogin.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
            return;
        }
        // Add the user to the database
        List<Book> books = LibraryManagementDatabase.getInstance().getBooksByName(name);
        if (books.size() == 0) {
//            req.setAttribute("message", "Book doesn't exist!");
//            req.setAttribute("redirectUrl", "userpage.jsp");
//
//            // Forward to success.jsp
//            req.getRequestDispatcher("success.html").forward(req, resp);

            String message = "Book doesn't exist!";
            String redirectUrl = "userpage.jsp"; // Redirect URL for the admin page

            // Encode parameters to handle special characters
            String encodedMessage = URLEncoder.encode(message, "UTF-8");
            String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
            resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
        } else {
            System.out.println(name);
            System.out.println(userId + "userid");
            List<Book> availableBooks = new ArrayList<>();
            for (Book book : books) {
                if (book.getQuantity() > 0)
                    availableBooks.add(book);
            }
            if (availableBooks.size() == 0) {
//                req.setAttribute("message", "Book not available!");
//                req.setAttribute("redirectUrl", "userpage.jsp");
//
//                // Forward to success.jsp
//                req.getRequestDispatcher("success.html").forward(req, resp);

                String message = "Book not available!";
                String redirectUrl = "userpage.jsp"; // Redirect URL for the admin page

                // Encode parameters to handle special characters
                String encodedMessage = URLEncoder.encode(message, "UTF-8");
                String encodedRedirectUrl = URLEncoder.encode(redirectUrl, "UTF-8");
                resp.sendRedirect("success.html?message=" + encodedMessage + "&redirectUrl=" + encodedRedirectUrl);
            } else {
                req.setAttribute("books", availableBooks);
                req.setAttribute("userId", userId);
                // Forward the request to viewuser.jsp
                RequestDispatcher dispatcher = req.getRequestDispatcher("/viewavailablebook.jsp");
                dispatcher.forward(req, resp);
            }
        }
        System.out.println(books.size());
        for (Book book : books)
            System.out.println(book);
    }
}


