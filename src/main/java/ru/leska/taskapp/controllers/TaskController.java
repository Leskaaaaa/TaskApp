package ru.leska.taskapp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.leska.taskapp.dto.TaskDTO;
import ru.leska.taskapp.models.Task;
import ru.leska.taskapp.services.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskController(TaskService taskService, ModelMapper modelMapper) {
        this.taskService = taskService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<TaskDTO> getTasks() {
        return taskService.findAllTasks()
                .stream()
                .map(this::convertToTaskDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") int id) {
        TaskDTO taskDTO = convertToTaskDTO(taskService.findTaskById(id));
        return new ResponseEntity<>(taskDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO taskDTO) {
        Task task = convertToTask(taskDTO);
        Task createdTask = taskService.createTask(task);
        return new ResponseEntity<>(convertToTaskDTO(createdTask), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") int id,
                                        @RequestBody TaskDTO taskDTO) {
        try {
            Task task = convertToTask(taskDTO);
            Task updateTask = taskService.updateTask(id, task);
            return new ResponseEntity<>(convertToTaskDTO(updateTask), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") int id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private TaskDTO convertToTaskDTO(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    private Task convertToTask(TaskDTO taskDTO) {
        return modelMapper.map(taskDTO, Task.class);
    }
}
