package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.Publication;
import com.geoshot.geoshotweb.classes.User;
import com.geoshot.geoshotweb.classes.publicationsDAO;
import com.geoshot.geoshotweb.classes.usersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "search", value = "/search")
public class SearchServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String username     = (String) session.getAttribute("username");

        if(username == null) {

            response.sendRedirect("/home");

        } else {

            request.setAttribute("username",username);

            request.getRequestDispatcher("search.jsp").forward(request,response);

        }


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String username     = (String) session.getAttribute("username");

        if(username == null) {

            response.sendRedirect("/home");

        } else {

            String searchedUsername = request.getParameter("searched-username");

            usersDAO UserManager = new usersDAO();

            User searchedUser = UserManager.getUser(searchedUsername);

            request.setAttribute("username", username);

            if(searchedUser == null) {
                // não tem esse usuário!

                request.setAttribute("user-not-found","user-not-found");
                request.getRequestDispatcher("search.jsp").forward(request,response);

            } else {

                boolean userFollows = UserManager.follows(username,searchedUsername);

                request.setAttribute("followship-state",userFollows);
                request.setAttribute("user", searchedUser);

                request.getRequestDispatcher("search.jsp").forward(request,response);


            }

        }

    }

}
