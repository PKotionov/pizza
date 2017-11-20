package ru.mail.pashok.shop.repository;

import ru.mail.pashok.shop.repository.model.User;
import ru.mail.pashok.shop.service.model.UserDTO;

import java.util.List;

public interface UserRepository {
    User getByUserName(String username);

    User getByUserID(Long userID);

    List<UserDTO> showUserList();

    boolean addUser(String username, String password);

    void deleteUser(long id);
}
