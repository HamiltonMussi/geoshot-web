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



@WebServlet(name="signup", value="/signup")
public class SignupServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username == null) {
            request.getRequestDispatcher("/signup.jsp").forward(request,response);
        } else {
            response.sendRedirect("/initial-page");
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String confirmPassword  = request.getParameter("confirm-password");
        String password         = request.getParameter("password");
        String username         = request.getParameter("username");
        String email            = request.getParameter("email");
        String name             = request.getParameter("name");

        boolean mustRepeat = false;

        if(!confirmPassword.equals(password)) {
            request.setAttribute("non-equals-password","non-equals-password");
            mustRepeat = true;
        }

        usersDAO userManager = new usersDAO();

        if(userManager.check("email",email)) {
            request.setAttribute("already-email","already-email");
            mustRepeat = true;
        }

        if(userManager.check("username", username)) {
            request.setAttribute("already-username","already-username");
            mustRepeat = true;
        }


        if(mustRepeat) {
            request.getRequestDispatcher("/signup.jsp").forward(request,response);
        } else {
            userManager.insertUser(name, username, email, HashGeneretor.getHash("SHA256",password), "default-photo");
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("/initial-page");
        }
    }

    public void destroy() {}
}
