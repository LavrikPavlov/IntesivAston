package ru.aston.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aston.models.workers.Developer;
import ru.aston.models.workers.NonDeveloper;
import ru.aston.models.workers.Worker;
import ru.aston.service.DepartmentService;
import ru.aston.service.TaskService;
import ru.aston.service.WorkerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/workers")
public class WorkerController {

    private final TaskService taskService;
    private final DepartmentService departmentService;
    private final WorkerService workerService;


    @Autowired
    public WorkerController(TaskService taskService, DepartmentService departmentService,
                            WorkerService workerService) {
        this.taskService = taskService;
        this.departmentService = departmentService;
        this.workerService = workerService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("workers", workerService.findAll());
        return "worker/all-workers";
    }

    @GetMapping("/{id}")
    public String showProfile(Model model, @PathVariable("id") int id){
        model.addAttribute("worker", workerService.findById(id));
        model.addAttribute("tasks", taskService.findAll());
        return "worker/profile";
    }

    @GetMapping("/new")
    public String create(Model model){
        model.addAttribute("worker", new Worker());
        model.addAttribute("departments", departmentService.findAll());
        return "worker/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("worker") @Valid Worker worker,
                         BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());
            return "worker/new";
        }
        workerService.save(worker);
        return "redirect:/workers";
    }

    @GetMapping("{id}/edit")
    public String updateWorker(Model model, @PathVariable("id") int id){
        model.addAttribute("worker", workerService.findById(id));
        model.addAttribute("departments", departmentService.findAll());
        return "worker/edit";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute("worker") Worker worker,
                         @PathVariable("id") int id){
        workerService.update(id, worker);
        return "redirect:/workers/" + id;
    }

    @PatchMapping("/{id}/non")
    public String update(@ModelAttribute("worker") NonDeveloper updateWorker,
                         @PathVariable("id") int id){
        workerService.updateNon(id, new NonDeveloper(workerService.findById(id), updateWorker.getRole()));
        return "redirect:/workers/" + id;
    }

    @PatchMapping("/{id}/dev")
    public String update(@ModelAttribute("worker") Developer updateWorker,
                         @PathVariable("id") int id){
        workerService.updateDev(id, new Developer(workerService.findById(id), updateWorker.getProgrammingLanguage()));
        return "redirect:/workers/" + id;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        workerService.delete(id);
        return "redirect:/workers";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteTask(@PathVariable("id") int id, @RequestParam("taskId") int taskId) {
        workerService.deleteTask(id, taskId);
        return "redirect:/workers/" + id;
    }


    @PostMapping("/{id}/assign")
        public String assign(@PathVariable("id") int id, @RequestParam("taskId") int taskId) {
        workerService.assignTask(id, taskId);
        return "redirect:/workers/" + id;
    }

}
