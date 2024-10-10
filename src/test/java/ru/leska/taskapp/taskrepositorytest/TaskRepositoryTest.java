package ru.leska.taskapp.taskrepositorytest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.leska.taskapp.exception.TaskNotFoundException;
import ru.leska.taskapp.models.Task;
import ru.leska.taskapp.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void TaskRepository_FindAllTask_ReturnAllTasks() {
        // Arrange
        Task task = Task.builder()
                .title("First task")
                .description("First description")
                .date(LocalDate.now())
                .completed(true)
                .build();

        taskRepository.save(task);

        // Act
        List<Task> tasks = taskRepository.findAll();

        // Assert
        Assertions.assertEquals(1, tasks.size());
    }

    @Test
    public void TaskRepository_SaveTask_ReturnSavedTask() {
        // Arrange
        Task task = Task.builder()
                .title("First title")
                .description("First description")
                .date(LocalDate.now())
                .completed(true)
                .build();

        // Act
        Task savedTask = taskRepository.save(task);

        // Assert
        Assertions.assertNotNull(savedTask);
    }
}
