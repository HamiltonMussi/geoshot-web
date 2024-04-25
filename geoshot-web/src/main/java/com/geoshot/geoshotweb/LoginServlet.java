package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.HashGeneretor;
import com.geoshot.geoshotweb.classes.usersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="login", value="/login")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username==null) {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        } else {
            response.sendRedirect("/initial-page");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String hashPassword = HashGeneretor.getHash("SHA256",password);

        usersDAO verifyUserDAO = new usersDAO();
        String realUsername = verifyUserDAO.checkUser(username,hashPassword);

        if(realUsername!=null) {

            HttpSession session = request.getSession();
            session.setAttribute("username", realUsername);
            response.sendRedirect("/initial-page");

        } else {

            request.setAttribute("nologin","nologin");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    public void destroy() {}
}
