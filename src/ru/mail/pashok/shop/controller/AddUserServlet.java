package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.controller.validator.UserNameValidation;
import ru.mail.pashok.shop.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        if(password.equals(request.getParameter("confirmPassword").trim())){
            validate(response, username, password);
        }else {
            response.getWriter().write("Check confirm password and try again.");
        }

    }
    private void validate(HttpServletResponse resp, String username, String password) throws IOException {
        List<String> errors = new ArrayList<>();
        UserNameValidation.checkUserName(username, errors);
        if (errors.isEmpty()) {
           if(UserServiceImpl.getInstance().addUser(username, password)){
               resp.sendRedirect("/admin/clients");
           }else {
               resp.getWriter().write("User with this name already exist. Try again.");
           }
        } else {
            System.out.println(errors + ". User not added.");
        }
    }
}
