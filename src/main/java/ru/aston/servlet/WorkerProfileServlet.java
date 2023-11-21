package ru.aston.servlet;


import ru.aston.dao.DepartmentDAO;
import ru.aston.dao.TaskDAO;
import ru.aston.dao.WorkerDAO;
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

    private WorkerDAO workerDAO;
    private DepartmentDAO departmentDAO;

    private TaskDAO taskDAO;


    @Override
    public void init() throws ServletException {
        super.init();
        workerDAO = new WorkerDAO();
        departmentDAO = new DepartmentDAO();
        taskDAO = new TaskDAO();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String workerIdParam = req.getParameter("id");
        if (workerIdParam != null) {
            try {
                List<Task> tasks = taskDAO.findAll();
                List<Department> departments = departmentDAO.findAll();
                Worker worker = workerDAO.findById(Integer.parseInt(workerIdParam));


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

                Worker worker = workerDAO.findById(Integer.parseInt(workerIdParam));

                switch (action) {
                    case "assignTask":
                        workerDAO.assignTask(workerId,taskId);

                    case "removeTask":
                        workerDAO.deleteTask(workerId,taskId);
                        break;

                    case "changeDepart":

                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;

                    case "updateWorker":

                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;

                    case "updateAny":

                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;

                    case "deleteWorker":

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
