package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.CalculateAccuracy;
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

            int pubId = Integer.parseInt((String) request.getParameter("pub-id"));

            publicationsDAO PublicationManager = new publicationsDAO();

            Publication thisPublication = PublicationManager.getPublicationById(pubId);

            request.setAttribute("publication",thisPublication);

            request.getRequestDispatcher("chall.jsp").forward(request,response);

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username     = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            int pubId = Integer.parseInt((String) request.getParameter("pub-id"));

            String userAnswerString   = ((String) request.getParameter("user-answer"));

            publicationsDAO PublicationManager = new publicationsDAO();

            Publication thisPublication = PublicationManager.getPublicationById(pubId);

            String correctValue = thisPublication.getCorrectValue();

            double accuracy = CalculateAccuracy.getAccuracy(correctValue,userAnswerString); // Faremos esse calculo quando Hamilton terminar API Google;

            attemptsDAO AttemptManager = new attemptsDAO();

            AttemptManager.insertAttempt(pubId,username,accuracy);

            response.sendRedirect("/my-attempts");
        }


    }