package io.github.learnjakartaee.repository;

import io.github.learnjakartaee.entity.Department;
import jakarta.servlet.ServletException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    DataSource myDataSource;

    public DepartmentRepository(DataSource dataSource) {
        myDataSource = dataSource;
    }

    public List<Department> findAll() throws ServletException {
        List<Department> listBook = new ArrayList<>();
        try (Connection con = myDataSource.getConnection()) {

            String sql = "SELECT * FROM department";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("department_name");
                Department d = new Department();
                d.setId(id);
                d.setDepartmentName(title);
                listBook.add(d);
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Unable to get details in db");
        }
        return listBook;
    }

    public boolean save(Department department) throws ServletException{
        try (Connection con = myDataSource.getConnection()) {

            String sql = "INSERT INTO department (title, author, price) VALUES (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, department.getId());
            statement.setString(2, department.getDepartmentName());

            boolean rowInserted = statement.executeUpdate() > 0;
            statement.close();
            return rowInserted;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Unable to save details in db");
        }
    }

    public boolean deleteBook(Department department) throws ServletException {
        try (Connection con = myDataSource.getConnection()) {

            String sql = "DELETE FROM department where book_id = ?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, department.getId());

            boolean rowDeleted = statement.executeUpdate() > 0;
            statement.close();
            return rowDeleted;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Unable to delete details in db");
        }
    }

    public boolean  updateDepartment(Department department) throws ServletException {
        try (Connection con = myDataSource.getConnection()) {

            String sql = "UPDATE department SET department_name = ? ";
            sql += " WHERE id = ?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, department.getDepartmentName());
            statement.setLong(2, department.getId());

            boolean rowUpdated = statement.executeUpdate() > 0;
            statement.close();
            return rowUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Unable to delete details in db");
        }
    }

    public Department get(Long id) throws ServletException {
        try (Connection con = myDataSource.getConnection()) {

            Department department = null;
            String sql = "SELECT * FROM department WHERE book_id = ?";

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String departmentName = resultSet.getString("department_name");

                department = new Department();
                department.setId(id);
                department.setDepartmentName(departmentName);
            }

            resultSet.close();
            statement.close();

            return department;
        }catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Unable to get details in db");
        }
    }

}
