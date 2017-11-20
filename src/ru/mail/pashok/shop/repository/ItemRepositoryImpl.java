package ru.mail.pashok.shop.repository;

import ru.mail.pashok.shop.repository.model.Item;
import ru.mail.pashok.shop.service.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    private static ItemRepositoryImpl instance;

    private ItemRepositoryImpl() {
    }

    public static ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepositoryImpl();
        }
        return instance;
    }

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();
    private static Connection connection;

    @Override
    public Item getByItemId(Long id) {
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT t_cost.f_id, f_name, f_diameter, f_price FROM t_cost " +
                        "LEFT JOIN t_pizza ON t_cost.f_pizzaNameID = t_pizza.f_id " +
                        "LEFT JOIN t_size ON t_cost.f_diameterID = t_size.f_id " +
                        "WHERE t_cost.f_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String itemName = resultSet.getString("f_name");
                Long diameter = Long.valueOf((resultSet.getString("f_diameter")));
                Double price = Double.valueOf((resultSet.getString("f_price")));
                return new Item(id, itemName, diameter, price);
            }
            resultSet.close();
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
    public List<Item> showItems() {
        List<Item> items = new ArrayList<>();
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT t_cost.f_id, f_name, f_diameter, f_price FROM t_cost " +
                        "LEFT JOIN t_pizza ON t_cost.f_pizzaNameID = t_pizza.f_id " +
                        "LEFT JOIN t_size ON t_cost.f_diameterID = t_size.f_id")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = Long.valueOf((resultSet.getString("t_cost.f_id")));
                String itemName = resultSet.getString("f_name");
                Long diameter = Long.valueOf((resultSet.getString("f_diameter")));
                Double price = Double.valueOf((resultSet.getString("f_price")));
                items.add(new Item(id, itemName, diameter, price));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return items;
    }
}




