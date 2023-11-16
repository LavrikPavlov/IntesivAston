package ru.aston.servlet;

import ru.aston.dao.PersonDAO;
import ru.aston.models.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "PersonServlet", value = "/person")
public class PersonServlet extends HttpServlet {

    private final PersonDAO personDAO;

    public PersonServlet() {
        this.personDAO = new PersonDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Person> persons = personDAO.getAllPerson();
            System.out.println(persons);
            request.setAttribute("persons", persons);

            RequestDispatcher dispatcher = request.getRequestDispatcher("person.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        try {
            Person newPerson = personDAO.createPerson(name, email);
            response.getWriter().println("New Person created: " + newPerson.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error creating new Person");
        }
    }
}

