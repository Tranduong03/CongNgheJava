package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillDAO {
    public ResultSet getAllBills() {
        ResultSet rs = null;
        rs= SQLOperation.GetDatabase("SELECT * FROM Invoice where TotalMoney > 0");
        return rs;
    }

    public int getInvoiceID(String IDCode) throws SQLException {
        ResultSet rs = null;
        rs = SQLOperation.GetDatabase("SELECT InvoiceID FROM Invoice where InvoiceCode = '"+ IDCode +"'");

        return rs.next()? rs.getInt(1): 0;
    }

    public ResultSet getInvoice(int ID) throws SQLException {
        ResultSet rs= null;
        rs= SQLOperation.GetDatabase("SELECT CustomerID, EmployeeID FROM Invoice where InvoiceID = '"+ ID +"'");
        return rs;
    }

    public ResultSet getDetailsBillByIDCode(String IDCode) {
        ResultSet rs = null;
        rs = SQLOperation.GetDatabase("SELECT di.* " +
                "FROM DetailsInvoice di " +
                "JOIN Invoice i ON di.InvoiceID = i.InvoiceID " +
                "WHERE i.InvoiceCode = '"+ IDCode +"'; ");

        return rs;
    }

    public double getTotalRevenue() throws SQLException {
        ResultSet rs = null;
        rs = SQLOperation.GetDatabase("SELECT Sum(TotalMoney) FROM Invoice");
        return rs.next()? rs.getDouble(1): -1.0;
    }
}
