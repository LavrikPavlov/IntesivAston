package ru.aston.servlet;


import ru.aston.dao.DepartmentDAOImpl;
import ru.aston.dao.TaskDAOImpl;
import ru.aston.dao.WorkerDAOImpl;
import ru.aston.models.Department;
import ru.aston.models.Task;
import ru.aston.models.workers.Developer;
import ru.aston.models.workers.NonDeveloper;
import ru.aston.models.workers.Worker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WorkerProfileServlet", value = "/profile")
public class WorkerProfileServlet extends HttpServlet {

    private WorkerDAOImpl workerDAOImpl;
    private DepartmentDAOImpl departmentDAOImpl;

    private TaskDAOImpl taskDAOImpl;


    @Override
    public void init() throws ServletException {
        super.init();
        workerDAOImpl = new WorkerDAOImpl();
        departmentDAOImpl = new DepartmentDAOImpl();
        taskDAOImpl = new TaskDAOImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String workerIdParam = req.getParameter("id");
        if (workerIdParam != null) {
            try {
                List<Task> tasks = taskDAOImpl.findAll();
                List<Department> departments = departmentDAOImpl.findAll();
                Worker worker = workerDAOImpl.findById(Integer.parseInt(workerIdParam));


                req.setAttribute("worker", worker);
                req.setAttribute("allTasks", tasks);
                req.setAttribute("allDepartments", departments);

                req.getRequestDispatcher("/worker/worker-profile.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendRedirect("/workers");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String workerIdParam = req.getParameter("id");
        String action = req.getParameter("action");

        try {
            if (workerIdParam != null) {
                int workerId = Integer.parseInt(workerIdParam);
                int taskId = Integer.parseInt(req.getParameter("taskId"));

                Worker worker = workerDAOImpl.findById(Integer.parseInt(workerIdParam));
                Department department = departmentDAOImpl
                        .findById(Integer.parseInt(req.getParameter("department")));

                switch (action) {
                    case "assignTask":
                        workerDAOImpl.assignTask(workerId,taskId);
                        break;

                    case "removeTask":
                        workerDAOImpl.deleteTask(workerId,taskId);
                        break;

                    case "changeDepart":
                        worker.setDepartment(department);
                        workerDAOImpl.chacngeWorkerType(worker, req, resp);
                        workerDAOImpl.update(worker);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;

                    case "updateWorker":
                        worker.setNameWorker(req.getParameter("name"));

                        if(worker.getWorkerType().equals("Developer")) {
                            ((Developer) worker).setProgrammingLanguage(req.getParameter("programmingLanguage"));
                        } else {
                            ((NonDeveloper) worker).setRole(req.getParameter("role"));
                        }

                        workerDAOImpl.update(worker);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;


                    case "deleteWorker":
                        workerDAOImpl.delete(worker);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.sendRedirect("/workers");
                        return;
                }
                resp.sendRedirect("/profile?id=" + workerId);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный идентификатор работника");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат числа");
        }
    }
}
