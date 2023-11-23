package ru.aston.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aston.service.DepartmentService;
import ru.aston.service.TaskService;
import ru.aston.service.WorkerService;

@Controller
@RequestMapping("/workers")
public class WorkerController {

    private final DepartmentService departmentService;

    private final TaskService taskService;

    private final WorkerService workerService;


    @Autowired
    public WorkerController(DepartmentService departmentService, TaskService taskService,
                            WorkerService workerService) {
        this.departmentService = departmentService;
        this.taskService = taskService;
        this.workerService = workerService;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("workers", workerService.findAll());
        return "worker/all-workers";
    }
    @GetMapping("/{id}")
    public String showProfile(Model model, @PathVariable("id") int id){
        model.addAttribute("worker", workerService.findById(id));
        model.addAttribute("task", taskService.findAll());
        return "";
    }
}
