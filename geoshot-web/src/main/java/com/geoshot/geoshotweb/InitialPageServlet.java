package com.geoshot.geoshotweb;

import java.io.*;

import com.geoshot.geoshotweb.classes.Publication;
import com.geoshot.geoshotweb.classes.attemptsDAO;
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

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            int    pubId        = Integer.parseInt((String) session.getAttribute("pub_id"));
            int    userAnwser   = Integer.parseInt((String) session.getAttribute("user-anwser"));

            publicationsDAO PublicationManager = new publicationsDAO();

            Publication thisPublication = PublicationManager.getPublicationById(pubId);

            int correctValue = thisPublication.getCorrectValue();

            // Calculo da Pontuação;

            double accuracy = 50.0; // Faremos esse calculo quando Hamilton terminar API Google;

            attemptsDAO AttemptManager = new attemptsDAO();

            AttemptManager.insertAttempt(pubId,username,accuracy);

            // E depois, mando pra onde?

        }


    }

    public void destroy() {}

}