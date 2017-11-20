package ru.mail.pashok.shop.repository.model;

import ru.mail.pashok.shop.service.ItemServiceImpl;
import ru.mail.pashok.shop.service.UserServiceImpl;
import ru.mail.pashok.shop.service.model.ItemDTO;

import java.util.List;

public class Order {
    private Integer id;
    private Long itemId, quantity, diameter, userID;
    private String username, itemName;
    private Double price, sumPrice;
    private Item item;

    public Order(Integer id, Item item, Long quantity, Long userID) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.itemId = item.getId();
        this.itemName = item.getItemName();
        this.diameter = item.getDiameter();
        this.price = item.getPrice();
        this.sumPrice = price*quantity;
        this.userID = userID;
    }

    public Order(Item item, Long quantity) {
        this.quantity = quantity;
        this.item = item;
    }

    public Order(Integer id, String username, String itemName, long diameter, double price, long quantity) {
        this.id = id;
        this.quantity = quantity;
        this.username = username;
        this.itemName = itemName;
        this.price = price;
        this.diameter = diameter;
        sumPrice = quantity*price;
    }

    public Order(String itemName, long diameter, double price, long quantity) {
        this.quantity = quantity;
        this.diameter = diameter;
        this.itemName = itemName;
        this.price = price;
        this.sumPrice = price*quantity;
    }

    public Order(Integer id, long itemId, long quantity, String username) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.username = username;
        ItemDTO item = ItemServiceImpl.getInstance().getItem(itemId);
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.diameter = item.getDiameter();
        this.sumPrice = price*quantity;
    }
    public Order(Integer id, long itemId, long quantity, Long userID) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.username = UserServiceImpl.getInstance().getUser(userID).getUsername();
        ItemDTO item = ItemServiceImpl.getInstance().getItem(itemId);
        this.itemName = item.getItemName();
        this.price = item.getPrice();
        this.diameter = item.getDiameter();
        this.sumPrice = price*quantity;
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
        return "Order{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", diameter=" + diameter +
                ", username='" + username + '\'' +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", sumPrice=" + sumPrice +
                '}';
    }
}
