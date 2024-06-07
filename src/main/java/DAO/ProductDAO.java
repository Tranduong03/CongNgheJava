package DAO;

import Model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    public ResultSet getAllProducts() {
        ResultSet rs;
        rs = SQLOperation.GetDatabase("SELECT Product.Name, Product.SellPrice, Product.Quantity, Category.Name AS CategoryName, Product.Description\n" +
                "FROM Product\n" +
                "INNER JOIN Category ON Category.CategoryID = Product.CategoryID\n" +
                "WHERE Product.Status = 1;\n");
        return rs;
    }

    public ResultSet getSearchProduct(String name) {
        ResultSet rs;
        rs = SQLOperation.GetDatabase("SELECT Product.Name, Product.SellPrice, Product.Quantity, Category.Name AS CategoryName, Product.Description\n" +
                "FROM Product\n" +
                "INNER JOIN Category ON Category.CategoryID = Product.CategoryID\n" +
                "WHERE Product.Status = 1 AND Product.Name LIKE '%"+ name +"%';\n");
        return rs;
    }

    public int getProductID(String name) throws SQLException{
        ResultSet rs;
        rs = SQLOperation.GetDatabase("Select ProductID from Product where Name = '"+ name +"';");
        return rs.next()? rs.getInt(1): -1;
    }

    public void setProduct(Product product, String oldName) throws SQLException {
        SQLOperation.SetDatabase("update Product " +
                                        "SET Name = '"+ product.getName() +"'," +
                                            "SellPrice ="+ product.getSellPrice() +"," +
                                            "CategoryID = "+ getCategoryID(product.getCategoryName()) +"," +
                                            "Description = '"+ product.getDescription() + "' " +
                                        "Where ProductID = "+ getProductID(oldName) +" "
                                ,"Edit Prod complete");
    }

    public int getCategoryID(String CategoryName) throws SQLException {
        ResultSet rs;
        rs=SQLOperation.GetDatabase("SELECT CategoryID FROM Category WHERE Name = '" + CategoryName + "'");
        assert rs != null;
        return rs.next()? rs.getInt(1) : null;
    }

    public String getCategoryName(int CategoryID) throws SQLException {
        ResultSet rs;
        rs=SQLOperation.GetDatabase("SELECT Name FROM Category WHERE CategoryID = " + CategoryID);
        assert rs != null;
        return rs.next()? rs.getString(1) : null;
    }

    public void addProduct(Product product) {
        SQLOperation.SetDatabase("INSERT INTO Product(Name, CategoryID," +
                                        " SellPrice, Description, Quantity) " +
                                        "VALUES('"+ product.getName() +"', "+ product.getCategoryID() +"," +
                                        " "+ product.getSellPrice() +", '"+ product.getDescription() +"'," +
                                        " "+ product.getQuantity() +");","Product Add Completed");
    }

    public ResultSet getAllCategory(){
        ResultSet rs;
        rs = SQLOperation.GetDatabase("SELECT Name FROM Category;");
        return rs;
    }

    public int getNumCategory(){
        ResultSet rs;
        rs = SQLOperation.GetDatabase("SELECT COUNT(*) FROM Category;");
        try {
            return rs.next() ? rs.getInt(1): null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delProduct(String prodName) throws SQLException {
        SQLOperation.SetDatabase("UPDATE Product SET Status = 0 WHERE Name ='"+ prodName +"';", "Delete Product: " + prodName);
    }

    public void delProducts(String[] prodNames) throws SQLException {
        for (String prodName : prodNames) {
            delProduct(prodName);
        }
    }

    public String getProductName(int productID) throws SQLException {
        ResultSet rs = null;
        rs= SQLOperation.GetDatabase("Select Name from Product where ProductID = "+ productID +";");
        return rs.next()?rs.getString("Name"):"";
    }

    public int getNumProduct() throws SQLException {
        ResultSet rs;
        rs = SQLOperation.GetDatabase("SELECT COUNT(*) FROM Product");
        return rs.next()?rs.getInt(1): -1;
    }

    public void pushProduct(int productID, int quantity){
        SQLOperation.SetDatabase("UPDATE Product\n" +
                                        "SET Quantity = Quantity + "+ quantity +
                                        " WHERE ProductID = "+ productID +"  ;\n",
                                    "Add completed "+ quantity +" for product: "+ productID);
    }

    public ResultSet getRevenueDetails(){
        ResultSet rs = SQLOperation.GetDatabase("SELECT \n" +
                "    p.Name,\n" +
                "    COALESCE(SUM(di.Quantity), 0) AS QuantitySold,\n" +
                "    COALESCE(SUM(di.Quantity * di.Value), 0) AS TotalValueSold\n" +
                "FROM \n" +
                "    Product p\n" +
                "LEFT JOIN \n" +
                "    DetailsInvoice di ON p.ProductID = di.ProductID\n" +
                "GROUP BY \n" +
                "    p.ProductID, p.Name, p.Description, p.SellPrice\n" +
                "HAVING\n" +
                "\tCOALESCE(SUM(di.Quantity), 0) > 0\n" +
                "ORDER BY \n" +
                "    TotalValueSold DESC;");

        return rs;
    }
}