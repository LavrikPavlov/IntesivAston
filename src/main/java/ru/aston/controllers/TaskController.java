package ru.aston.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aston.models.Task;
import ru.aston.service.TaskService;

import javax.validation.Valid;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String show(Model model){
        model.addAttribute("tasks", taskService.findAll());
        return "task/all-task";
    }

    @GetMapping("{id}")
    public String showTask(Model model, @PathVariable("id") int id){
        model.addAttribute("task", taskService.findOne(id));
        return "task/task-one";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("task", taskService.findOne(id));
        return "task/edit";
    }

    @GetMapping("/new")
    public String newTask(Model model){
        model.addAttribute("task", new Task());
        return "task/new";
    }

    @PatchMapping("{id}")
    public String editTask(@ModelAttribute("task") @Valid Task updateTask,
                           @PathVariable("id") int id){
        taskService.update(id, updateTask);
        return "redirect:/task/" + id;
    }

    @PostMapping("/new")
    public String newTask(@ModelAttribute("task") @Valid Task newTask,
                          BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return "task/new";
        }

        taskService.save(newTask);
        return "redirect:/task";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id){
        taskService.delete(id);
        return "redirect:/task";
    }

}
