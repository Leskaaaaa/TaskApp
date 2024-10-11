package ru.leska.taskapp.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(int id) {
        super("Task not found exception " + id);
    }
}
