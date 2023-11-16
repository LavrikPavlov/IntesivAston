package ru.aston.dao;

import com.sun.org.apache.bcel.internal.generic.PUSH;
import ru.aston.connect.DataConnection;
import ru.aston.models.Adress;
import ru.aston.models.Management;
import ru.aston.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdressDAO {
    private PreparedStatement ps;

    private final Connection connection;

    public AdressDAO() throws SQLException {
        this.connection = new DataConnection().conn();
    }

    public Adress createNewAdreaa(String adressFull, Person person) throws SQLException {
        String sql = "INSERT INTO Adress(adress_full, person_id) VALUES (?,?)";
        int personId;
        ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, adressFull);
        if(person != null) {
            ps.setInt(2, person.getId());
            personId = person.getId();
        } else {
            personId = Integer.parseInt(null);
        }
        ps.executeUpdate();
        ResultSet resultSet = ps.getGeneratedKeys();
        return new Adress(resultSet.getInt(1), personId, adressFull);
    }

    public List<Adress> getAllAdress() throws SQLException{
        List<Adress> adressList = new ArrayList<>();
        String sql = "SELECT * FROM Adress";

        ps = connection.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()){
            adressList.add(new Adress(resultSet.getInt("id"),
                    resultSet.getInt("person_id"),
                    resultSet.getString("adress_full")));
        }
        return adressList;
    }

    public void updateAdress(int id, String adress_full, Person person) throws SQLException {
        String sql = "UPDATE Adress SET adress_full = ?, person_id = ? WHERE id = ?";
        ps = connection.prepareStatement(sql);

        ps.setString(1, adress_full);
        ps.setInt(2, person.getId());
        ps.setInt(3, id);
        ps.executeUpdate();
    }





}
