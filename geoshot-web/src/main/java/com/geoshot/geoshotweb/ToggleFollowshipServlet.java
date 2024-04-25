package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.User;
import com.geoshot.geoshotweb.classes.usersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="toggle-followship",value="/toggle-followship")
public class ToggleFollowshipServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username     = (String) session.getAttribute("username");

        if(username == null) {

            response.sendRedirect("/home");

        } else {

            String searchedUsername = request.getParameter("searched-username");

            usersDAO UserManager = new usersDAO();

            User searchedUser = UserManager.getUser(searchedUsername);

            if(searchedUser == null) {
                response.sendRedirect("/search");
            } else {

                if(UserManager.follows(username,searchedUsername)) {
                    // Usuário segue o pesquisado;
                    UserManager.removeFollow(username,searchedUsername);
                } else {
                    // Usuário não segue o pesquisado;
                    UserManager.insertFollow(username,searchedUsername);
                }
                response.sendRedirect("/search");
            }
        }
    }
}
