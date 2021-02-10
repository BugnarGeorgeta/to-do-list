package org.example.persistance;

import org.example.transfer.CreateTaskRequest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskRepository {

    public void createTask(CreateTaskRequest request) throws IOException, SQLException {
        String sql = "INSERT INTO task(description, deadline) VALUES (?,?)";

        try (Connection connection = DatabaseConfiguration.getConnection();
             PreparedStatement preparedStatemnt = connection.prepareStatement(sql)) {

            preparedStatemnt.setString(1, request.getDescription());
            preparedStatemnt.setDate(2, Date.valueOf(request.getDeadline()));

            preparedStatemnt.executeUpdate();

        }

    }
}
