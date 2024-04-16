package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.MyAttempt;
import com.geoshot.geoshotweb.classes.publicationsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name="my-attempts",value="/my-attempts")
public class MyAttemptsServlet  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {

            publicationsDAO PublicationsManager = new publicationsDAO();

            List<MyAttempt> attemptslist = PublicationsManager.getMyAttempts(username);

            request.setAttribute("username",username);
            request.setAttribute("attemptslist", attemptslist);
            request.getRequestDispatcher("myAttempts.jsp").forward(request,response);
        }

    }
}
