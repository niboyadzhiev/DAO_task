package com.homework;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeDataCollector {

    public  EmployeeData collectData() throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int employee_id = 0;
        String first_name = null;
        String last_name = null;
        String email = null;
        String phone_number = null;
        Timestamp hire_date = null;
        String job_id = null;
        BigDecimal salary = null;
        BigDecimal commission_pct = null;
        int manager_id = 0;
        int department_id = 0;

        System.out.println("You ara about to create new employee entry. Please provide all data:");


        while (employee_id == 0) {
            try {
                System.out.print("Employee ID: ");
                employee_id = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                sc.next();
            }
        }


        while (first_name == null) {
            try {
                System.out.print("First name: ");
                first_name = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                sc.next();
            }
        }

        while (last_name == null) {
            try {
                System.out.print("Last name: ");
                last_name = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                sc.next();
            }
        }

        while (email == null) {
            try {
                System.out.print("Email: ");
                email = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                sc.next();
            }
        }

        while (phone_number == null) {
            try {
                System.out.print("Phone number: ");
                phone_number = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                sc.next();
            }
        }

        while (hire_date == null) {
            try {
                System.out.print("Date /yyyy-mm-dd/: ");
                String dataInput = sc.next();
                String[] dataArray = dataInput.split("-");
                for (String c : dataArray) {
                    if (c.charAt(0) == '0') {
                        c = c.substring(1);
                    }
                }

                hire_date = new Timestamp(Integer.parseInt(dataArray[0]) - 1900, Integer.parseInt(dataArray[1]) - 1, Integer.parseInt(dataArray[2]), 0, 0, 0, 0);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid input");
            }
        }

        while (job_id == null) {
            try {
                System.out.print("Job ID: ");
                job_id = sc.next();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                sc.next();
            }
        }

        while (salary == null) {
            try {
                System.out.print("Salary: ");
                salary = new BigDecimal(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                sc.next();
            }
        }

        while (commission_pct == null) {
            try {
                System.out.print("Commission percentage: ");
                commission_pct = new BigDecimal(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }

        while (manager_id == 0) {
            try {
                System.out.print("Manager ID: ");
                manager_id = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                sc.next();
            }
        }

        while (department_id == 0) {
            try {
                System.out.print("Department ID: ");
                department_id = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                sc.next();
            }
        }

        return new EmployeeData(employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, commission_pct, manager_id, department_id);




    }
}
