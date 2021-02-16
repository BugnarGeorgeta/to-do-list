package org.example.persistance;

import org.example.domain.Task;
import org.example.transfer.CreateTaskRequest;
import org.example.transfer.UpdateTaskRequest;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    public void createTask(CreateTaskRequest request) throws IOException, SQLException, ClassNotFoundException {
        String sql = "INSERT INTO task(description, deadline) VALUES (?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatemnt = connection.prepareStatement(sql)) {

            preparedStatemnt.setString(1, request.getDescription());
            preparedStatemnt.setDate(2, Date.valueOf(request.getDeadline()));

            preparedStatemnt.executeUpdate();

        }

    }

    public void updateTask(UpdateTaskRequest request, long id) throws IOException, SQLException, ClassNotFoundException {
        String sql = "UPDATE task SET  done=? WHERE id=?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatemnt = connection.prepareStatement(sql)) {

            preparedStatemnt.setBoolean(1, request.isDone());
            preparedStatemnt.setLong(2, id);
            preparedStatemnt.executeUpdate();

        }

    }

    public void deleteTask(long id) throws IOException, SQLException, ClassNotFoundException {
        String sql = "DELETE FROM task WHERE id =?";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatemnt = connection.prepareStatement(sql)) {

            preparedStatemnt.setLong(1, id);
            preparedStatemnt.executeUpdate();

        }
    }

    public List<Task> getTasks() throws IOException, SQLException, ClassNotFoundException {
        String sql = "SELECT id,description,deadline,done FROM task";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            List<Task> tasks = new ArrayList<>();
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getLong("id"));
                task.setDescription(resultSet.getString("description"));
                task.setDeadline(resultSet.getDate("deadline").toLocalDate());
                task.setDone(resultSet.getBoolean("done"));

                tasks.add(task);
            }
            return tasks;
        }
    }
}


