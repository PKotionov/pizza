package ru.mail.pashok.shop.service.converter;

import ru.mail.pashok.shop.repository.model.Item;
import ru.mail.pashok.shop.repository.model.UserOrder;
import ru.mail.pashok.shop.service.model.ItemDTO;
import ru.mail.pashok.shop.service.model.UserDTO;
import ru.mail.pashok.shop.service.model.UserOrderDTO;

import java.util.ArrayList;
import java.util.List;

import static ru.mail.pashok.shop.service.converter.ItemConverter.itemToItemDTO;

public class UserOrderConverter {
    public static UserOrderDTO userOrderToUserOrderDTO(UserOrder userOrder){
        return new UserOrderDTO(userOrder.getId(), userOrder.getUsername(), userOrder.getStatus());

    }
    public static List<UserOrderDTO> userOrderListToUserOrderDTOList(List<UserOrder> userOrders){
        List<UserOrderDTO> userOrderDTOS = new ArrayList<>();
        for (UserOrder userOrder : userOrders) {
            userOrderDTOS.add(userOrderToUserOrderDTO(userOrder));
        }
        return userOrderDTOS;
    }
}
