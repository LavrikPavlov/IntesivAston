package ru.aston.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.models.Department;
import ru.aston.models.workers.Worker;
import ru.aston.repositories.DepartmentRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll(){
        return  departmentRepository.findAll();
    }

    public Department findById(int id){
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElse(null);
    }

    @Transactional
    public void save(Department department){
        departmentRepository.save(department);
    }

    @Transactional
    public void update(int id, Department updateDepartment){
        updateDepartment.setId(id);
        departmentRepository.save(updateDepartment);
    }

    @Transactional
    public void delete(int id){
        departmentRepository.deleteById(id);
    }

    public List<Worker> getWorkersByDepartmentId(int id){
        Optional<Department> department = departmentRepository.findById(id);
        if(department.isPresent()){
            Hibernate.initialize(department.get().getWorkers());
            return department.get().getWorkers();
        } else {
            return Collections.emptyList();
        }
    }
}
