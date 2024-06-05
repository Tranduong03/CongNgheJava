package Model;

import java.time.LocalDate;

public class Product {

    public Product() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    private int id;
    private String name;
    private double sellPrice;
    private int quantity;
    private int categoryID;
    private String categoryName;
    private String description;


    public Product(String name, double sellPrice, int quantity, String categoryName, String description) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.categoryName = categoryName;
        this.description = description;
    }



}
