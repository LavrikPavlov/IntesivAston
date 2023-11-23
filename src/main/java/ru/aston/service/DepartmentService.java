package ru.aston.service;

import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aston.models.Department;
import ru.aston.repositories.DepartmentRepository;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
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
}
