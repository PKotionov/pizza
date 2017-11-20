package ru.mail.pashok.shop.controller;

import ru.mail.pashok.shop.service.ItemServiceImpl;
import ru.mail.pashok.shop.service.UserServiceImpl;
import ru.mail.pashok.shop.service.model.ItemDTO;
import ru.mail.pashok.shop.service.model.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShowItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ItemDTO> items = ItemServiceImpl.showItems();
        String username = (String) req.getSession().getAttribute("username");
        req.setAttribute("items", items);
        req.setAttribute("username", username);
        req.getRequestDispatcher("/WEB-INF/pages/showItems.jsp").forward(req, resp);
    }
}
