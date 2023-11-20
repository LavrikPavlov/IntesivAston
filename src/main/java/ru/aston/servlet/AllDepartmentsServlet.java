package ru.aston.servlet;

import ru.aston.models.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/departments")
public class AllDepartmentsServlet extends HttpServlet {


//    @Override
//    public void init() throws ServletException {
//        super.init();
//        this.departamentDAO = new DepartamentDAO();
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("departments", departamentDAO.getAllDepartments());
//        req.getRequestDispatcher("departament/all-departments.jsp").forward(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Department department = departamentDAO.createNewDepartment(req.getParameter("department"));
//        resp.sendRedirect(req.getContextPath() + "/workers");
//    }

}
