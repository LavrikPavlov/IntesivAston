package ru.aston.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.models.abstractModel.Worker;

import javax.persistence.criteria.CriteriaBuilder;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
}
