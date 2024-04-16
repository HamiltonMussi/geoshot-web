package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.Publication;
import com.geoshot.geoshotweb.classes.attemptsDAO;
import com.geoshot.geoshotweb.classes.publicationsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name="chall",value = "/chall")
public class ChallServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {

            int pubId = Integer.parseInt((String) request.getParameter("pub_id"));

            publicationsDAO PublicationManager = new publicationsDAO();

            Publication thisPublication = PublicationManager.getPublicationById(pubId);

            request.setAttribute("publication",thisPublication);

            request.getRequestDispatcher("PAGINA-DESAFIO-UNICO-JSP").forward(request,response);

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username     = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            int    pubId        = Integer.parseInt((String) request.getParameter("pub-id"));
            int    userAnwser   = Integer.parseInt((String) request.getParameter("user-anwser"));

            publicationsDAO PublicationManager = new publicationsDAO();

            Publication thisPublication = PublicationManager.getPublicationById(pubId);

            int correctValue = thisPublication.getCorrectValue();

            // Calculo da Pontuação;

            double accuracy = 50.0; // Faremos esse calculo quando Hamilton terminar API Google;

            attemptsDAO AttemptManager = new attemptsDAO();

            AttemptManager.insertAttempt(pubId,username,accuracy);

            response.sendRedirect("/my-attempts");
        }


    }

}
