package com.librarymanagement.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/checkSession")
public class SessionCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        // Check if either user or admin is logged in
        if (req.getSession().getAttribute("user") != null || req.getSession().getAttribute("admin") != null) {
            response.getWriter().write("logged_in"); // User is logged in
        } else {
            response.getWriter().write("not_logged_in"); // No active session
        }
    }
}

