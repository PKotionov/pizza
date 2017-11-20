package ru.mail.pashok.shop.service.converter;

import ru.mail.pashok.shop.repository.model.User;
import ru.mail.pashok.shop.service.model.RoleDTO;
import ru.mail.pashok.shop.service.model.UserDTO;


public class UserConverter {

    public static UserDTO userToUserDTO(User user) {
        RoleDTO roleDTO = RoleDTO.roleIdToRoleDTO(user.getRoleId());
        return new UserDTO(user.getId(), user.getUsername(), roleDTO);
    }
}
