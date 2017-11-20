package ru.mail.pashok.shop.service;

import ru.mail.pashok.shop.repository.*;
import ru.mail.pashok.shop.repository.model.User;
import ru.mail.pashok.shop.service.converter.UserConverter;
import ru.mail.pashok.shop.service.model.RoleDTO;
import ru.mail.pashok.shop.service.model.UserDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Kotionov_PV on 03.11.17.
 */
public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    @Override
    public UserDTO getUser(String username) {
        User user = userRepository.getByUserName(username);
        if (user != null) {
            return UserConverter.userToUserDTO(user);
        }
        return null;
    }

    public RoleDTO getUserRole(String username) {
        User user = userRepository.getByUserName(username);
        if (user != null) {
            return UserConverter.userToUserDTO(user).getRoleDTO();
        }
        return null;
    }

    public UserDTO getUser(Long userID) {
        User user = userRepository.getByUserID(userID);
        if (user != null) {
            return UserConverter.userToUserDTO(user);
        }
        return null;
    }

    public Long getUserId(String username) {
        User user = userRepository.getByUserName(username);
        if (user != null) {
            return UserConverter.userToUserDTO(user).getId();
        }
        return null;
    }

    @Override
    public boolean isUserValid(String username, String password) {
        User user = userRepository.getByUserName(username);
        System.out.println(user);
        try {
            if (Objects.equals(user.getPassword(), password.trim())) {
                return true;
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static List<UserDTO> showUserList() {
        return UserRepositoryImpl.getInstance().showUserList();
    }

    public boolean addUser(String username, String password) {
        try {
            UserRepositoryImpl.getInstance().addUser(username, password);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteUser(long id) {
        try {
            UserRepositoryImpl.getInstance().deleteUser(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}