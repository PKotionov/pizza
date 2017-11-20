package ru.mail.pashok.shop.service;

import ru.mail.pashok.shop.service.model.UserDTO;

/**
 * Created by Kotionov_PV on 03.11.17.
 */
public interface UserService {

    UserDTO getUser(String username);

    boolean isUserValid(String username, String password);
}
