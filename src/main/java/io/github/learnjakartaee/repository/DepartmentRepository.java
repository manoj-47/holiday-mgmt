package io.github.learnjakartaee.repository;

import io.github.learnjakartaee.PersistenceLayer;
import io.github.learnjakartaee.entity.Department;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository {

    DataSource myDataSource;

    public DepartmentRepository(DataSource dataSource) {
        myDataSource = dataSource;
    }

    public List<Department> findAll() {
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
        }
        return listBook;
    }
}
