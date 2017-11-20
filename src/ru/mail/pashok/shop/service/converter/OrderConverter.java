package ru.mail.pashok.shop.service.converter;

import ru.mail.pashok.shop.repository.model.ConfirmedOrder;
import ru.mail.pashok.shop.repository.model.Order;
import ru.mail.pashok.shop.repository.model.StatusEnum;
import ru.mail.pashok.shop.service.model.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {
    public static OrderDTO orderToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getUsername(), order.getItemName(), order.getDiameter(), order.getPrice(), order.getQuantity());
        return orderDTO;
    }
    public static Order orderDTOToOrder(OrderDTO orderDTO) {
        Order order = new Order(orderDTO.getId(), ItemConverter.itemDTOToItem(orderDTO.getItemDTO()), orderDTO.getQuantity(), orderDTO.getUserID());
        return order;
    }

    public static List<Order> orderListDTOToOrderList(List<OrderDTO> orderDTOS){
        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOS) {
            orders.add(orderDTOToOrder(orderDTO));
        }
        return orders;
    }
}
