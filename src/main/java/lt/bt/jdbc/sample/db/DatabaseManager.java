package lt.bt.jdbc.sample.db;

import lt.bt.jdbc.sample.entity.DepartmentsEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String URL         = "jdbc:mysql://172.16.6.82/employees";
    private static final String USERNAME    = "root";
    private static final String PASSWORD    = "mypass";
    private static final String SELECT_DEPARTMENTS = "Select * From departments";
    private static final String SELECT_EMPLOYEE_MIN_SALARY = "select employees.emp_no, first_name, last_name, sum(salaries.salary) as salary from employees.employees\n" +
            "INNER JOIN employees.salaries\n" +
            "ON employees.emp_no = salaries.emp_no group by employees.emp_no order by salary, employees.emp_no asc limit 10";

    private Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection( URL, USERNAME, PASSWORD );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<DepartmentsEntity> getDepartmentns(){
        List<DepartmentsEntity> results = new ArrayList<>();
        try {
            int ID_COL = 1;
            int NAME_COL = 2;
            PreparedStatement ps = connection.prepareStatement(SELECT_DEPARTMENTS);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                DepartmentsEntity dpEntity = new DepartmentsEntity(rs.getString(ID_COL), rs.getString(NAME_COL));
                results.add(dpEntity);
            }
            closeResultSet(rs);
            closePrepaerdStatement(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    public List<Object> getMinSalary(){
        List<Object> results = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(SELECT_EMPLOYEE_MIN_SALARY);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getLong(1) + "  " +
                        rs.getString(2) + " " +
                        rs.getString(3) + " " +
                        rs.getLong(4));
            }
            closeResultSet(rs);
            closePrepaerdStatement(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    private void closePrepaerdStatement( PreparedStatement stmt ) throws SQLException
    {
        if ( stmt != null )
        {
            stmt.close();
        }
    }

    private static void closeResultSet( ResultSet rs ) throws SQLException
    {
        if ( rs != null )
        {
            rs.close();
        }
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
