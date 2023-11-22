package ru.aston.servlet;

import ru.aston.dao.DepartmentDAO;
import ru.aston.dao.WorkerDAO;
import ru.aston.models.Department;
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


@WebServlet("/workers")
public class WorkersServlet extends HttpServlet {

    private DepartmentDAO departmentDAO;

    private WorkerDAO workerDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        departmentDAO = new DepartmentDAO();
        workerDAO = new WorkerDAO();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentDAO.findAll();
        List<Worker> workers = workerDAO.findAll();
        req.setAttribute("departments", departments);
        req.setAttribute("workers", workers);
        req.getRequestDispatcher("worker/all-workers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Department department = departmentDAO
                .findById(Integer.parseInt(req.getParameter("department")));
        Worker workerNon = new Developer();
        Worker workerDev = new NonDeveloper();

        if (department.getId() == 1) {
            workerDev.setDepartment(department);
            workerDev.setLogin(req.getParameter("login"));
            workerDev.setNameUser(req.getParameter("nameWorker"));
            Developer newDevoleper = new Developer(
                    workerDev,
                    "None"
            );
            workerDAO.save(newDevoleper);
        } else {
            workerNon.setDepartment(department);
            workerNon.setLogin(req.getParameter("login"));
            workerNon.setNameUser(req.getParameter("nameWorker"));
            NonDeveloper newNonDev = new NonDeveloper(
                    workerNon,
                    "None"
            );
            workerDAO.save(newNonDev);
        }

        resp.sendRedirect(req.getContextPath() + "/workers");
    }
}
