package ru.aston.dao;

import ru.aston.connect.DataConnection;
import ru.aston.models.Adress;
import ru.aston.models.Management;
import ru.aston.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagementDAO {
    private PreparedStatement ps;
    private final Connection connection;



    public ManagementDAO() {
        this.connection = new DataConnection().conn();
    }


    public Management createManagemnt(String nameCompany, Adress adress) throws SQLException {
        String sql = "INSERT INTO Management(adress_id, name_company) VALUES (?,?)";
        ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, adress.getId());
        ps.setString(2, nameCompany);
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        return new Management(resultSet.getInt(1), adress.getId(),nameCompany);
    }

    public List<Management> getAllManager() throws SQLException {
        List<Management> managementList = new ArrayList<>();
        String sql = "SELECT * FROM Management";

        ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()){
            managementList.add(new Management(resultSet.getInt("id"),
                    resultSet.getInt("adress_id"),
                    resultSet.getString("name_company")));
        }
        return managementList;
    }

    public Management foundManagementCompany(String nameCompany) throws SQLException {
        String sql = "SELECT * FROM Management WHERE name LIKE ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + nameCompany + "%");

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int adressId = resultSet.getInt("adress_id");

                    return new Management(id, adressId, name);
                } else {
                    return null;
                }
            }
        }
    }

    public void deleteManagement(int id) throws SQLException{
        String sql = "DELETE FROM Management WHERE id = ?";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
