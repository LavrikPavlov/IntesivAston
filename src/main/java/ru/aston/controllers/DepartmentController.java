package ru.aston.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.aston.models.Department;
import ru.aston.service.DepartmentService;

import javax.validation.Valid;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    public String show(Model model) {
        model.addAttribute("departments", departmentService.findAll());
        return "department/all-department";
    }

    @GetMapping("{id}")
    public String showDepart(Model model, @PathVariable("id") int id) {
        model.addAttribute("department", departmentService.findById(id));
        return "department/depart";
    }

    @GetMapping("{id}/edit")
    public String editDepart(Model model, @PathVariable("id") int id) {
        model.addAttribute("department", departmentService.findById(id));
        return "department/edit";
    }

    @GetMapping("/new")
    public String newDepart(Model model) {
        model.addAttribute("department", new Department());
        return "department/new";
    }

    @PatchMapping("{id}")
    public String editDepart(@ModelAttribute("department") @Valid Department updatedDepartment,
                             @PathVariable("id") int id) {
        departmentService.update(id, updatedDepartment);
        return "redirect:/department/" + id;
    }

    @PostMapping("/new")
    public String newDepart(@ModelAttribute("department") @Valid Department newDepartment,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "department/new";
        }

        departmentService.save(newDepartment);
        return "redirect:/department";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        departmentService.delete(id);
        return "redirect:/department";
    }

}
