package ru.mail.pashok.shop;

import ru.mail.pashok.shop.controller.validator.UserNameValidation;
import ru.mail.pashok.shop.service.UserService;
import ru.mail.pashok.shop.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotionov_PV on 04.11.17.
 */
public class Test {
    public static void main(String[] args) {
        String username = "admin", password = "admin";
        List<String> errors = new ArrayList<>();
        UserService userService = UserServiceImpl.getInstance();
        UserNameValidation.checkUserName(username, errors);
        if (errors.isEmpty()) {
            if (userService.isUserValid(username.trim(), password.trim())) {
                System.out.println("Your are welcome!");
            } else {
                System.out.println("User is not valid. Check username or password.");
            }
        } else {
            System.out.println("Errors: " + errors);
        }
    }
}
