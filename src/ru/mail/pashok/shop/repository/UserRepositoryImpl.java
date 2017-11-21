package ru.mail.pashok.shop.repository;

import ru.mail.pashok.shop.repository.model.Card;
import ru.mail.pashok.shop.repository.model.User;
import ru.mail.pashok.shop.service.model.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kotionov_PV on 03.11.17.
 */
public class UserRepositoryImpl implements UserRepository {
    private static UserRepository instance;

    private UserRepositoryImpl() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();
    private static Connection connection;

    @Override
    public User getByUserName(String username) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try {
                    Statement statement = connection.createStatement();

                    ResultSet resultSet = statement.executeQuery(String.format(
                            "SELECT t_user.f_id, f_userName, f_password, f_roleId from t_user where f_userName ='%s'",
                            username));
                    if (resultSet.next()) {
                        return User.newUser().
                                id(resultSet.getInt("f_id"))
                                .username(resultSet.getString("f_userName"))
                                .password(resultSet.getString("f_password"))
                                .roleId(resultSet.getInt("f_roleId"))
                                .build();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public User getByUserID(Long userID) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(String.format(
                            "SELECT f_id, f_userName, f_password, f_roleId from t_user where f_id ='%d'",
                            userID));
                    if (resultSet.next()) {
                        return User.newUser().
                                id(resultSet.getInt("f_id"))
                                .username(resultSet.getString("f_userName"))
                                .password(resultSet.getString("f_password"))
                                .roleId(resultSet.getInt("f_roleId"))
                                .build();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    public List<UserDTO> showUserList() {
        connection = connectionService.getConnection();
        List<UserDTO> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT t_user.f_id, f_userName, f_password, f_roleId, T_Role.F_Role FROM t_user JOIN t_role ON t_user.f_roleID = t_role.f_id")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = Long.valueOf((resultSet.getString("t_user.f_id")));
                String userName = resultSet.getString("f_userName");
                String roleString = resultSet.getString("f_role");
                UserDTO user = new UserDTO(id, userName, roleString);
                users.add(user);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    public boolean addUser(String username, String password) {
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO t_user (f_username, f_password, f_roleId) VALUES(?, ?, 2)")) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteUser(long id) {
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM t_user WHERE f_id = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public List<Card> getCardList(Long userId) {
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT f_id, f_userID, f_cardNumber, f_month, f_year, f_description FROM t_card WHERE f_userID = ?")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Card> cards = new ArrayList<>();
            while (resultSet.next()) {
                Long id = resultSet.getLong("f_id");
                Long userID = resultSet.getLong("f_userID");
                String cardNumber = resultSet.getString("f_cardNumber");
                Integer month = resultSet.getInt("f_month");
                Integer year = resultSet.getInt("f_year");
                String description = resultSet.getString("f_description");
                String username = getByUserID(userID).getUsername();
                cards.add(new Card(id, userID, cardNumber, description, username, year, month));
            }
            statement.close();
            return cards;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }

    @Override
    public void addNewCardToDataBase(Card card) {
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO t_card(f_userID, f_cardNumber, f_month, f_year, f_description) VALUES(?, ?, ?, ?, ?)")){
                statement.setLong(1, card.getUserID());
                statement.setString(2, card.getCardNumber());
            statement.setInt(3, card.getMonth());
            statement.setInt(4, card.getYear());
            statement.setString(5, card.getDescription());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}