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
import java.sql.SQLException;
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
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertDept(request, response);
                    break;
                case "/delete":
                    deleteDept(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateDept(request, response);
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

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/main/departmentForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Department depar = departmentRepository.get(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/main/department.jsp");
        request.setAttribute("department", depar);
        dispatcher.forward(request, response);

    }

    private void insertDept(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dName = request.getParameter("department_name");

        Department newBook = new Department();
        newBook.setDepartmentName(dName);
        departmentRepository.save(newBook);
        response.sendRedirect("list");
    }

    private void updateDept(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String dName = request.getParameter("department_name");

        Department newBook = new Department();
        newBook.setId(id);
        newBook.setDepartmentName(dName);
        departmentRepository.updateDepartment(newBook);
        response.sendRedirect("list");
    }

    private void deleteDept(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        Department newBook = new Department();
        newBook.setId(id);
        departmentRepository.deleteBook(newBook);
        response.sendRedirect("list");

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
