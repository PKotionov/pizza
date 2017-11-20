package ru.mail.pashok.shop.createSQL;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Kotionov_PV on 03.11.17.
 */
public class CreateDataBase {
    public static void main(String[] args) {
        File file = new File(".\\src\\ru\\mail\\pashok\\shop\\createSQL\\databases.txt");
        if (file.exists()) {
            createTables(file);
        } else {
            System.out.println("File not found.");
        }
    }
    public static void createTables(File file) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/TEST", "root", "");
             BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE T_PIZZA");
            statement.executeUpdate("DROP TABLE T_SIZE");
            statement.executeUpdate("DROP TABLE T_COST");
            statement.executeUpdate("DROP TABLE T_USER");
            statement.executeUpdate("DROP TABLE T_ROLE");
            statement.executeUpdate("DROP TABLE T_ORDER");
            statement.executeUpdate("DROP TABLE t_totalOrder");
            statement.executeUpdate("DROP TABLE t_orderUser");
            int count = 0;
            while ((line = reader.readLine()) != null) {
                count += statement.executeUpdate(line);
            }
            System.out.println("Added " + count + " rows.");
            statement.close();
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}


