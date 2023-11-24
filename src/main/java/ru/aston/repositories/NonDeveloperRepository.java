package ru.aston.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.models.workers.NonDeveloper;

@Repository
public interface NonDeveloperRepository extends JpaRepository<NonDeveloper, Integer> {
}
