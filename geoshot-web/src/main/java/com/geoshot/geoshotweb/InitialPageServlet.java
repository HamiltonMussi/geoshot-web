package com.geoshot.geoshotweb;

import java.io.*;

import com.geoshot.geoshotweb.classes.publicationsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "InitialPageServlet", value = "/initial-page")
public class InitialPageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        publicationsDAO feedGenerator = new publicationsDAO();

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            request.setAttribute("feedlist",feedGenerator.getFeedFromUser(username));
            request.setAttribute("username",username);
            request.getRequestDispatcher("initialPage.jsp").forward(request,response);
        }
    }

    public void destroy() {}

}