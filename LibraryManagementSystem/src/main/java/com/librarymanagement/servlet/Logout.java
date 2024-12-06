package com.librarymanagement.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Get the current session
        HttpSession session = req.getSession();

        // Get the referer header to check where the request came from
        String referer = req.getHeader("Referer");

        // If the request is coming from userpage, remove the 'user' session attribute
        if (referer != null && referer.contains("userpage.jsp")) {
            if(session.getAttribute("admin")==null)
                session.invalidate();
            else
            session.removeAttribute("user");
        }
        // If the request is coming from adminpage, remove the 'admin' session attribute
        else if (referer != null && referer.contains("adminpage.jsp")) {
            if(session.getAttribute("user")==null)
                session.invalidate();
            else
                session.removeAttribute("admin");
        }

        // Set cache-control headers to prevent caching the pages
        resp.setHeader("Cache-Control", "no-store");  // HTTP 1.1
        resp.setHeader("Pragma", "no-cache");         // HTTP 1.0
        resp.setDateHeader("Expires", 0);              // Proxies

        // Redirect to the home page after logging out
        resp.sendRedirect("index.html");
    }
}
