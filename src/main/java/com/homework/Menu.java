package com.homework;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    int numberOfAttempts;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeDataCollector employeeDataCollector;

    public Menu(int numberOfAttempts) {
        this.numberOfAttempts = numberOfAttempts;
    }

    public void showMenu() throws ClassNotFoundException {
        int origNumOfAttempts = numberOfAttempts;
        Scanner sc = new Scanner(System.in);
        String options = "search/create/delete/exit";
        List<String> optionsList = new ArrayList<>(List.of(options.split("/")));
        String input = null;

        outer_loop:
        while (numberOfAttempts > 0) {
            System.out.print("Choose an option by typing one of the following - " + options + ": ");
            input = sc.nextLine();
            if (!optionsList.contains(input)) {
                System.out.println("Invalid input. Remaining attempts: " + --numberOfAttempts);
            }

            switch (input) {
                case "search":
                    System.out.println("You can search by combination of employee id, first name, last name and department name.");
                    System.out.print("Employee ID: ");
                    String employeeIDString = sc.nextLine();
                    if (employeeIDString.equals("")) employeeIDString = "0";
                    Integer employeeIDInteger = Integer.parseInt(employeeIDString);
                    System.out.print("First name: ");
                    String firstName = sc.nextLine();
                    System.out.print("Last name: ");
                    String lastName = sc.nextLine();
                    System.out.print("Department name: ");
                    String deptName = sc.nextLine();
                    employeeService.searchFromEmplFromService(employeeIDInteger, firstName, lastName, deptName);
                    numberOfAttempts = origNumOfAttempts;
                    break;
                case "create":
                    EmployeeData employeeData = employeeDataCollector.collectData();
                    boolean isCreted =  employeeService.createEmployee(employeeData);
                    if (isCreted) {
                        System.out.println("Successful import");
                    } else {
                        System.out.println("Unsuccessful import");
                    };
                    break;
                case "delete":
                    int idInput = 0;
                    while (idInput == 0) {
                        try {
                            System.out.print("Employee ID for deletion: ");
                            idInput = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input");
                            sc.next();
                        }
                    }
                    if (employeeService.deleteEmployee(idInput)) {
                        System.out.println("Successful deletion of employee " + idInput);
                    } else {
                        System.out.println("Unsuccessful deletion");
                    }
                    break;
                case "exit":
                    break outer_loop;

            }


        }

        sc.close();


    }
}
