package ru.mail.pashok.shop.repository.model;

public class ConfirmedOrder extends Order {
    private StatusEnum status;
    private Long orderNumber;

    public ConfirmedOrder(Order order, StatusEnum status, Long orderNumber) {
        super(order.getId(), order.getUsername(), order.getItemName(), order.getDiameter(), order.getPrice(), order.getQuantity());
        this.status = status;
        this.orderNumber = orderNumber;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public StatusEnum getStatus() {
        return status;
    }
}
