package ru.leska.taskapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskDTO {

    @NotBlank(message = "Field Title should not been empty")
    private String title;

    @NotBlank(message = "Field Description should not been empty")
    private String description;

    @NotNull(message = "Field Date should not been empty")
    private LocalDate date;

    private boolean completed;

}
