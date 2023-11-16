package ru.aston.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdressServlet", value = "/adress")
public class AdressServlet extends HttpServlet{

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.getWriter().println("Adress 1");
        }
}
