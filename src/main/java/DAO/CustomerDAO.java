package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
    public int getNumCustomers(){
        ResultSet rs = null;
        try {
            rs = SQLOperation.GetDatabase("SELECT COUNT(*) FROM Customer;");
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

    public ResultSet getAllCusInPage(int page) throws SQLException, Exception {
        ResultSet result;
        int numCustomers = getNumCustomers();
        if(page == getNumCustomers()/8+1){
            result = SQLOperation.GetDatabase("SELECT CustomerID, Name, Gender, Phone \n" +
                    "FROM Customer \n" +
                    "ORDER BY CustomerID \n" +
                    "OFFSET " + (page-1)*8 + "ROWS FETCH NEXT " + (numCustomers - (page-1)*8)  + " ROWS ONLY" );
        }
        else {
            result = SQLOperation.GetDatabase("SELECT CustomerID, Name, Gender, Phone \n" +
                    "FROM Customer \n" +
                    "ORDER BY CustomerID \n" +
                    "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT 8 ROWS ONLY;");
        }

        return result;
    }

    public int getSearchedListCustomer(String name) throws SQLException, Exception {
        ResultSet rs = null;
        try {
            String query = "SELECT COUNT(*) FROM Customer WHERE Name LIKE '%" + name + "%';";
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

    public ResultSet getSearchedCusInPage(int page, String name) throws SQLException, Exception {
        ResultSet result;
        int numEmployees = getSearchedListCustomer(name);
        if(page == getSearchedListCustomer(name)/8+1){
            result = SQLOperation.GetDatabase("SELECT CustomerID, Name, Gender, Phone \n" +
                    "FROM Customer\n" +
                    "WHERE Name LIKE '%"+ name + "%' " +
                    "ORDER BY CustomerID \n" +
                    "OFFSET " + (page-1)*8 + "ROWS FETCH NEXT " + (numEmployees - (page-1)*8)  + " ROWS ONLY;" );
        }
        else {
            result = SQLOperation.GetDatabase("SELECT CustomerID, Name, Gender, Phone \n" +
                    "FROM Customer \n" +
                    "WHERE Name LIKE '%"+ name + "%' " +
                    "ORDER BY CustomerID \n" +
                    "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT 8 ROWS ONLY;");
        }

        return result;
    }

    public ResultSet getCustomer(String customerID) throws SQLException, Exception {
        ResultSet rs = null;
        rs = SQLOperation.GetDatabase("SELECT CustomerID, Name, Gender, Phone, Birthdate, Address, Email, Tichdiem " +
                "FROM Customer " +
                "WHERE CustomerID = "+ customerID +";");
        return rs;
    }

    public void deleteCustomer(int customerID) throws SQLException, Exception {
        SQLOperation.SetDatabase("DELETE FROM Customer WHERE CustomerID='"+ customerID +"';", "Delete Success");
    }

    public ResultSet name_getSortCusInPage(int page) throws SQLException, Exception {
        ResultSet result;
        int numCustomers = getNumCustomers();
        if(page == getNumCustomers() / 8 + 1) {
            result = SQLOperation.GetDatabase(
                    "SELECT CustomerID, Name, Gender, Phone " +
                            "FROM Customer " +
                            "ORDER BY Name ASC " +
                            "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT " + (numCustomers - (page - 1) * 8) + " ROWS ONLY;");
        } else {
            result = SQLOperation.GetDatabase(
                    "SELECT CustomerID, Name, Gender, Phone " +
                            "FROM Customer " +
                            "ORDER BY Name ASC " +
                            "OFFSET " + (page - 1) * 8 + " ROWS FETCH NEXT 8 ROWS ONLY;");
        }
        return result;
    }

}
