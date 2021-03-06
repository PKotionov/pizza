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
import java.util.Objects;

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

    private OrderRepository orderRepository = OrderRepositoryImpl.getInstance();

    public Long getLastOrderNumber() {
        if(lastOrderNumber == 0){
            lastOrderNumber = orderRepository.getLastOrderNumber();
        }
        return lastOrderNumber;
    }

    public void setLastOrderNumber(Long lastOrderNumber) {
        this.lastOrderNumber = lastOrderNumber;
    }

    @Override
    public boolean addOrderToDatabase(Long userID, List<OrderDTO> basketDTO) {
        System.out.println("addOrderToDatabase: " + basketDTO);
        List<Order> basket = OrderConverter.orderListDTOToOrderList(basketDTO);
        if (basket != null) {
            orderRepository.addNewOrder(userID, basket);
            return true;
        }
        return false;
    }

    public List<OrderDTO> deleteOrder(Integer id, List<OrderDTO> basket) {
        for (int i = 0; i < basket.size(); i++) {
            if (Objects.equals(basket.get(i).getId(), id)) {
                basket.remove(i);
            }
        }
        return basket;
    }

    public List<UserOrderDTO> getUserOrders(){
        List<UserOrder> userOrders = orderRepository.getUserOrders();
        List<UserOrderDTO> userOrderDTOS = UserOrderConverter.userOrderListToUserOrderDTOList(userOrders);
        return userOrderDTOS;
    }

    public List<ConfirmedOrder> getConfirmedOrders(Long userID) {

        return orderRepository.getClientOrders(userID);
    }

    public void deleteOrderFromDatabase(long id) {
        orderRepository.deleteOrderAdmin(id);
    }

    public void setStatus(long id, Long statusID) {
        orderRepository.changeOrderStatus(id, statusID);
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
        List<Order> orders = orderRepository.getOrdersByOrderNumber(orderNumber);
        return orders;
    }

}
