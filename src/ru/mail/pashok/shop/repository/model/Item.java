package ru.mail.pashok.shop.repository.model;

public class Item {
    private long id, diameter;
    private String itemName;
    double price;

    public Item(long id, String itemName, long diameter, double price) {
        this.id = id;
        this.diameter = diameter;
        this.itemName = itemName;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getDiameter() {
        return diameter;
    }

    public void setDiameter(long diameter) {
        this.diameter = diameter;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", diameter=" + diameter +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                '}';
    }
}
