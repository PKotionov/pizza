package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.repository.model.Item;
import ru.mail.pashok.shop.repository.model.Order;
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

public class BasketServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("Basket started");
//        String username = (String) request.getSession().getAttribute("username");
//        List<Order> orders = OrderServiceImpl.getInstance().getOrder();
////        List<OrderDTO> ordersUser = new ArrayList<>();
////        long i = 1;
////        for (OrderDTO order : orders) {
////            if(order.getUsername().equals(username)){
////                order.setId(i);
////                ordersUser.add(order);
////                i++;
////            }
////        }
////        request.setAttribute("orders", ordersUser);
//        request.setAttribute("orders", orders);
//        request.getRequestDispatcher("/WEB-INF/pages/showClientOrders.jsp").forward(request, response);
    }
}
