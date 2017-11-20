package ru.mail.pashok.shop.service.model;

import ru.mail.pashok.shop.repository.model.Item;
import ru.mail.pashok.shop.service.ItemServiceImpl;
import ru.mail.pashok.shop.service.UserServiceImpl;

public class OrderDTO {
    private Long itemId, quantity, diameter, userID;
    private Integer id;
    private String username, itemName;
    private double price, sumPrice;
    private ItemDTO itemDTO;

    public OrderDTO(Integer id, ItemDTO itemDTO, Long quantity, Long userID) {
        this.id = id;
        this.itemDTO = itemDTO;
        this.quantity = quantity;
        this.itemId = itemDTO.getId();
        this.itemName = itemDTO.getItemName();
        this.diameter = itemDTO.getDiameter();
        this.price = itemDTO.getPrice();
        this.sumPrice = price*quantity;
        this.userID = userID;
    }

    public OrderDTO(Integer id, String username, String itemName, long diameter, double price, long quantity) {
        this.id = id;
        this.quantity = quantity;
        this.username = username;
        this.itemName = itemName;
        this.price = price;
        this.diameter = diameter;
        sumPrice = quantity*price;
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public Long getUserID() {
        return userID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getDiameter() {
        return diameter;
    }

    public void setDiameter(long diameter) {
        this.diameter = diameter;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice) {
        this.sumPrice = sumPrice;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                " id=" + id +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", diameter=" + diameter +
                ", userID=" + userID +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", sumPrice=" + sumPrice +
                '}';
    }
}
