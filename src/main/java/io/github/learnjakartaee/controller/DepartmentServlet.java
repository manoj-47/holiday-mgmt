package io.github.learnjakartaee.controller;

import io.github.learnjakartaee.entity.Department;
import io.github.learnjakartaee.repository.DepartmentRepository;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/department")
public class DepartmentServlet extends HttpServlet {

    private static final long serialVersionUID = 3892187441638408459L;

    @Resource(name = "myDataSource")
    private DataSource dataSource;

    private DepartmentRepository departmentRepository;

    public void init() {
        departmentRepository = new DepartmentRepository(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();


        try {
            switch (action) {
                case "/new":
                    break;
                case "/insert":
                    break;
                case "/delete":
                    break;
                case "/edit":
                    break;
                case "/update":
                    break;
                default:
                    listDepartments(request, response);
                    break;
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listDepartments(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Department> listBook = departmentRepository.findAll();
        request.setAttribute("listDepartment", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/main/department.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        if (name == null || name.isEmpty()) {
            request.setAttribute("greeting", "I'm sorry, I didn't catch your name...");
        } else {
            request.setAttribute("greeting", String.format("Hello %s, how are you today?", name));
        }
        doGet(request, response);
    }
}
