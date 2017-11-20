package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.repository.model.ConfirmedOrder;
import ru.mail.pashok.shop.service.ItemServiceImpl;
import ru.mail.pashok.shop.service.OrderServiceImpl;
import ru.mail.pashok.shop.service.model.ItemDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowAllOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ConfirmedOrder> confirmedOrders = OrderServiceImpl.getInstance().getAllConfirmedOrders();
        request.setAttribute("orders", confirmedOrders);
        System.out.println(confirmedOrders);
        double sum = 0;
        for (ConfirmedOrder order : confirmedOrders) {
            sum+= order.getSumPrice();
        }
        request.setAttribute("sum", sum);
        request.getRequestDispatcher("/WEB-INF/pages/showAllOrders.jsp").forward(request, response);
    }
}
