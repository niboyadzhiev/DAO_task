package com.homework;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class EmployeeDAO {

    @Autowired
    DBConnector dbConnector;




    public List<Employee> getAllEmployees () throws ClassNotFoundException {
        List<Employee> resultList = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dbConnector.getConnection();


            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT e.employee_id employeeID, e.first_name firstName, e.last_name lastName, d.department_name department " +
                    "FROM employees e LEFT JOIN departments d ON e.department_id = d.department_id");
            while (resultSet.next()) {
                resultList.add(new Employee(resultSet.getInt("employeeID"),resultSet.getString("firstName"),
                        resultSet.getString("lastName"),resultSet.getString("department"), resultSet.getBigDecimal("salary")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        // for testing purposes
        for (Employee e : resultList) {
            System.out.println(e.getEmployeeId() + "; " + e.getFirstName() + "; " + e.getLastName() + "; " + e.getDepartment());
        }

        return resultList;
    }

    public List<Employee>  searchEmployee(int employeeID, String firstName, String lastName, String deptName) throws ClassNotFoundException {
        Connection connection = null;
        List<Employee> resultList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        try {
            connection = dbConnector.getConnection();

            String queryText = "SELECT e.employee_id employeeID, e.first_name firstName, e.last_name lastName, d.department_name department " +
                    "FROM employees e LEFT JOIN departments d ON e.department_id = d.department_id " +
                    "WHERE (e.employee_id = ? OR ? = 0) AND (e.first_name = ? OR ? = '') AND " +
                    "(e.last_name = ? OR ? = '') AND (d.department_name = ? OR ? = '');";

            statement = connection.prepareStatement(queryText,ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1,employeeID);
            statement.setInt(2,employeeID);
            statement.setString(3,firstName);
            statement.setString(4,firstName);
            statement.setString(5,lastName);
            statement.setString(6,lastName);
            statement.setString(7,deptName);
            statement.setString(8,deptName);
            System.out.println(statement);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                resultList.add(new Employee(resultSet.getInt("employeeID"),resultSet.getString("firstName"),
                        resultSet.getString("lastName"),resultSet.getString("department"), resultSet.getBigDecimal("salary")));
            }

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                if (i > 1) System.out.print("; ");
                System.out.print(resultSet.getMetaData().getColumnLabel(i));
                if (i == resultSet.getMetaData().getColumnCount()) {
                    System.out.println();
                }
            }

            resultSet.beforeFirst();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    if (i > 1) System.out.print("; ");
                    System.out.print(resultSet.getString(i));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Code: " + e.getErrorCode());
        } finally {
            try {
//                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Code: " + e.getErrorCode());
            }

        }
        return resultList;

    }

    public List<Employee>  listAllEmployees() throws ClassNotFoundException {
        Connection connection = null;
        List<Employee> resultList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;


        try {
            connection = dbConnector.getConnection();

            String queryText = "SELECT e.employee_id employeeID, e.first_name firstName, e.last_name lastName, d.department_name department, e.salary salary " +
                    "FROM employees e LEFT JOIN departments d ON e.department_id = d.department_id;";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(queryText);

            while (resultSet.next()) {
                resultList.add(new Employee(resultSet.getInt("employeeID"),resultSet.getString("firstName"),
                        resultSet.getString("lastName"),resultSet.getString("department"), resultSet.getBigDecimal("salary")));
            }

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                if (i > 1) System.out.print("; ");
                System.out.print(resultSet.getMetaData().getColumnLabel(i));
                if (i == resultSet.getMetaData().getColumnCount()) {
                    System.out.println();
                }
            }

            resultSet.beforeFirst();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    if (i > 1) System.out.print("; ");
                    System.out.print(resultSet.getString(i));
                }
                System.out.println();
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Code: " + e.getErrorCode());
        } finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Code: " + e.getErrorCode());
            }

        }
        return resultList;

    }

    public boolean deleteEmployee(int employee_id) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isDeleted = false;

        try {
            connection = dbConnector.getConnection();
            String deleteSQL = "DELETE FROM employees e WHERE e.employee_id = ?;";
            statement = connection.prepareStatement(deleteSQL);
            statement.setInt(1, employee_id);

            if (statement.executeUpdate() > 0) isDeleted = true;


        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Code: " + e.getErrorCode());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Code: " + e.getErrorCode());
            }

        }
        return isDeleted;


    }

    public boolean createEmployee(EmployeeData data) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isInserted = false;

        try {
            connection = dbConnector.getConnection();
            String insertSQL = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?);";
            statement = connection.prepareStatement(insertSQL);
            statement.setInt(1, data.getEmployee_id());
            statement.setString(2, data.getFirst_name());
            statement.setString(3, data.getLast_name());
            statement.setString(4, data.getEmail());
            statement.setString(5, data.getPhone_number());
            statement.setTimestamp(6, new Timestamp(data.getHire_date().getTime()));
            statement.setString(7, data.getJob_id());
            statement.setBigDecimal(8, data.getSalary());
            statement.setBigDecimal(9, data.getCommission_pct());
            statement.setInt(10, data.getManager_id());
            statement.setInt(11, data.getDepartment_id());
            if (statement.executeUpdate() > 0) isInserted = true;


        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Vendor Code: " + e.getErrorCode());
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception: " + e.getMessage());
                System.out.println("SQL State: " + e.getSQLState());
                System.out.println("Vendor Code: " + e.getErrorCode());
            }

        }

        return isInserted;
    }



}
