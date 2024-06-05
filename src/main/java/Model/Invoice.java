package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Invoice {
    private int InvoiceID;//numeric ID for bill
    private String InvoiceCode;//String ID for bill
    private int CustomerID;
    private int EmployeeID;
    private LocalDateTime DateBill;
    private double TotalMoney;

    public Invoice(int invoiceID, String invoiceCode, int customerID, int employeeID, LocalDateTime dateBill) {
        InvoiceID = invoiceID;
        InvoiceCode = invoiceCode;
        CustomerID = customerID;
        EmployeeID = employeeID;
        DateBill = dateBill;
    }

    public Invoice() {

    }

    public Invoice(String invoiceCode, LocalDateTime dateBill, double totalMoney) {
        InvoiceCode = invoiceCode;
        DateBill = dateBill;
        TotalMoney = totalMoney;
    }

    public int getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        InvoiceID = invoiceID;
    }

    public String getInvoiceCode() {
        return InvoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        InvoiceCode = invoiceCode;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public int getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(int employeeID) {
        EmployeeID = employeeID;
    }

    public LocalDateTime getDateBill() {
        return DateBill;
    }

    public void setDateBill(LocalDateTime dateBill) {
        DateBill = dateBill;
    }

    public double getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        TotalMoney = totalMoney;
    }
}
