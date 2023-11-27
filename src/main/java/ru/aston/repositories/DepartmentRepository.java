package ru.aston.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.models.Department;


@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
