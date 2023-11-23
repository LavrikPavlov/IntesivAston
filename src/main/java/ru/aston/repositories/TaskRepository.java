package ru.aston.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
