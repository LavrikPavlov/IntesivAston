package ru.aston.dao;

import ru.aston.connect.DataConnection;
import ru.aston.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private PreparedStatement ps;

    private final Connection connection;

    public PersonDAO() {
        this.connection = new DataConnection().conn();
    }


    public Person createPerson(String name, String email) throws SQLException {
        String sql = "INSERT INTO Person(name, email) VALUES (?,?)";
        ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, name);
        ps.setString(2, email);
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        return new Person(resultSet.getInt(1), name, email);


    }

    public List<Person> getAllPerson() throws SQLException{
        List<Person> personList = new ArrayList<>();
        String sql = "SELECT * FROM Person";

        ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()){
            personList.add(new Person(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email")));
        }

        return personList;
    }

    public void updatePerson(int id, String name, String email) throws SQLException {
        String sql = "UPDATE Person SET name = ?, email = ? WHERE id = ?";
        ps = connection.prepareStatement(sql);

        ps.setString(1, name);
        ps.setString(2, email);

        ps.setInt(3, id);
        ps.executeUpdate();
    }

    public void deletePerson(int id) throws SQLException{
        String sql = "DELETE FROM Person WHERE id = ?";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public Person getPersonById(int id) throws SQLException {
        String sql = "SELECT * FROM Person WHERE id = ?";
        ps = connection.prepareStatement(sql);
        ps.setInt(1, id);

        try (ResultSet resultSet = ps.executeQuery()) {
            if (resultSet.next()) {
                return new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email")
                );
            } else {
                throw new SQLException("Person not found with ID: " + id);
            }
        }
    }
}
