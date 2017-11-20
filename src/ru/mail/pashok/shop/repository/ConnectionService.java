package ru.mail.pashok.shop.repository;

import java.sql.Connection;

public interface ConnectionService {
    Connection getConnection();
}
