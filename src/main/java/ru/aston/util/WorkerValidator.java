//package ru.aston.util;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import ru.aston.models.workers.Worker;
//import ru.aston.service.WorkerService;
//
//import javax.validation.Validator;
//
//@Component
//public class WorkerValidator implements Validator {
//
//    private final WorkerService workerService;
//
//    @Autowired
//    public WorkerValidator(WorkerService workerService) {
//        this.workerService = workerService;
//    }
//
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return Worker.class.equals(aClass);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        Worker worker = (Worker) o;
//
//        if (workerService.getPersonByLogin(worker.getLogin()).isPresent())
//            errors.rejectValue("login", "Рабочий с таким логином уже существует");
//    }
//}
//
