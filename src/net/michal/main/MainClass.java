package net.michal.main;

import net.michal.model.Employee;
import net.michal.service.DatabaseService;

import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        DatabaseService databaseService = new DatabaseService();

        try (Scanner input = new Scanner(System.in)) {

            boolean isRunning = true;
            while (isRunning) {
                System.out.println("Enter choice:");
                System.out.println("1 - Insert");
                System.out.println("2 - Select all");
                System.out.println("3 - Select employee by ID");
                System.out.println("4 - Delete employee");
                System.out.println("5 - Update employee");
                System.out.println("6 - Exit");

                int choice = Integer.parseInt(input.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Enter: | name | address | salary |");
                        databaseService.insertEmployee(
                                new Employee(
                                        input.nextLine(),
                                        input.nextLine(),
                                        Double.parseDouble(input.nextLine())
                                )
                        );
                        break;
                    case 2:
                        databaseService.getAllEmployees();
                        break;
                    case 3:
                        System.out.println("Enter ID of an employee:");
                        databaseService.getEmployeeById(Integer.parseInt(input.nextLine()));
                        break;
                    case 4:
                        System.out.println("Ented ID of an employee:");
                        databaseService.deleteEmployeeById(Integer.parseInt(input.nextLine()));
                        System.out.println(choice);
                        break;
                    case 5:
                        System.out.println("Enter ID of an employee:");
                        int updateId = Integer.parseInt(input.nextLine());
                        boolean isFound = databaseService.getEmployeeById(updateId);

                        if (isFound) {
                            System.out.println("Enter: | name | address | salary |");
                            Employee employee = new Employee(
                                    updateId,
                                    input.nextLine(),
                                    input.nextLine(),
                                    Double.parseDouble(input.nextLine()
                                    )
                            );
                            databaseService.updateEmployee(employee);
                        } else {

                        }
                        break;
                    case 6:
                        System.out.println("Successfully exited.");
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Encountered an error!: " + e);
        }
    }
}