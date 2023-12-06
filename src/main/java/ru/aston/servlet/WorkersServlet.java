package ru.aston.servlet;

import ru.aston.dao.DepartmentDAOImpl;
import ru.aston.dao.WorkerDAOImpl;
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

    private DepartmentDAOImpl departmentDAOImpl;

    private WorkerDAOImpl workerDAOImpl;

    @Override
    public void init() throws ServletException {
        super.init();
        departmentDAOImpl = new DepartmentDAOImpl();
        workerDAOImpl = new WorkerDAOImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> departments = departmentDAOImpl.findAll();
        List<Worker> workers = workerDAOImpl.findAll();
        req.setAttribute("departments", departments);
        req.setAttribute("workers", workers);
        req.getRequestDispatcher("worker/all-workers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Department department = departmentDAOImpl
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
            workerDAOImpl.save(newDevoleper);
        } else {
            workerNon.setDepartment(department);
            workerNon.setLogin(req.getParameter("login"));
            workerNon.setNameUser(req.getParameter("nameWorker"));
            NonDeveloper newNonDev = new NonDeveloper(
                    workerNon,
                    "None"
            );
            workerDAOImpl.save(newNonDev);
        }

        resp.sendRedirect(req.getContextPath() + "/workers");
    }
}
