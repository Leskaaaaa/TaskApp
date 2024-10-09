package ru.leska.taskapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.leska.taskapp.models.Task;
import ru.leska.taskapp.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findTaskById(int id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    @Transactional
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(int id, Task updatedTask) {
        if (taskRepository.existsById(id)) {
            updatedTask.setId(id);
            return taskRepository.save(updatedTask);
        } else {
            throw new RuntimeException("Task not created");
        }
    }

    @Transactional
    public void deleteTask(int id) {
        taskRepository.deleteById(id);
    }
}
