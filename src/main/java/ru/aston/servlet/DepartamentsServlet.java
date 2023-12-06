package ru.aston.servlet;

import ru.aston.dao.DepartmentDAOImpl;
import ru.aston.models.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/department")
public class DepartamentsServlet extends HttpServlet {

    private DepartmentDAOImpl departamentDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        departamentDAO = new DepartmentDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String departIdParam = req.getParameter("id");

        if (departIdParam != null) {
            try {
                Department department = departamentDAO.findById(Integer.parseInt(departIdParam));
                req.setAttribute("Department", department);

                req.getRequestDispatcher("/departament/department.jsp").forward(req, resp);
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
        String departIdParam = req.getParameter("id");
        String action = req.getParameter("action");
        if (departIdParam != null) {
            Department department = departamentDAO.findById(Integer.parseInt(departIdParam));
            switch (action) {
                case "updateDepart":
                    String newNameDepart = req.getParameter("nameDepart");
                    department.setNameDepart(newNameDepart);
                    departamentDAO.update(department);
                    break;
                case "deleteDepart":
                    departamentDAO.delete(department);
                    resp.sendRedirect("/departments");
                    return;
            }
            resp.sendRedirect("/department?id=" + departIdParam);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный идентификатор департамента");
        }
    }
}
