package ru.mail.pashok.shop.service;

import ru.mail.pashok.shop.repository.model.Item;
import ru.mail.pashok.shop.repository.model.Order;
import ru.mail.pashok.shop.service.model.ItemDTO;
import ru.mail.pashok.shop.service.model.OrderDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Kotionov_PV on 13.11.17.
 */
public interface OrderService {

    boolean addOrderToDatabase(Long userID, List<OrderDTO> basket);
}
