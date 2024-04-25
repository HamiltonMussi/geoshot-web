package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.Publication;
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

@WebServlet(name="my-challs",value="/my-challs")
public class MyChallsServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String username     = (String) session.getAttribute("username");

        if(username == null) {

            response.sendRedirect("/home");

        } else {

            publicationsDAO PublicationManager = new publicationsDAO();

            List<Publication> publicationlist = PublicationManager.getMyPublications(username);

            usersDAO UserManager = new usersDAO();

            request.setAttribute("user",UserManager.getUser(username));
            request.setAttribute("publicationlist", publicationlist);

            request.getRequestDispatcher("myChallenges.jsp").forward(request,response);

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username     = (String) session.getAttribute("username");

        if(username == null) {

            response.sendRedirect("/home");

        } else {

            int pubId = Integer.parseInt((String) request.getParameter("pub-id"));

            publicationsDAO PublicationManager = new publicationsDAO();

            PublicationManager.removePublicationById(pubId);

            response.sendRedirect("/my-challs");
        }
    }
}
