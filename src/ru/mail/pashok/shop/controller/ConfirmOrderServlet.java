package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.repository.model.ConfirmedOrder;
import ru.mail.pashok.shop.repository.model.Order;
import ru.mail.pashok.shop.service.OrderServiceImpl;
import ru.mail.pashok.shop.service.model.OrderDTO;
import ru.mail.pashok.shop.service.model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfirmOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        System.out.println(userDTO);
        List<OrderDTO> basket = (List<OrderDTO>) request.getSession().getAttribute("basket");
        System.out.println(basket);
        if(basket != null){
            OrderServiceImpl.getInstance().addOrderToDatabase(userDTO.getId(), basket);
        }
        request.getSession().setAttribute("basket", new ArrayList<OrderDTO>());
        List<ConfirmedOrder> confirmedOrders = OrderServiceImpl.getInstance().getConfirmedOrders(userDTO.getId());
        request.setAttribute("orders", confirmedOrders);
        System.out.println("confirmedOrders:" + confirmedOrders);
        double sum = 0;
        for (ConfirmedOrder order : confirmedOrders) {
            sum += order.getSumPrice();
        }
        request.setAttribute("sum", sum);
        request.getRequestDispatcher("/WEB-INF/pages/showConfirmedOrders.jsp").forward(request, response);
    }
}
