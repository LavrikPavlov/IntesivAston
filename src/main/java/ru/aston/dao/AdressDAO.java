package ru.aston.dao;

import ru.aston.connect.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdressDAO {
    private PreparedStatement preparedStatement;

    private DataConnection dataConnection;
    private final Connection connection;


    public AdressDAO() throws SQLException {
        this.connection = dataConnection.conn();
    }


}
