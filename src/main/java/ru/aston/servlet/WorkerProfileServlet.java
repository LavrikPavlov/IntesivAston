package ru.aston.servlet;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WorkerProfileServlet", value = "/profile")
public class WorkerProfileServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String workerIdParam = req.getParameter("id");

        if (workerIdParam != null) {
            try {

//                req.setAttribute("worker", worker);
//                req.setAttribute("allTasks", tasks);
//                req.setAttribute("allDepartments", departments);

                req.getRequestDispatcher("/worker/worker-profile.jsp").forward(req, resp);
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
        String workerIdParam = req.getParameter("id");
        String action = req.getParameter("action");

        try {
            if (workerIdParam != null) {
                int workerId = Integer.parseInt(workerIdParam);
                int taskId = Integer.parseInt(req.getParameter("taskId"));

                switch (action) {
                    case "assignTask":

                        break;

                    case "removeTask":

                        break;

                    case "changeDepart":

                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;

                    case "updateWorker":

                        resp.setStatus(HttpServletResponse.SC_OK);
                        break;
                    case "deleteWorker":

                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.sendRedirect("/workers");
                        return;
                }
                resp.sendRedirect("/profile?id=" + workerId);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный идентификатор работника");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Неверный формат числа");
        }
    }
}
