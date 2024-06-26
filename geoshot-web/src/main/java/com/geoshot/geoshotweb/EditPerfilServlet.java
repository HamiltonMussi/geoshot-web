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
            usersDAO UserManager = new usersDAO();
            User thisUser = UserManager.getUser(username);
            request.setAttribute("user",thisUser);
            request.getRequestDispatcher("editPerfil.jsp").forward(request,response);
        }

    }

    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        if(username == null) {
            response.sendRedirect("/home");
        } else {
            usersDAO UserManager = new usersDAO();
            User thisUser = UserManager.getUser(username);
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
                request.setAttribute("user",thisUser);
                request.getRequestDispatcher("editPerfil.jsp").forward(request,response);
            } else {

                if(!newPassword.isEmpty()) {
                    // Editar Apenas Senha;
                    UserManager.changePasswordFromUser(username,newPassword);

                }
                if(!encodedPhoto.isEmpty()) {
                    // Editar Apenas photo;
                    UserManager.changePhotoFromUser(username,encodedPhoto);
                }

                response.sendRedirect("/perfil");
            }
        }
    }
}
