package ru.mail.pashok.shop.repository;

import ru.mail.pashok.shop.repository.model.*;
import ru.mail.pashok.shop.service.ItemServiceImpl;
import ru.mail.pashok.shop.service.OrderServiceImpl;
import ru.mail.pashok.shop.service.UserServiceImpl;
import ru.mail.pashok.shop.service.converter.ItemConverter;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private static OrderRepository instance;

    private OrderRepositoryImpl() {
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepositoryImpl();
        }
        return instance;
    }

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();
    private static Connection connection;

    public void addNewOrder(Long userID, List<Order> basket) {
        connection = connectionService.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO t_orderUser (f_userID, f_statusID) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userID);
            statement.setInt(2, 1);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            generatedKeys.next();
            long numberOrders = generatedKeys.getLong(1);
            OrderServiceImpl.getInstance().setLastOrderNumber(numberOrders);
            generatedKeys.close();
            addOrderItems(userID, connection, basket);
        } catch (SQLException e) {
            try {
                System.out.println(e.getMessage());
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println(e.getMessage());
            }
            try {
                connection.close();
            } catch (SQLException e1) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addOrderItems(Long userID, Connection connection, List<Order> basket) {
        System.out.println("addOrderItems " + basket);
        if (basket != null) {
            for (Order order : basket) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO t_order(f_userID, f_itemID, f_quantity, f_orderNumber, f_statusID) VALUES(?, ?, ?, ?, ?)")) {
                    statement.setLong(1, userID);
                    statement.setLong(2, order.getItemId());
                    statement.setLong(3, order.getQuantity());
                    statement.setLong(4, OrderServiceImpl.getInstance().getLastOrderNumber());
                    statement.setLong(5, 1);
                    statement.executeUpdate();
                } catch (SQLException e) {
                    try {
                        connection.rollback();
                        connection.close();
                    } catch (SQLException e1) {
                        System.out.println(e1.getMessage());
                    }
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            addTotalOrder(connection, basket);
        } else {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void addTotalOrder(Connection connection, List<Order> basket) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO t_totalOrder (f_orderNumber, f_idItem) VALUES (?, ?)")) {
            if (basket.size() > 0) {
                for (Order order : basket) {
                    statement.setLong(1, OrderServiceImpl.getInstance().getLastOrderNumber());
                    statement.setLong(2, order.getItemId());
                    statement.executeUpdate();
                }
                connection.commit();
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println(e.getMessage());
            }
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public List<ConfirmedOrder> getClientOrders(Long userID) {
        List<ConfirmedOrder> orders = new ArrayList<>();
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT f_id, f_userID, f_itemID, f_quantity, f_orderNumber, f_statusID FROM t_order")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long userIDFromDB = (resultSet.getLong("f_userID"));
                if (userID == userIDFromDB) {
                    Integer id = Integer.valueOf((resultSet.getString("f_id")));
                    Long itemID = resultSet.getLong("f_itemID");
                    Long quantity = resultSet.getLong("f_quantity");
                    Long orderNumber = resultSet.getLong("f_orderNumber");
                    int statusID = resultSet.getInt("f_statusID");
                    StatusEnum status = StatusEnum.getStatusFromStatusID(statusID);
                    Item item = ItemRepositoryImpl.getInstance().getByItemId(itemID);
                    Order order = new Order(id, item, quantity, userID);
                    orders.add(new ConfirmedOrder(order, status, orderNumber));
                }
            }
            resultSet.close();
            System.out.println(orders);
            return orders;
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
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

//    public List<ConfirmedOrder> getAllOrders() {
//        List<ConfirmedOrder> orders = new ArrayList<>();
//        connection = connectionService.getConnection();
//        try (PreparedStatement statement = connection.prepareStatement(
//                "SELECT f_id, f_userID, f_itemID, f_quantity, f_orderNumber, f_statusID FROM t_order")) {
//            System.out.println("result set started");
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                System.out.println("while started");
//                Integer id = Integer.valueOf((resultSet.getString("f_id")));
//                System.out.println("id " + id);
//                Long userID = resultSet.getLong("f_userID");
//                System.out.println("userID " + userID);
//                Long itemID = resultSet.getLong("f_itemID");
//                System.out.println("itemID " + itemID);
//                Long quantity = resultSet.getLong("f_quantity");
//                System.out.println(quantity);
//                Long orderNumber = resultSet.getLong("f_orderNumber");
//                System.out.println(orderNumber);
//                int statusID = resultSet.getInt("f_statusID");
//                System.out.println(statusID);
//                StatusEnum status = StatusEnum.getStatusFromStatusID(statusID);
//                System.out.println(status);
//                Order order = new Order(id, itemID, quantity, userID);
//                orders.add(new ConfirmedOrder(order, status, orderNumber));
//            }
//            System.out.println("while finished");
//            resultSet.close();
//            return orders;
//        } catch (SQLException e1) {
//            System.out.println(e1.getMessage());
//            e1.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//        return null;
//    }


    public List<UserOrder> getUserOrders() {
        List<UserOrder> orders = new ArrayList<>();
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT f_id, f_userID, f_statusID FROM t_orderUser")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("f_id");
                Long userID = resultSet.getLong("f_userID");
                int statusID = resultSet.getInt("f_statusID");
                orders.add(new UserOrder(id, userID, statusID));
            }
            resultSet.close();
            return orders;
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
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
    public List<Order> getOrdersByOrderNumber(Long orderNumber) {
        List<Order> orders = new ArrayList<>();
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT f_idItem FROM t_totalOrder WHERE f_orderNumber = ?")) {
            statement.setLong(1, orderNumber);
            ResultSet resultSet = statement.executeQuery();
            PreparedStatement statement2 = connection.prepareStatement(
                    "SELECT f_quantity FROM t_order WHERE f_orderNumber = ?");
            statement2.setLong(1, orderNumber);
            ResultSet resultSet2 = statement2.executeQuery();
            while (resultSet.next() && resultSet2.next()) {
                int idItem = resultSet.getInt("f_idItem");
                int quantity = resultSet2.getInt("f_quantity");
                Order order = OrderRepositoryImpl.getInstance().getOrderByNumber(idItem, quantity);
                orders.add(order);
            }
            resultSet.close();
            resultSet2.close();
            statement2.close();
            return orders;
        } catch (SQLException e1) {
            e1.printStackTrace();
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

    public Order getOrderByNumber(int idItem, int quantity) {
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT t_pizza.f_name, t_size.f_diameter, t_cost.f_price FROM t_cost " +
                        "LEFT JOIN t_pizza ON t_cost.f_pizzaNameID = t_pizza.f_id " +
                        "LEFT JOIN t_size ON t_cost.f_diameterID = t_size.f_id " +
                        "WHERE t_cost.f_id = ?")) {
            statement.setInt(1, idItem);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String itemName = resultSet.getString("t_pizza.f_name");
                int diameter = resultSet.getInt("t_size.f_diameter");
                double price = resultSet.getDouble("t_cost.f_price");
                resultSet.close();
                return new Order(itemName, diameter, price, quantity);
            }
        } catch (SQLException e1) {
            System.out.println(e1.getMessage());
            e1.printStackTrace();
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
    public long getLastOrderNumber() {
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM t_orderUser")) {
            ResultSet resultSet = statement.executeQuery();
            long maxID = 0;
            while (resultSet.next()) {
                if (maxID < resultSet.getLong("id")) {
                    maxID = resultSet.getLong("id");
                }
            }
            resultSet.close();
            return maxID;
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
        return 0;
    }

    public void deleteOrderAdmin(long id) {
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM t_order WHERE f_orderNumber = ?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM t_orderUser WHERE f_id = ?")) {
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
    public void changeOrderStatus(long id, long statusID) {
        connection = connectionService.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE t_orderUser SET f_statusID = ? WHERE f_id = ?");
             PreparedStatement statement2 = connection.prepareStatement(
                     "UPDATE t_order SET f_statusID = ? WHERE f_orderNumber = ?")) {
            statement.setLong(1, statusID);
            statement.setLong(2, id);
            statement.executeUpdate();
            statement.close();
            statement2.setLong(1, statusID);
            statement2.setLong(2, id);
            statement2.executeUpdate();
            statement2.close();
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
}