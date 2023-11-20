package ru.aston.dao;

import ru.aston.connect.DataConnection;;
import ru.aston.models.Department;
import ru.aston.models.Task;
import ru.aston.models.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkerDAO {

    private final DataConnection dataConnection;


    public WorkerDAO() {
        this.dataConnection = new DataConnection();
    }

    public List<Worker> getAllWorkers() {
        String sql = "SELECT * FROM Worker";
        List<Worker> workers = new ArrayList<>();

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery();) {



            while (resultSet.next()) {
                int deparId = resultSet.getInt("department");
                Department department = getDepartmentById(deparId);
                Worker worker = new Worker(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("name_user"),
                        department,
                        Collections.emptyList()
                );
                workers.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return workers;
    }

    public Worker createNewWorker(String login, String nameUser, Department department) {
        String sql = "INSERT INTO Worker(login, name_user, department) VALUES (?,?,?)";
        Worker worker = new Worker();

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, login);
            ps.setString(2, nameUser);
            ps.setInt(3, department.getId());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    worker.setId(generatedKeys.getInt(1));
                    worker.setLogin(login);
                    worker.setNameWorker(nameUser);
                    worker.setDepartment(department);
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return worker;
    }

    public void updateWorker(Worker newWorker) {
        String sql = "UPDATE Worker SET login = ?, name_user = ?, department = ? WHERE id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newWorker.getLogin());
            ps.setString(2, newWorker.getNameWorker());
            ps.setObject(3, newWorker.getDepartment().getId());
            ps.setInt(4, newWorker.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении сотрудника", e);
        }
    }

    public void deleteWorker(int workerId) {
        String sql = "DELETE FROM Worker WHERE id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, workerId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении сотрудника", e);
        }
    }

    public Worker getWorkerById(int workerId) {
        String sql = "SELECT * FROM Worker WHERE id = ?";
        Worker worker = new Worker();

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, workerId);

            try(ResultSet resultSet = ps.executeQuery()) {

                if (resultSet.next()) {
                    Department department = getDepartmentById(resultSet.getInt("department"));
                    List<Task> tasks = getTasksByUserId(resultSet.getInt("id"));

                    worker.setId(resultSet.getInt("id"));
                    worker.setLogin(resultSet.getString("login"));
                    worker.setNameWorker(resultSet.getString("name_user"));
                    worker.setDepartment(department);
                    worker.setTasks(tasks);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return worker;
    }


    public List<Task> getTasksByUserId(int userId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * " +
                "FROM Task " +
                "JOIN workertask ON Task.id = workertask.task_id " +
                "JOIN worker ON workertask.user_id = worker.id " +
                "WHERE worker.id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = new Task();
                    task.setId(resultSet.getInt("task_id"));
                    task.setNameTask(resultSet.getString("name_task"));
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public Department getDepartmentById(int departmentId) {
        Department department = new Department();
        String sql = "SELECT * FROM Department WHERE id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, departmentId);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setNameDepart(resultSet.getString("name_depart"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении отдела по идентификатору", e);
        }

        return department;
    }

    public void assignTaskToWorker(int workerId, int taskId) {
        String sql = "INSERT INTO WorkerTask(user_id, task_id) VALUES (?, ?)";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, workerId);
            ps.setInt(2, taskId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при установке задачи для работника", e);
        }
    }

    public void removeTaskFromWorker(int workerId, int taskId) {
        String sql = "DELETE FROM WorkerTask WHERE user_id = ? AND task_id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, workerId);
            ps.setInt(2, taskId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении задачи у работника", e);
        }
    }


}
