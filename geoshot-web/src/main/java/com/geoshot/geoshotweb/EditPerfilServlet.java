package com.geoshot.geoshotweb;

import com.geoshot.geoshotweb.classes.PhotoConverter;
import com.geoshot.geoshotweb.classes.User;
import com.geoshot.geoshotweb.classes.usersDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;


@WebServlet(name="edit-perfil", value="/edit-perfil")
@MultipartConfig
public class EditPerfilServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            usersDAO verifyUser = new usersDAO();
            User thisUser = verifyUser.getUser(username);
            request.setAttribute("username", thisUser.getUsername());
            request.setAttribute("name", thisUser.getName());
            request.setAttribute("email", thisUser.getEmail());
            request.setAttribute("photo", thisUser.getPhoto());
            request.setAttribute("attempts",thisUser.getAttempts());
            request.setAttribute("accuracy",thisUser.getAccuracy());
            request.getRequestDispatcher("PAGINA-JSP").forward(request,response);
        }

    }

    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            usersDAO verifyUser = new usersDAO();
            User thisUser = verifyUser.getUser(username);
            String oldPassword = request.getParameter("old-password");
            String newPassword = request.getParameter("password");
            String newConfirmPassword = request.getParameter("confirm-password");

            Part filepart = request.getPart("photo");
            InputStream inputStream = filepart.getInputStream();
            String encodedPhoto = PhotoConverter.encoder(inputStream.readAllBytes());

            boolean noMatchNewPassword    = false;
            boolean noPhotoOrPassword     = false;
            boolean noOldPasswordOnUpdate = false;

            if(oldPassword.isEmpty() && !newPassword.isEmpty()) {
                noOldPasswordOnUpdate = true;
            }

            if(!newPassword.equals(newConfirmPassword)) {
                noMatchNewPassword = true;
            }

            if(newPassword.isEmpty() && encodedPhoto.isEmpty()) {
                noPhotoOrPassword = true;
            }

            if(noPhotoOrPassword || noMatchNewPassword || noOldPasswordOnUpdate) {
                if(noPhotoOrPassword)     request.setAttribute("no-photo","no-photo");
                if(noMatchNewPassword)    request.setAttribute("non-equal-password","non-equal-password");
                if(noOldPasswordOnUpdate) request.setAttribute("no-old-password-on-update","no-old-password-on-update");
                request.setAttribute("username", thisUser.getUsername());
                request.setAttribute("name", thisUser.getName());
                request.setAttribute("email", thisUser.getEmail());
                request.setAttribute("photo", thisUser.getPhoto());
                request.setAttribute("attempts",thisUser.getAttempts());
                request.setAttribute("accuracy",thisUser.getAccuracy());
                request.getRequestDispatcher("PAGINA-JSP").forward(request,response);
            } else {

                if(!encodedPhoto.isEmpty()) {
                    // Muda a foto;
                } else {
                    // Muda só a senha;
                }

                response.sendRedirect("/perfil");

            }


        }
    }

}
