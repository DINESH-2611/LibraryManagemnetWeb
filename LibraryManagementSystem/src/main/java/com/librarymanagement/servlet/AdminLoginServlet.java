package com.librarymanagement.servlet;

import com.librarymanagement.Database.LibraryManagementDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
@WebServlet("/adminlogin")
public class AdminLoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Check if the session has the email attribute
        HttpSession session = request.getSession();
//        if (session != null && session.getAttribute("email") != null) {
//            // If the email attribute is found, redirect to the admin page
//            response.sendRedirect("adminpage.jsp");
//        } else {
//            // If not logged in, forward to the admin login page
//            request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
//        }
        if(LibraryManagementDatabase.getInstance().isAdminLogin()){
            session.setAttribute("email", "admin@gmail.com");
            request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
        }else{
            response.sendRedirect("index.html");
        }
    }
}

