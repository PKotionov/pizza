package ru.mail.pashok.shop.service.model;

import ru.mail.pashok.shop.repository.model.StatusEnum;
import ru.mail.pashok.shop.service.UserServiceImpl;

public class UserOrderDTO {
    long id;
    String username;
    StatusEnum status;

    public UserOrderDTO(long id, String username, StatusEnum status) {
        this.id = id;
        this.username = username;
        this.status = status;
    }

    public UserOrderDTO(long id, long userID, int statusID) {
        this.id = id;
        this.username = UserServiceImpl.getInstance().getUser(userID).getUsername();
        this.status = StatusEnum.getStatusFromStatusID(statusID);
    }


    public long getId() {
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
