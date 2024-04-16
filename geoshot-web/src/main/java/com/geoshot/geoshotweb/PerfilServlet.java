package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.geoshot.geoshotweb.classes.usersDAO;
import java.io.IOException;

@WebServlet(name="perfil", value="/perfil")
public class PerfilServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            usersDAO UserManager= new usersDAO();
            User thisUser = UserManager.getUser(username);
            request.setAttribute("user", thisUser);
            request.getRequestDispatcher("perfil.jsp").forward(request,response);
        }
    }

}
