package ru.aston.servlet;

import ru.aston.dao.TaskDAO;
import ru.aston.models.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tasks")
public class AllTaskServlet extends HttpServlet {

    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDAO = new TaskDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("departments", taskDAO.findAll());
        req.getRequestDispatcher("task/all-task.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Task newTask = new Task();
        newTask.setNameTask(req.getParameter("NameTask"));
        newTask.setTextTask(req.getParameter("TaskText"));
        taskDAO.save(newTask);

        resp.sendRedirect(req.getContextPath() + "/tasks");
    }
}
