package net.michal.util;

public class QueryUtil {

    public static String insertEmployeeQuery() {
        return "INSERT INTO EMPLOYEE_INFO (EMPLOYEE_NAME, EMPLOYEE_ADDRESS, EMPLOYEE_SALARY) VALUES (?, ?, ?)";
    }

    public static String selectAllEmployeeQuery() {
        return "SELECT * FROM EMPLOYEE_INFO";
    }

    public static String selectEmployeeById(int employeeId) {
        return "SELECT * FROM EMPLOYEE_INFO WHERE EMPLOYEE_ID = " + employeeId;
    }

    public static String deleteEmployeeByID(int employeeId) {
        return "DELETE FROM EMPLOYEE_INFO WHERE EMPLOYEE_ID = " + employeeId;
    }

    public static String updateEmployeeQuery(int employeeId) {
        return "UPDATE EMPLOYEE_INFO SET EMPLOYEE_NAME = ?, " +
                "EMPLOYEE_ADDRESS = ?, " +
                "EMPLOYEE_SALARY = ? WHERE EMPLOYEE_ID = " + employeeId;
    }
}