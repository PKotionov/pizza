package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.repository.model.Order;
import ru.mail.pashok.shop.repository.model.User;
import ru.mail.pashok.shop.service.ItemServiceImpl;
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

/**
 * Created by Kotionov_PV on 13.11.17.
 */
public class AddOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        long idItem = Long.valueOf(request.getParameter("id"));
        String quantityString = request.getParameter("quantity");
        List<OrderDTO> basket = (List<OrderDTO>) request.getSession().getAttribute("basket");
        if(basket == null) {
            basket = new ArrayList<>();
        }
        try {
            Long quantity = Long.valueOf(quantityString);
            if (quantity > 0) {
                basket.add(new OrderDTO(basket.size()+1, ItemServiceImpl.getInstance().getItem(idItem), quantity, userDTO.getId()));
                request.getSession().setAttribute("basket", basket);
                response.sendRedirect("/user/items");
            } else {
                response.sendRedirect("/user/items");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("/user/items");
            System.out.println(e.getMessage());
        }
    }
}