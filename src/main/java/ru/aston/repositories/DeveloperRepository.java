package ru.aston.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.models.workers.Developer;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
}
