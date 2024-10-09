package ru.leska.taskapp.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class TaskDTO {

    @NotBlank(message = "Field Title should not been empty")
    private String title;

    @NotBlank(message = "Field Description should not been empty")
    private String description;

    @NotBlank(message = "Field Date should not been empty")
    private Date date;

    private boolean completed;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
