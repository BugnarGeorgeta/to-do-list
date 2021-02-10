package org.example;

import org.example.persistance.TaskRepository;
import org.example.transfer.CreateTaskRequest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException, SQLException {
        TaskRepository taskRepository = new TaskRepository();

        CreateTaskRequest request = new CreateTaskRequest();
        request.setDescription("Learn jdbc");
        request.setDeadline(LocalDate.now().plusWeeks(1));

        taskRepository.createTask(request);
    }
}
