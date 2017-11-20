package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.repository.model.Order;
import ru.mail.pashok.shop.repository.model.UserOrder;
import ru.mail.pashok.shop.service.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowOrderDetailsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long orderNumber = Long.valueOf(request.getParameter("id"));
        List<Order> orders = OrderServiceImpl.getInstance().getOrderDetails(orderNumber);
        System.out.println("orders details " + orders);
        request.setAttribute("orders", orders);
        double sum = 0;
        for (Order order : orders) {
            sum+= order.getSumPrice();
        }
        request.setAttribute("sum", sum);
        System.out.println("sum" + sum);
        request.getRequestDispatcher("/WEB-INF/pages/showOrderDetails.jsp").forward(request, response);
    }
}
