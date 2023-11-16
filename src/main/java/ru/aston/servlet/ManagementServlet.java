package ru.aston.servlet;

import ru.aston.dao.AdressDAO;
import ru.aston.dao.ManagementDAO;
import ru.aston.models.Adress;
import ru.aston.models.Management;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/management")
public class ManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ManagementDAO managementDAO;
    private AdressDAO adressDAO;

    @Override
    public void init() {
        this.managementDAO = new ManagementDAO();
        this.adressDAO = new AdressDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Management> managerList = managementDAO.getAllManager();
            request.setAttribute("managerList", managerList);
            request.getRequestDispatcher("managment.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameCompany = request.getParameter("nameCompany");
        String addressFull = request.getParameter("addressFull");

        try {
            Adress address = adressDAO.createNewAdress(addressFull, null); // Здесь можно передать объект Person, если он доступен

            Management newManager = managementDAO.createManagemnt(nameCompany, address);
            response.getWriter().println("New Manager created: " + newManager.getNameCompany());
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error creating new Manager");
        }
    }
}