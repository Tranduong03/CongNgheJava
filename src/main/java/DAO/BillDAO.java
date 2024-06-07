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

    public int getInvoiceID(int empID, int cusID) throws SQLException {
        ResultSet rs = null;
        String query;

        if (cusID != 0) {
            query = "SELECT InvoiceID FROM Invoice WHERE EmployeeID = " + empID + " AND CustomerID = " + cusID + " ORDER BY CustomerID desc;";
        } else {
            query = "SELECT InvoiceID FROM Invoice WHERE EmployeeID = " + empID + " AND CustomerID IS NULL ORDER BY CustomerID desc;";
        }

        rs = SQLOperation.GetDatabase(query);
        return rs.next() ? rs.getInt(1) : 0;
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

    public ResultSet getRevenueByMonth() throws SQLException{
        ResultSet rs= null;
        rs= SQLOperation.GetDatabase("WITH MonthlyRevenue AS (\n" +
                "    SELECT\n" +
                "        FORMAT(DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - Number, 0), 'yyyy-MM') AS Month,\n" +
                "        SUM(COALESCE(di.Quantity * di.Value, 0)) AS Revenue\n" +
                "    FROM\n" +
                "        (SELECT TOP 12 ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) - 1 AS Number\n" +
                "         FROM master.dbo.spt_values) AS Numbers\n" +
                "    LEFT JOIN\n" +
                "        Invoice i ON i.DateBill >= DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - Number, 0)\n" +
                "                 AND i.DateBill < DATEADD(MONTH, DATEDIFF(MONTH, -1, GETDATE()) - Number, 0)\n" +
                "    LEFT JOIN\n" +
                "        DetailsInvoice di ON i.InvoiceID = di.InvoiceID\n" +
                "    GROUP BY\n" +
                "        FORMAT(DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - Number, 0), 'yyyy-MM')\n" +
                ")\n" +
                "SELECT\n" +
                "    Month,\n" +
                "    Revenue\n" +
                "FROM\n" +
                "    MonthlyRevenue\n" +
                "ORDER BY\n" +
                "    Month ASC;\n");
        return rs;
    }

    public void createBill(int empID, int cusID){
        SQLOperation.SetDatabase("INSERT INTO Invoice(EmployeeID, CustomerID) VALUES("+ empID + ", "+ cusID +" )","Bill create completed");
    }

    public void createBill(int empID){
        SQLOperation.SetDatabase("INSERT INTO Invoice(EmployeeID, CustomerID) VALUES("+ empID + ", NULL )","Bill create completed");
    }

    public void createBillDetails(int billID, int prodID, int quantity){
        SQLOperation.SetDatabase("INSERT INTO DetailsInvoice(InvoiceID, ProductID, Quantity)" +
                                        " VALUES("+ billID + ","+ prodID + ","+ quantity + ");",
                                String.format("Bill %d add prod %d x %d", billID, prodID, quantity));

        SQLOperation.SetDatabase("UPDATE Product\n" +
                "SET Quantity = Quantity - "+ quantity +"\n" +
                "WHERE ProductID = "+ prodID +";\n","");
    }

}
