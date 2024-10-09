package ru.leska.taskapp.exception;

public class TaskNotCreatedException extends RuntimeException {
    public TaskNotCreatedException(String message) {
        super(message);
    }
}
