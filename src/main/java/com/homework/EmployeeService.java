package com.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


public class EmployeeService {



    @Autowired
    EmployeeDAO employeeDAO;

    public List<Employee> getAllEmplFromService() throws ClassNotFoundException {
        return employeeDAO.getAllEmployees();
    }

    public List<Employee> searchFromEmplFromService(int employeeId, String firstName, String lastName, String department) throws ClassNotFoundException {
        return employeeDAO.searchEmployee(employeeId,firstName,lastName,department);
    }

    public boolean createEmployee(EmployeeData data) throws ClassNotFoundException {
        return employeeDAO.createEmployee(data);
    }

    public boolean deleteEmployee(int employee_id) throws ClassNotFoundException {
        return employeeDAO.deleteEmployee(employee_id);
    }




}
