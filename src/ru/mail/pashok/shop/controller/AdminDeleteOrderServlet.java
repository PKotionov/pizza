package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.service.OrderServiceImpl;
import ru.mail.pashok.shop.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminDeleteOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.valueOf(request.getParameter("id"));
        OrderServiceImpl.getInstance().deleteOrderFromDatabase(id);
        request.getRequestDispatcher("/admin/order/show").forward(request, response);
    }
}
