package DAO;

import Model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    public static Employee checkLogin(String account, String password) {
        Employee employee = null;
        try {
            ResultSet rs = SQLOperation.GetDatabase("select* from Employee "
                    + "where Account='"+ account +"' "
                    + "and password= '"+ password + "'; ");
            while (true){
                assert rs != null;
                if (!rs.next()) break;
                employee = new Employee();

                employee.setId(rs.getInt(1));
                employee.setAccount(rs.getString(2));
                employee.setPassword(rs.getString(3));
                employee.setName(rs.getString(4));
                employee.setBirthDate(rs.getDate(5).toLocalDate());
                employee.setHireDate(rs.getDate(6).toLocalDate());
                employee.setPhone(rs.getString(7));
                employee.setEmail(rs.getString(8));
                employee.setGender(rs.getBoolean(9));
                employee.setSalary(rs.getDouble(10));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employee;
    }
}
