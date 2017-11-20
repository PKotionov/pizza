package ru.mail.pashok.shop.repository;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionServiceImpl implements ConnectionService {

    private static ConnectionServiceImpl instance;

    private ConnectionServiceImpl() {
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static ConnectionServiceImpl getInstance() {
        if (instance == null) {
            instance = new ConnectionServiceImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        try {
            Properties property = new Properties();
            try {
                InputStream stream = this.getClass().getClassLoader().getResourceAsStream("database.properties");
                if (stream != null) {
                    property.load(stream);
                    String url = property.getProperty("url");
                    String username = property.getProperty("username");
                    String password = property.getProperty("password");
                    return DriverManager.getConnection(url, username, password);
                } else {
                    System.err.println("File database.properties not found.");
                    return null;
                }
            } catch (IOException e) {
                System.err.println("File database.properties not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return null;
    }
}
