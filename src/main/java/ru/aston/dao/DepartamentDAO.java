package ru.aston.dao;

import ru.aston.connect.DataConnection;
import ru.aston.models.Department;
import ru.aston.models.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class DepartamentDAO {

    private final DataConnection dataConnection;


    public DepartamentDAO() {
        this.dataConnection = new DataConnection();
    }

    public List<Department> getAllDepartments(){
        List<Department> departmentList = new ArrayList<>();
        String sql = "SELECT * FROM Department";

        try(Connection connection = dataConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();) {
            while (resultSet.next()){
                Department department = new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("name_depart"),
                        getWorkersByDepartmentId(resultSet.getInt("id"))

                );
                departmentList.add(department);
                connection.close();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return departmentList;
    }

    public Department createNewDepartment(String nameDepart){
        Department department = new Department();
        String sql = "INSERT INTO Department(name_depart) VALUES (?)";

        try(Connection connection = dataConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);){

            ps.setString(1, nameDepart);
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    department.setId(generatedKeys.getInt(1));
                }
                connection.close();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return department;
    }

    public void deleteDepartment(int idDepart) {
        String sql = "DELETE FROM Department WHERE id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idDepart);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении отдела", e);
        }
    }

    public void updateDepartment(Department newDepartment) {
        String sql = "UPDATE Department SET name_depart = ? WHERE id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newDepartment.getNameDepart());
            ps.setInt(2, newDepartment.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении отдела", e);
        }
    }

    public Department getDepartmentById(int idDepart){
        Department department = new Department();
        String sql = "SELECT * FROM Department WHERE id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idDepart);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                department.setId(resultSet.getInt("id"));
                department.setNameDepart(resultSet.getString("name_depart"));
                department.setWorkers(getWorkersByDepartmentId(idDepart));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return department;
    }

    public List<Worker> getWorkersByDepartmentId(int departmentId) {
        List<Worker> workers = new ArrayList<>();
        String sql = "SELECT w.id AS worker_id, w.login, w.name_user, d.id AS department_id, d.name_depart " +
                "FROM Worker w " +
                "JOIN Department d ON w.department = d.id " +
                "WHERE d.id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, departmentId);

            try (ResultSet resultSet = ps.executeQuery()) {
                Map<Integer, Department> departmentMap = new HashMap<>();

                while (resultSet.next()) {
                    int departmentIdResult = resultSet.getInt("department_id");

                    Department department = departmentMap.computeIfAbsent(departmentIdResult, id -> {
                        Department newDepartment = new Department();
                        newDepartment.setId(id);
                        try {
                            newDepartment.setNameDepart(resultSet.getString("name_depart"));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        return newDepartment;
                    });

                    Worker worker = new Worker(
                            resultSet.getInt("worker_id"),
                            resultSet.getString("login"),
                            resultSet.getString("name_user"),
                            department,
                            Collections.emptyList()
                    );

                    workers.add(worker);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }
}

