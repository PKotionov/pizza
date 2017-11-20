package ru.mail.pashok.shop.repository.model;

import ru.mail.pashok.shop.service.UserServiceImpl;

public class UserOrder {
    Long id;
    String username;
    StatusEnum status;

    public UserOrder(long id, String username, StatusEnum status) {
        this.id = id;
        this.username = username;
        this.status = status;
    }

    public UserOrder(long id, long userID, int statusID) {
        this.id = id;
        this.username = UserServiceImpl.getInstance().getUser(userID).getUsername();
        this.status = StatusEnum.getStatusFromStatusID(statusID);
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public StatusEnum getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UserOrderDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", status=" + status +
                '}';
    }
}
