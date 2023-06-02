package net.michal.service;

import net.michal.model.Employee;
import net.michal.util.DatabaseUtil;
import net.michal.util.QueryUtil;

import java.sql.*;

public class DatabaseService {

    DatabaseUtil databaseUtil = new DatabaseUtil();

    public void insertEmployee(Employee employee) throws SQLException {
        try (Connection connection = databaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QueryUtil.insertEmployeeQuery())) {

            preparedStatement.setString(1, employee.getEmployeeName());
            preparedStatement.setString(2, employee.getEmployeeAddress());
            preparedStatement.setDouble(3, employee.getEmployeeSalary());
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Successfully created the record..");
            } else {
                System.out.println("Unable to create the record.");
            }
        }
    }

    public void getAllEmployees() throws SQLException {
        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryUtil.selectAllEmployeeQuery())) {

            while (resultSet.next()) {
                printEmployee(new Employee(
                                resultSet.getInt("EMPLOYEE_ID"),
                                resultSet.getString("EMPLOYEE_NAME"),
                                resultSet.getString("EMPLOYEE_ADDRESS"),
                                resultSet.getDouble("EMPLOYEE_SALARY")
                        )
                );
            }
        }
    }

    private void printEmployee(Employee employee) throws SQLException {
        System.out.println(
                "Employee id: " + employee.getEmployeeId() +
                        "\nEmployee name: " + employee.getEmployeeName() +
                        "\nEmployee salary: " + employee.getEmployeeSalary() +
                        "\nEmployee address: " + employee.getEmployeeAddress() +
                        "\n-------------------------------------"
        );
    }

    public boolean getEmployeeById(int employeeId) throws SQLException {
        boolean isFound = false;

        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QueryUtil.selectEmployeeById(employeeId))) {
            if (resultSet.next()) {
                isFound = true;
                printEmployee(new Employee(
                                resultSet.getInt("EMPLOYEE_ID"),
                                resultSet.getString("EMPLOYEE_NAME"),
                                resultSet.getString("EMPLOYEE_ADDRESS"),
                                resultSet.getDouble("EMPLOYEE_SALARY")
                        )
                );
            } else {
                System.out.println("Record not found for ID: " + employeeId);
            }
        }

        return isFound;
    }

    public void deleteEmployeeById(int employeeId) throws SQLException {
        try (Connection connection = databaseUtil.getConnection();
             Statement statement = connection.createStatement()) {

            int rows = statement.executeUpdate(QueryUtil.deleteEmployeeByID(employeeId));

            if (rows > 0) {
                System.out.println("Successfully deleted the record.");
            } else {
                System.out.println("Unable to delete the record.");
            }
        }
    }

    public void updateEmployee(Employee employee) throws SQLException {
        try(Connection connection = databaseUtil.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement(QueryUtil.updateEmployeeQuery(employee.getEmployeeId()))) {

            preparedStatement.setString(1, employee.getEmployeeName());
            preparedStatement.setString(2, employee.getEmployeeAddress());
            preparedStatement.setDouble(3, employee.getEmployeeSalary());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Successfully updated the record.");
            } else {
                System.out.println("Unable to update the record.");
            }
        }
    }
}