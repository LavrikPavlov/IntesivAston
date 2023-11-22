package ru.aston.servlet;

import ru.aston.dao.DepartmentDAO;
import ru.aston.models.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/departments")
public class AllDepartmentsServlet extends HttpServlet {

    private DepartmentDAO departamentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        departamentDAO = new DepartmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("departments", departamentDAO.findAll());
        req.getRequestDispatcher("departament/all-departments.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = new Department();
        department.setNameDepart(req.getParameter("department"));
        departamentDAO.save(department);
        resp.sendRedirect(req.getContextPath() + "/workers");
    }

}
