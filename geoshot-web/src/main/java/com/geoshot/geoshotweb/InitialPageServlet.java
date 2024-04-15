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


        if(username == null) {
            response.sendRedirect("/home");
        } else {
            publicationsDAO feedGenerator = new publicationsDAO();
            request.setAttribute("feedlist",feedGenerator.getFeedFromUser(username));
            request.setAttribute("username",username);
            request.getRequestDispatcher("initialPage.jsp").forward(request,response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username     = (String) session.getAttribute("username");
        int    pubId        = Integer.parseInt((String) session.getAttribute("pub_id"));
        int    userAnwser   = Integer.parseInt((String) session.getAttribute("user-anwser"));

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            //continua no desafio.
        }


    }

    public void destroy() {}

}