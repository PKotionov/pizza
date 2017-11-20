package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.controller.validator.UserNameValidation;
import ru.mail.pashok.shop.service.UserService;
import ru.mail.pashok.shop.service.UserServiceImpl;
import ru.mail.pashok.shop.service.converter.UserConverter;
import ru.mail.pashok.shop.service.model.RoleDTO;
import ru.mail.pashok.shop.service.model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Kotionov_PV on 03.11.17.
 */
public class LoginServlet extends HttpServlet {
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        if (validate(resp, username, password)) {
            req.getSession().setAttribute("username", username);
            UserDTO userDTO = UserServiceImpl.getInstance().getUser(username);
            req.getSession().setAttribute("user", userDTO);
            RoleDTO role = userDTO.getRoleDTO();
            switch (role) {
                case USER:
                    resp.sendRedirect("/user");
                    break;
                case ADMIN:
                    resp.sendRedirect("/admin");
                    break;
                default:
                    System.out.println("Servlet Login Error: unknown role");
                    break;
            }
        }
    }

    private boolean validate(HttpServletResponse resp, String username, String password) throws IOException {
        List<String> errors = new ArrayList<>();
        UserNameValidation.checkUserName(username, errors);
        if (errors.isEmpty()) {
            if (!userService.isUserValid(username, password)) {
                resp.getWriter().write("User is not valid. Check username or password.");
            } else {
                return true;
            }
        } else {
            resp.getWriter().write("Errors: " + errors);
            System.out.println("Errors: " + errors);
        }
        return false;
    }
}
