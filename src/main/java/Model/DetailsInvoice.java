package Model;

public class DetailsInvoice {
    private int InvoiceID;
    private int ProductID;
    private String ProductName;
    private int Quantity;
    private double Value;
    private double SubTotal;

    public DetailsInvoice(String productName, int quantity, double value, double subTotal, int invoiceID, int productID) {
        ProductName = productName;
        Quantity = quantity;
        Value = value;
        SubTotal = subTotal;
        InvoiceID = invoiceID;
        ProductID = productID;
    }

    public DetailsInvoice() {
    }

    public DetailsInvoice(String productName, int quantity, double value, double subTotal) {
        ProductName = productName;
        Quantity = quantity;
        Value = value;
        SubTotal = subTotal;
    }


    public int getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        InvoiceID = invoiceID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(double subTotal) {
        SubTotal = subTotal;
    }
}
