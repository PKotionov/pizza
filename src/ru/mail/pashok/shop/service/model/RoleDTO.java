package ru.mail.pashok.shop.service.model;

public enum RoleDTO {
    ADMIN,
    USER;

    public static RoleDTO roleIdToRoleDTO(Integer roleID){
        RoleDTO roleDTO = null;
        switch (roleID){
            case 1:
                roleDTO =  ADMIN;
                break;
            case 2:
                roleDTO =  USER;
                break;
            default:
                System.out.println("Error in RoleDTO: there are no such roleID.");
        }
        return roleDTO;
    }
}
