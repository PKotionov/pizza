package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.repository.model.Order;
import ru.mail.pashok.shop.service.OrderServiceImpl;
import ru.mail.pashok.shop.service.model.OrderDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ChangeQuantityItemsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.valueOf(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        List<OrderDTO> basket = (List<OrderDTO>) request.getSession().getAttribute("basket");
        if(quantity >= 0){
            basket = OrderServiceImpl.getInstance().setQuantity(id, quantity, basket);
        }
        request.getSession().setAttribute("basket", basket);
        request.getRequestDispatcher("/user/orders").forward(request, response);
    }
}
