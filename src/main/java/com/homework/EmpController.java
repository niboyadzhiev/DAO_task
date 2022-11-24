package com.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public class EmpController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/viewemp")
    public String viewemp (Model m) throws ClassNotFoundException {
        List<Employee> list=employeeService.getAllEmplFromService();
        m.addAttribute("list",list);
        return "viewemp";
    }





}
