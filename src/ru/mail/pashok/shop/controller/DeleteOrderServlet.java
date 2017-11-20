package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.service.OrderServiceImpl;
import ru.mail.pashok.shop.service.model.OrderDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        List<OrderDTO> basket = (List<OrderDTO>) request.getSession().getAttribute("basket");
        System.out.println("delete: " + basket);
        basket = OrderServiceImpl.getInstance().deleteOrder(id, basket);
        request.getSession().setAttribute("basket", basket);
        request.getRequestDispatcher("/user/orders").forward(request, response);
    }
}
