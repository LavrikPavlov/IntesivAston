package ru.aston.servlet;

import ru.aston.dao.TaskDAO;
import ru.aston.models.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {

    private TaskDAO taskDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        taskDAO = new TaskDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String taskIdParam = req.getParameter("id");

        if (taskIdParam != null) {
            try {
                Task task = taskDAO.findById(Integer.parseInt(taskIdParam));
                req.setAttribute("Task", task);

                req.getRequestDispatcher("/task/taskOne.jsp").forward(req, resp);
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.sendRedirect("/task");
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String taskIdParam = req.getParameter("id");
        String action = req.getParameter("action");
        if (taskIdParam != null) {
            Task task = taskDAO.findById(Integer.parseInt(taskIdParam));
            switch (action) {
                case "updateTask":
                    Task newTask = new Task();
                    newTask.setNameTask(req.getParameter("textTask"));
                    newTask.setTextTask(req.getParameter("taskText"));
                    taskDAO.save(newTask);
                    break;
                case "deleteTask":
                    taskDAO.delete(task);
                    resp.sendRedirect("/tasks");
                    return;
            }
            resp.sendRedirect("/task?id=" + taskIdParam);
            resp.setStatus(HttpServletResponse.SC_OK);

        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный идентификатор департамента");
        }
    }

}
