package ru.mail.pashok.shop.service.model;

import ru.mail.pashok.shop.repository.model.StatusEnum;

public class ConfirmedOrderDTO extends OrderDTO {
    private StatusEnum status;
    private Long orderNumber;

    public Long getOrderNumber() {
        return orderNumber;
    }

    public ConfirmedOrderDTO(OrderDTO order, StatusEnum status, Long orderNumber) {
        super(order.getId(), order.getUsername(), order.getItemName(), order.getDiameter(), order.getPrice(), order.getQuantity());
        this.status = status;
        this.orderNumber = orderNumber;
    }

    public StatusEnum getStatus() {
        return status;
    }
}
