package ru.mail.pashok.shop.repository.model;

/**
 * Created by Kotionov_PV on 03.11.17.
 */
public class User {
    private Integer id;
    private String username, password;
    private Integer roleId;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.roleId = builder.roleId;
    }

    public static Builder newUser() {
        return new Builder();
    }


    public static final class Builder {
        private Integer id;
        private String username;
        private String password;
        private Integer roleId;

        private Builder() {
        }

        public User build() {
            return new User(this);
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder roleId(Integer roleId) {
            this.roleId = roleId;
            return this;
        }
    }
}
