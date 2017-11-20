package ru.mail.pashok.shop.controller.validator;

import java.util.List;

/**
 * Created by Kotionov_PV on 03.11.17.
 */
public class UserNameValidation {

    public static List<String> checkUserName(String username, List<String> errors) {
        if (username.trim().length() > 21) {
            errors.add("Length of username more than 20 letters.Check username.");
        }
        if (username == null || username.trim() == "") {
            errors.add("Please, enter your username.");
        }
        return errors;
    }
}
