package ru.aston.servlet;

import ru.aston.dao.TaskDAOImpl;
import ru.aston.models.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tasks")
public class AllTaskServlet extends HttpServlet {

    private TaskDAOImpl taskDAOImpl;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDAOImpl = new TaskDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("departments", taskDAOImpl.findAll());
        req.getRequestDispatcher("task/all-task.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task newTask = new Task();
        newTask.setNameTask(req.getParameter("NameTask"));
        newTask.setTextTask(req.getParameter("TaskText"));
        taskDAOImpl.save(newTask);

        resp.sendRedirect(req.getContextPath() + "/tasks");
    }
}
