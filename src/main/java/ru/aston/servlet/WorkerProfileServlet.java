package ru.aston.servlet;

import ru.aston.dao.DepartamentDAO;
import ru.aston.dao.TaskDAO;
import ru.aston.dao.WorkerDAO;
import ru.aston.models.Department;
import ru.aston.models.Task;
import ru.aston.models.Worker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WorkerProfileServlet", value = "/profile")
public class WorkerProfileServlet extends HttpServlet {
    private WorkerDAO workerDAO;

    private DepartamentDAO departamentDAO;

    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        departamentDAO = new DepartamentDAO();
        workerDAO = new WorkerDAO();
        taskDAO = new TaskDAO();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String workerIdParam = req.getParameter("id");

        if (workerIdParam != null) {
            try {
                int workerId = Integer.parseInt(workerIdParam);
                Worker worker = workerDAO.getWorkerById(workerId);
                List<Department> departments = departamentDAO.getAllDepartments();
                List<Task> tasks = taskDAO.getAllTask();


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
                Worker updatedWorker = workerDAO.getWorkerById(workerId);

                switch (action) {
                    case "assignTask":
                        workerDAO.assignTaskToWorker(workerId, taskId);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;

                    case "removeTask":
                        workerDAO.removeTaskFromWorker(workerId, taskId);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;

                    case "changeDepart":
                        Department department = workerDAO.
                                getDepartmentById(Integer.parseInt(req.getParameter("department")));
                        updatedWorker.setDepartment(department);
                        workerDAO.updateWorker(updatedWorker);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;

                    case "updateWorker":
                        String newName = req.getParameter("name");
                        updatedWorker.setNameWorker(newName);
                        workerDAO.updateWorker(updatedWorker);
                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    case "deleteWorker":
                        workerDAO.deleteWorker(workerId);
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
