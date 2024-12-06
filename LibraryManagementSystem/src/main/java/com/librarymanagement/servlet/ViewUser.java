package com.librarymanagement.servlet;

import com.librarymanagement.Database.LibraryManagementDatabase;
import com.librarymanagement.model.User; // Assuming User class exists

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;

@WebServlet("/ViewUser")
public class ViewUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get all users from the database
        List<User> users = LibraryManagementDatabase.getInstance().getAllUsers();
        System.out.println(users.size());
        for (User user:users)
            System.out.println(user);

        // Set the users list as an attribute to be used in the JSP page
        req.setAttribute("users", users);

        // Forward the request to viewuser.jsp
        RequestDispatcher dispatcher = req.getRequestDispatcher("/viewuser.jsp");
        dispatcher.forward(req, resp);
    }
}

