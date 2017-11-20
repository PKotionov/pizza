package ru.mail.pashok.shop.repository;

import ru.mail.pashok.shop.repository.model.ConfirmedOrder;
import ru.mail.pashok.shop.repository.model.Order;
import ru.mail.pashok.shop.repository.model.User;
import ru.mail.pashok.shop.repository.model.UserOrder;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;

/**
 * Created by Kotionov_PV on 03.11.17.
 */
public interface OrderRepository {

    void addNewOrder(Long userID, List<Order> basket);

    void addOrderItems (Long userID, Connection connection, List<Order> basket);

    void addTotalOrder(Connection connection, List<Order> basket);

    List<ConfirmedOrder> getClientOrders(Long userID);

    List<ConfirmedOrder> getAllOrders();

    List<UserOrder> getUserOrders();

    List<Order> getOrdersByOrderNumber(Long orderNumber);

    Order getOrderByNumber(int idItem, int quantity);

    long getLastOrderNumber();

    void deleteOrderAdmin(long id);

    void changeOrderStatus(long id, long statusID);
}
