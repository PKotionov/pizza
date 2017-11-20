package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.service.UserServiceImpl;
import ru.mail.pashok.shop.service.model.UserDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserDTO> users = UserServiceImpl.showUserList();
        req.setAttribute("users", users);
        System.out.println(req.getParameter("username"));
        req.getRequestDispatcher("/WEB-INF/pages/showUser.jsp").forward(req, resp);
    }
}
