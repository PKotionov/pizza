package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.repository.model.ConfirmedOrder;
import ru.mail.pashok.shop.repository.model.UserOrder;
import ru.mail.pashok.shop.service.OrderServiceImpl;
import ru.mail.pashok.shop.service.model.UserOrderDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowAdminOrdersServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<UserOrderDTO> orders = OrderServiceImpl.getInstance().getUserOrders();
        System.out.println(orders);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/pages/showAdminOrders.jsp").forward(request, response);
    }
}
