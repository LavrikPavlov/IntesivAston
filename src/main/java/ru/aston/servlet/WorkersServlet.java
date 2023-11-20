package ru.aston.servlet;

import ru.aston.dao.DepartamentDAO;
import ru.aston.dao.WorkerDAO;
import ru.aston.models.Department;
import ru.aston.models.Worker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/workers")
public class WorkersServlet extends HttpServlet {

    private WorkerDAO workerDAO;
    private DepartamentDAO departamentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        this.departamentDAO = new DepartamentDAO();
        this.workerDAO = new WorkerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departamentDAO.getAllDepartments();
        req.setAttribute("departments", departments);

        req.setAttribute("workers", workerDAO.getAllWorkers());
        req.getRequestDispatcher("worker/all-workers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Department department = workerDAO.getDepartmentById(Integer.parseInt(req.getParameter("department")));

        Worker newWorker = workerDAO.createNewWorker(req.getParameter("login"), req.getParameter("nameWorker"), department);

        resp.sendRedirect(req.getContextPath() + "/workers");
    }
}
