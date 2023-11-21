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

//
//        req.setAttribute("workers", workerDAO.getAllWorkers());
        req.getRequestDispatcher("worker/all-workers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Department department = departmentDAO
                .findById(Integer.parseInt(req.getParameter("department")));

        if (department.getId() == 1) {
            Developer newDevoleper = new Developer(
                    req.getParameter("login"),
                    req.getParameter("nameWorker"),
                    department,
                    ""
            );
            workerDAO.save(newDevoleper);
        } else {
            NonDeveloper newNonDev = new NonDeveloper(
                    req.getParameter("login"),
                    req.getParameter("nameWorker"),
                    department,
                    ""
            );
            workerDAO.save(newNonDev);
        }

        resp.sendRedirect(req.getContextPath() + "/workers");
    }
}
