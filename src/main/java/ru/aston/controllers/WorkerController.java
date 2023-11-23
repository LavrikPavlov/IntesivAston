package ru.aston.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.aston.models.Task;
import ru.aston.models.abstractModel.Worker;
import ru.aston.service.TaskService;
import ru.aston.service.WorkerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/workers")
public class WorkerController {

    private final TaskService taskService;

    private final WorkerService workerService;


    @Autowired
    public WorkerController(TaskService taskService, WorkerService workerService) {
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
        return "worker/profile";
    }

    @GetMapping("/new")
    public String newWorker(){
        return "worker/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("worker") @Valid Worker worker){
        workerService.save(worker);
        return "redirect:/workers";
    }

    @GetMapping("{id}/edit")
    public String updateWorker(){
        return "worker/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("worker") @Valid Worker updateWorker,
                         @PathVariable("id") int id){
        workerService.update(id, updateWorker);
        return "redirect:/workers/" + id;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        workerService.delete(id);
        return "redirect:/workers";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable("id") int id, @ModelAttribute("worker") Task task ){
        workerService.deleteTask(id, task.getId());
        return "redirect:/workers/" + id;
    }

    @PostMapping("/{id}/assign")
        public String assign(@PathVariable("id") int id, @ModelAttribute("worker") Task task) {
        workerService.assignTask(id, task.getId());
        return "redirect:/workers/" + id;
    }

}
