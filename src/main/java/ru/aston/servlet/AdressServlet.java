package ru.aston.servlet;

import ru.aston.dao.AdressDAO;
import ru.aston.dao.PersonDAO;
import ru.aston.models.Adress;
import ru.aston.models.Person;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/address")
public class AdressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdressDAO adressDAO;
    private PersonDAO personDAO;

    @Override
    public void init() {
        this.adressDAO = new AdressDAO();
        this.personDAO = new PersonDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Adress> addressList = adressDAO.getAllAdress();
            request.setAttribute("addressList", addressList);
            request.getRequestDispatcher("adress.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().write("Error retrieving addresses: " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String addressFull = request.getParameter("addressFull");
        String personIdString = request.getParameter("personId");

        try {
            int personId = (personIdString != null && !personIdString.isEmpty()) ? Integer.parseInt(personIdString) : 0;
            Person person = personDAO.getPersonById(personId);

            Adress newAddress = adressDAO.createNewAdress(addressFull, person);
            response.getWriter().write("New Address created: " + newAddress.getAddressFull());

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.getWriter().write("Error creating new Address: " + e.getMessage());
        }
    }
}
