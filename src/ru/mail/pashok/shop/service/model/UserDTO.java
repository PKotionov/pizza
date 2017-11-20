package ru.mail.pashok.shop.service.model;

public class UserDTO {
    private long id;
    private String username, roleString;
    private RoleDTO roleDTO;

    public UserDTO(long id, String username, RoleDTO roleDTO) {
        this.id = id;
        this.username = username;
        this.roleDTO = roleDTO;
    }

    public UserDTO(long id, String username, String roleString) {
        this.id = id;
        this.username = username;
        this.roleString = roleString;
    }

    public RoleDTO getRoleDTO() {
        return roleDTO;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", roleDTO=" + roleDTO +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleString() {
        return roleString;
    }

    public void setRoleString(String roleString) {
        this.roleString = roleString;
    }
}
