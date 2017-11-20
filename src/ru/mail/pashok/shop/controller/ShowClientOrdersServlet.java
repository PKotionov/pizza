package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.repository.model.Order;
import ru.mail.pashok.shop.service.ItemServiceImpl;
import ru.mail.pashok.shop.service.OrderServiceImpl;
import ru.mail.pashok.shop.service.model.ItemDTO;
import ru.mail.pashok.shop.service.model.OrderDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowClientOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<OrderDTO> orders = (List<OrderDTO>) request.getSession().getAttribute("basket");
        request.setAttribute("orders", orders);
        double sum = 0;
        for (OrderDTO order : orders) {
            sum += order.getSumPrice();
        }
        request.setAttribute("sum", sum);
        request.getRequestDispatcher("/WEB-INF/pages/showClientOrders.jsp").forward(request, response);
    }

}
