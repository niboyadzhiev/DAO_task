package com.homework;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeData {
    private int employee_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private Timestamp hire_date;
    private String job_id;
    private BigDecimal salary;
    private BigDecimal commission_pct;
    private int manager_id;
    private int department_id;


    public EmployeeData(int employee_id, String first_name, String last_name, String email, String phone_number, Timestamp hire_date, String job_id, BigDecimal salary,
                        BigDecimal commission_pct, int manager_id, int department_id) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.hire_date = hire_date;
        this.job_id = job_id;
        this.salary = salary;
        this.commission_pct = commission_pct;
        this.manager_id = manager_id;
        this.department_id = department_id;
    }


    public int getEmployee_id() {
        return employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Timestamp getHire_date() {
        return hire_date;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public BigDecimal getCommission_pct() {
        return commission_pct;
    }

    public int getManager_id() {
        return manager_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public String getEmail() {
        return email;
    }

    public String getJob_id() {
        return job_id;
    }



}
