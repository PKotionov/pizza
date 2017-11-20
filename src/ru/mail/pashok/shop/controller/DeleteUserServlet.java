package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        if(UserServiceImpl.getInstance().deleteUser(id)){
            req.getRequestDispatcher("/admin/clients").forward(req, resp);
        }else {
            resp.getWriter().write("You can not delete this user.");
        }
    }
}
