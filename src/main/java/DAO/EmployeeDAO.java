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

    public int getNumEmployees() {
        ResultSet rs = null;
        try {
            rs = SQLOperation.GetDatabase("SELECT COUNT(*) FROM Employee;");
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Query did not return any result.");
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ, có thể hiển thị thông báo lỗi, ghi log, hoặc ném ngoại lệ khác để được xử lý ở một cấp độ cao hơn.
            throw new RuntimeException("Error while getting number of employees: " + e.getMessage(), e);
        } finally {
            // Đảm bảo ResultSet được đóng sau khi sử dụng xong.
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Xử lý ngoại lệ khi đóng ResultSet.
                    throw new RuntimeException("Error closing ResultSet: " + e.getMessage(), e);
                }
            }
        }
    }

    public ResultSet getAllEmpInPage(int page) throws SQLException, Exception {
        ResultSet result;
        int numEmployees = getNumEmployees();
        if(page == getNumEmployees()/8+1){
            result = SQLOperation.GetDatabase("SELECT EmployeeID, Name, HireDate, Salary\n" +
                    "FROM Employee\n" +
                    "ORDER BY EmployeeID \n" +
                    "OFFSET " + (page-1)*8 + "ROWS FETCH NEXT " + (numEmployees - (page-1)*8)  + " ROWS ONLY" );
        }
        else {
            result = SQLOperation.GetDatabase("SELECT EmployeeID, Name, HireDate, Salary \n" +
                    "FROM Employee \n" +
                    "ORDER BY EmployeeID \n" +
                    "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT 8 ROWS ONLY;");
        }

        return result;
    }

    public int getSearchedListEmployee(String name) throws SQLException, Exception {
        ResultSet rs = null;
        try {
            String query = "SELECT COUNT(*) FROM Employee WHERE Name LIKE '%" + name + "%';";
            rs = SQLOperation.GetDatabase(query);
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Query did not return any result.");
            }
        } catch (SQLException e) {
            // Xử lý ngoại lệ, có thể hiển thị thông báo lỗi, ghi log, hoặc ném ngoại lệ khác để được xử lý ở một cấp độ cao hơn.
            throw new RuntimeException("Error while getting number of employees: " + e.getMessage(), e);
        } finally {
            // Đảm bảo ResultSet được đóng sau khi sử dụng xong.
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    // Xử lý ngoại lệ khi đóng ResultSet.
                    throw new RuntimeException("Error closing ResultSet: " + e.getMessage(), e);
                }
            }
        }
    }

    public ResultSet getSearchedEmpInPage(int page, String name) throws SQLException, Exception {
        ResultSet result;
        int numEmployees = getSearchedListEmployee(name);
        if(page == getSearchedListEmployee(name)/8+1){
            result = SQLOperation.GetDatabase("SELECT EmployeeID, Name, HireDate, Salary\n" +
                    "FROM Employee\n" +
                    "WHERE Name LIKE '%"+ name + "%' " +
                    "ORDER BY EmployeeID \n" +
                    "OFFSET " + (page-1)*8 + "ROWS FETCH NEXT " + (numEmployees - (page-1)*8)  + " ROWS ONLY;" );
        }
        else {
            result = SQLOperation.GetDatabase("SELECT EmployeeID, Name, HireDate, Salary \n" +
                    "FROM Employee \n" +
                    "WHERE Name LIKE '%"+ name + "%' " +
                    "ORDER BY EmployeeID \n" +
                    "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT 8 ROWS ONLY;");
        }

        return result;
    }

    public ResultSet getEmployee(int employeeID) throws SQLException, Exception {
        ResultSet rs = null;
        rs = SQLOperation.GetDatabase("SELECT EmployeeID, Name, Gender, BirthDate, Phone, Email, Salary, HireDate, Account, Address " +
                    "FROM Employee " +
                    "WHERE EmployeeID = "+ employeeID +";");
        return rs;
    }

    public void deleteEmployee(int employeeID) throws SQLException, Exception {
        SQLOperation.SetDatabase("DELETE FROM Employee WHERE EmployeeID='"+ employeeID +"';", "Delete Success");
    }

    public ResultSet name_getSortEmpInPage(int page) throws SQLException, Exception {
        ResultSet result;
        int numEmployees = getNumEmployees();
        if(page == getNumEmployees() / 8 + 1) {
            result = SQLOperation.GetDatabase(
                    "SELECT EmployeeID, Name, HireDate, Salary " +
                            "FROM Employee " +
                            "ORDER BY Name ASC " +
                            "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT " + (numEmployees - (page - 1) * 8) + " ROWS ONLY;");
        } else {
            result = SQLOperation.GetDatabase(
                    "SELECT EmployeeID, Name, HireDate, Salary " +
                            "FROM Employee " +
                            "ORDER BY Name ASC " +
                            "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT 8 ROWS ONLY;");
        }
        return result;
    }

    public ResultSet salary_getSortEmpInPage(int page) throws SQLException, Exception {
        ResultSet result;
        int numEmployees = getNumEmployees();
        if(page == getNumEmployees() / 8 + 1) {
            result = SQLOperation.GetDatabase(
                    "SELECT EmployeeID, Name, HireDate, Salary " +
                            "FROM Employee " +
                            "ORDER BY Salary DESC " +
                            "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT " + (numEmployees - (page - 1) * 8) + " ROWS ONLY;");
        } else {
            result = SQLOperation.GetDatabase(
                    "SELECT EmployeeID, Name, HireDate, Salary " +
                            "FROM Employee " +
                            "ORDER BY Salary DESC " +
                            "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT 8 ROWS ONLY;");
        }
        return result;
    }

    public String getEmployeeName(int employeeID) throws Exception {
        ResultSet rs = getEmployee(employeeID);

        return rs.next()? rs.getString("Name"): "";
    }

    public ResultSet getTop5Employee() throws SQLException, Exception {
        ResultSet rs = null;
        rs = SQLOperation.GetDatabase("SELECT TOP 5 \n" +
                "    E.Name,\n" +
                "    SUM(I.TotalMoney) AS TotalSales\n" +
                "FROM \n" +
                "    Employee E\n" +
                "JOIN \n" +
                "    Invoice I ON E.EmployeeID = I.EmployeeID\n" +
                "GROUP BY \n" +
                "    E.EmployeeID, E.Name\n" +
                "ORDER BY \n" +
                "    TotalSales DESC;\n");

        return rs;
    }

}
