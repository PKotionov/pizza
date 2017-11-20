package ru.mail.pashok.shop.service;

import ru.mail.pashok.shop.repository.ConnectionService;
import ru.mail.pashok.shop.repository.ConnectionServiceImpl;
import ru.mail.pashok.shop.repository.OrderRepository;
import ru.mail.pashok.shop.repository.OrderRepositoryImpl;
import ru.mail.pashok.shop.repository.model.*;
import ru.mail.pashok.shop.service.converter.OrderConverter;
import ru.mail.pashok.shop.service.converter.UserOrderConverter;
import ru.mail.pashok.shop.service.model.ItemDTO;
import ru.mail.pashok.shop.service.model.OrderDTO;
import ru.mail.pashok.shop.service.model.UserOrderDTO;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotionov_PV on 13.11.17.
 */
public class OrderServiceImpl implements OrderService {

    private Long lastOrderNumber;

    private static OrderServiceImpl instance;

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    public Long getLastOrderNumber() {
        if(lastOrderNumber == 0){
            lastOrderNumber = OrderRepositoryImpl.getInstance().getLastOrderNumber();
        }
        return lastOrderNumber;
    }

    public void setLastOrderNumber(Long lastOrderNumber) {
        this.lastOrderNumber = lastOrderNumber;
    }


//    public List<Order> addOrder(String username, Long idItem, Long quantity, List<Order> basket) {
//        Order order = new Order(basket.size(), idItem, quantity, username);
//        basket.add(order);
//        return basket;
//    }

    @Override
    public boolean addOrderToDatabase(Long userID, List<OrderDTO> basketDTO) {
        System.out.println("addOrderToDatabase: " + basketDTO);
        List<Order> basket = OrderConverter.orderListDTOToOrderList(basketDTO);
        if (basket != null) {
            OrderRepositoryImpl.getInstance().addNewOrder(userID, basket);
            return true;
        }
        return false;
    }

    public List<OrderDTO> deleteOrder(Integer id, List<OrderDTO> basket) {
        System.out.println("in service: " + basket);
        System.out.println("delete id: " + id);
        for (int i = 0; i < basket.size(); i++) {
            if (basket.get(i).getId() == id) {
                basket.remove(i);
            }
        }
        return basket;
    }

    public List<UserOrderDTO> getUserOrders(){
        List<UserOrder> userOrders = OrderRepositoryImpl.getInstance().getUserOrders();
        List<UserOrderDTO> userOrderDTOS = UserOrderConverter.userOrderListToUserOrderDTOList(userOrders);
        return userOrderDTOS;
    }

    public List<ConfirmedOrder> getConfirmedOrders(Long userID) {

        return OrderRepositoryImpl.getInstance().getClientOrders(userID);
    }

    public List<ConfirmedOrder> getAllConfirmedOrders() {
        return OrderRepositoryImpl.getInstance().getAllOrders();
    }

    public void deleteOrderFromDatabase(long id) {
        OrderRepositoryImpl.getInstance().deleteOrderAdmin(id);
    }

    public void setStatus(long id, Long statusID) {
        OrderRepositoryImpl.getInstance().changeOrderStatus(id, statusID);
    }


    public List<OrderDTO> setQuantity(long id, int quantity, List<OrderDTO> basket) {
        for (OrderDTO order : basket) {
            if (order.getId() == id) {
                order.setQuantity(quantity);
            }
        }
        return basket;
    }

    public List<Order> getOrderDetails(Long orderNumber) {
        List<Order> orders = OrderRepositoryImpl.getInstance().getOrdersByOrderNumber(orderNumber);
        return orders;
    }

}
