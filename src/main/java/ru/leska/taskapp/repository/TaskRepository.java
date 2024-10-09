package ru.leska.taskapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.leska.taskapp.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
