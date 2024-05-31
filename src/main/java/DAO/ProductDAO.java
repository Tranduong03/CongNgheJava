package DAO;

import Model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    public ResultSet getAllProducts() {
        ResultSet rs;
        rs = SQLOperation.GetDatabase("select Name, SellPrice, Quantity, CategoryID, Description "
                + "from Product "
                + "where Status = 1;");
        return rs;
    }

    public ResultSet getSearchProduct(String name) {
        ResultSet rs;
        rs = SQLOperation.GetDatabase("Select Name, SellPrice, Quantity, CategoryID, Description " +
                                            " from Product " +
                                            " Where Status = 1 AND Name LIKE '%"+ name +"%';");
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
}