package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.PhotoConverter;
import com.geoshot.geoshotweb.classes.publicationsDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(name="create-chall", value = "/create-chall")
@MultipartConfig
public class CreateChallServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            request.getRequestDispatcher("createchall.jsp").forward(request,response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {

            Part filepart = request.getPart("photo");
            InputStream inputStream = filepart.getInputStream();
            String encodedPhoto = PhotoConverter.encoder(inputStream.readAllBytes());

            String scorrectValue = request.getParameter("anwser");

            if(encodedPhoto.isEmpty() || scorrectValue.isEmpty()) {
                request.setAttribute("missing","missing");
                request.getRequestDispatcher("createchall.jsp").forward(request,response);
            } else {
                int correctValue = Integer.parseInt(scorrectValue);

                publicationsDAO PublicationManager = new publicationsDAO();

                PublicationManager.insertPublication(encodedPhoto,username,correctValue);

                response.sendRedirect("/initial-page");

            }
        }
    }
}
