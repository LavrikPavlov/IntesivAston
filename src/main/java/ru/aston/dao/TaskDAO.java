package ru.aston.dao;

import ru.aston.connect.DataConnection;
import ru.aston.models.Department;
import ru.aston.models.Task;
import ru.aston.models.Worker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    private final DataConnection dataConnection;


    public TaskDAO() {
        this.dataConnection = new DataConnection();
    }

    public List<Task> getAllTask() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM Task";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery();) {
            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name_task"),
                        resultSet.getString("task_text"),
                        null
                );
                tasks.add(task);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }

    public Task createNewTask(Task newTask) {;
        Task task = new Task();
        String sql = "INSERT INTO Task(name_task, task_text) VALUES (?, ?)";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, newTask.getNameTask());
            ps.setString(2, newTask.getTaskText());
            ps.executeUpdate();


            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return task;
    }

    public void deleteTask(int idTask) {
        String sql = "DELETE FROM Task WHERE id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idTask);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении задачи", e);
        }
    }

    public void updateTask(Task newTask) {
        String sql = "UPDATE Task SET name_task = ?, task_text = ? WHERE id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, newTask.getNameTask());
            ps.setString(2, newTask.getTaskText());
            ps.setInt(3, newTask.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении задачи", e);
        }
    }

    public Task getTaskById(int idTask) {
        Task task = new Task();
        String sql = "SELECT * FROM Task WHERE id = ?";

        try(Connection connection = dataConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idTask);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                task.setId(resultSet.getInt("id"));
                task.setNameTask(resultSet.getString("name_task"));
                task.setTaskText(resultSet.getString("task_text"));
                task.setWorkers(getWorkersByTaskId(resultSet.getInt("id")));
            }

        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return task;
    }

    private List<Worker> getWorkersByTaskId(int idTask) {
        List<Worker> workers = new ArrayList<>();
        String sql = "SELECT w.* FROM Worker w " +
                "JOIN WorkerTask ut ON w.id = ut.user_id " +
                "WHERE ut.task_id = ?";

        try (Connection connection = dataConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, idTask);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Worker worker = new Worker();
                worker.setId(resultSet.getInt("id"));
                worker.setLogin(resultSet.getString("login"));
                worker.setNameWorker(resultSet.getString("name_user"));
                workers.add(worker);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении работников по задаче", e);
        }

        return workers;
    }
}
