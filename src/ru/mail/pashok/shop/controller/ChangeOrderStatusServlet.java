package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.service.OrderServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeOrderStatusServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Long statusID = Long.valueOf(request.getParameter("statusID"));
        long id = Long.valueOf(request.getParameter("id"));
        OrderServiceImpl.getInstance().setStatus(id, statusID);
        request.getRequestDispatcher("/admin/order/show").forward(request, response);
    }
}
