package ru.aston.dao;

import ru.aston.connect.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ManagementDAO {
    private PreparedStatement preparedStatement;

    private DataConnection dataConnection;
    private final Connection connection;

    public ManagementDAO(Connection connection) {
        this.connection = dataConnection.conn();
    }
}
