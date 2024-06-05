package Controller;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import DAO.ProductDAO;
import DAO.SQLOperation;
import Model.Product;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class ProductController implements Initializable {

    @FXML private AnchorPane prod_Show;
    @FXML private TableView<Product> prod_TableShow;
    @FXML private TableColumn<Product, Integer> prod_Ordinal;
    @FXML private TableColumn<Product, String> prod_name;
    @FXML private TableColumn<Product, String> prod_Category;
    @FXML private TableColumn<Product, Double> prod_SellPrice;
    @FXML private TableColumn<Product, Integer> prod_Quantity;
    @FXML private TableColumn<Product, String> prod_Description;

    @FXML private TabPane prod_Tab;

    @FXML public Tab tab_AddProd;
    @FXML private Tab tab_UpdateProd;
    @FXML private Tab tab_DeleteProd;

    @FXML private TextField txt_Search;
    @FXML private ImageView btn_Search;
    Boolean searchFlag = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        prod_Ordinal.setCellFactory(new Callback<TableColumn<Product, Integer>, TableCell<Product, Integer>>() {
            @Override
            public TableCell<Product, Integer> call(TableColumn<Product, Integer> param) {
                return new TableCell<Product, Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (this.getTableRow() != null && !empty) {
                            setText(this.getTableRow().getIndex() + 1 + "");
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        prod_TableShow.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setPrefHeight(30); // Set preferred height for each row
            return row;
        });

        prod_TableShow.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        prod_SellPrice.setCellFactory(column -> new TableCell<Product, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("$%.2f", item));
                }
            }
        });

        prod_Description.setCellFactory(column -> new TableCell<Product, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    Product product = getTableView().getItems().get(getIndex());
                    if (product.getQuantity()==0) {
                        setText("<SOLD OUT>"+ item);
                    } else {
                        setText(item);
                    }
                }
            }
        });

        // Set cell value factories for other columns
        prod_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        prod_Category.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        prod_SellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        prod_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        prod_Description.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Add sample data to the table
        try {
            prodShow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            setTab_AddProd();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        prod_TableShow.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setTab_UpdateProd();
            }
        });

        btn_Search.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    onSearchClicked();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        setTab_DeleteProd();

    }

    private ObservableList<Product> getSampleProductData() throws SQLException {
        ObservableList<Product> products = FXCollections.observableArrayList();
        ResultSet rs = new ProductDAO().getAllProducts();
        while(rs.next()){
            Product prod = new Product(
                    rs.getString("Name"),
                    rs.getDouble("SellPrice"),
                    rs.getInt("Quantity"),
                    new ProductDAO().getCategoryName(rs.getInt("CategoryID")),
                    rs.getString("Description")
            );

            products.add(prod);
        }

        return products;
    }

    // Tab Add Product
    private void setTab_AddProd() throws SQLException {
        Node content = tab_AddProd.getContent();
        AnchorPane pane = (AnchorPane) content.lookup("#pane_AddProd");
        Button save = (Button) pane.lookup("#btn_SaveNewProd");
        TextField name = (TextField) pane.lookup("#prod_AddName");
        TextField sellPrice = (TextField) pane.lookup("#prod_AddSellPrice");
        TextField quantity = (TextField) pane.lookup("#prod_AddQuantity");
        ChoiceBox<String> category = (ChoiceBox<String>) pane.lookup("#prod_AddCategory");
        List<String> categoryList = new ArrayList<>();
        ResultSet rs = new ProductDAO().getAllCategory();
        TextArea description = (TextArea) pane.lookup("#prod_AddDescription");
        try {
            while (rs.next()) {
                String categoryValue = rs.getString("Name");
                categoryList.add(categoryValue);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        category.getItems().addAll(categoryList);
        category.setValue("Sweets");

        rs.close();

        Product prod = new Product();

        save.setOnAction(event -> {
            try {
                String choseCategory = category.getSelectionModel().getSelectedItem();

                prod.setName(name.getText());
                prod.setCategoryName(choseCategory);
                try {
                    prod.setCategoryID(new ProductDAO().getCategoryID(choseCategory));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                prod.setDescription(description.getText());
                try {
                    prod.setSellPrice(Double.parseDouble(sellPrice.getText()));
                    prod.setQuantity(Integer.parseInt(quantity.getText()));
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter all the correctly number! (Quantity and SellPrice must be a positive number)");
                    alert.showAndWait();
                    return;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (prod != null) {
                System.out.println("Start to save");
                Boolean check = validate(prod);
                if (check) {
                    new ProductDAO().addProduct(prod);

                    try {
                        txt_Search.setText("");
                        prodShow();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });


    }

    // Add Product Validate
    private Boolean validate(Product product){
        String name = product.getName();
        double sellPrice = product.getSellPrice();
        int quantity = product.getQuantity();
        String categoryName = product.getCategoryName();
        String description = product.getDescription();

        if(name.equals("")){
            return false;
        }
        else {
            ResultSet rs = new SQLOperation().GetDatabase("Select Name from Product where Name = '"+ name +"';");
            try {
                if(rs.next()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("This product already exist. Please UPDATE Product!");
                    alert.showAndWait();
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        if(sellPrice<0) return false;

        if(quantity<0) return false;

        if(categoryName.equals("")) return false;

        return true;
    }

    // Tab Update Product
    public void setTab_UpdateProd() {
        Node content = tab_UpdateProd.getContent();
        AnchorPane pane = (AnchorPane) content.lookup("#pane_UpdateProd");
        TextField name = (TextField) pane.lookup("#prod_UpName");
        TextField sellPrice = (TextField) pane.lookup("#prod_UpSellPrice");
        TextArea description = (TextArea) pane.lookup("#prod_UpDescription");
        ChoiceBox<String> category = (ChoiceBox<String>) pane.lookup("#prod_UpCategory");
        category.getItems().clear();
        List<String> categoryList = new ArrayList<>();
        ResultSet rs = new ProductDAO().getAllCategory();
        try {
            while (rs.next()) {
                String categoryValue = rs.getString("Name");
                categoryList.add(categoryValue);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        category.getItems().addAll(categoryList);
        category.setValue("Sweets");

        Button save = (Button) pane.lookup("#btn_SaveProd");

        Product prod = prod_TableShow.getSelectionModel().getSelectedItem();
        name.setText(prod.getName());
        sellPrice.setText(String.valueOf(prod.getSellPrice()));
        description.setText(prod.getDescription());
        category.setValue(prod.getCategoryName());

        String curName = prod.getName();

        save.setOnAction(event -> {
            try {
                String choseCategory = category.getSelectionModel().getSelectedItem();

                prod.setName(name.getText());
                prod.setCategoryName(choseCategory);
                try {
                    prod.setCategoryID(new ProductDAO().getCategoryID(choseCategory));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                prod.setDescription(description.getText());
                try {
                    prod.setSellPrice(Double.parseDouble(sellPrice.getText()));
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Please enter all the correctly number! (SellPrice must be a positive number)");
                    alert.showAndWait();
                    return;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("Start to save");
            Boolean check = updateValidate(prod, curName);
            if(check) {
                try {
                    new ProductDAO().setProduct(prod, curName);
                    txt_Search.setText("");
                    prodShow();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // UpDate Product Validate
    private Boolean updateValidate(Product product, String curName){
        String name = product.getName();
        double sellPrice = product.getSellPrice();
        String categoryName = product.getCategoryName();
        String description = product.getDescription();

        if(name.equals("")){
            return false;
        }
        else {
            ResultSet rs = new SQLOperation().GetDatabase("Select Name from Product " +
                                                                "where Name = '"+ name +"' AND Name <> '"+ curName +"';");
            try {
                if(rs.next()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("This product already exist. Please Choose another name");
                    alert.showAndWait();
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(sellPrice<0) return false;

        if(categoryName.equals("")) return false;

        return true;
    }

    protected void onSearchClicked() throws SQLException {
        String name = txt_Search.getText();
        if(name.equals("")) searchFlag=false;
        else searchFlag=true;
        prodShow();
    }

    private ObservableList<Product> getSearchProductData(String name) throws SQLException {
        ObservableList<Product> listProd = FXCollections.observableArrayList();
        ResultSet rs = new ProductDAO().getSearchProduct(name);
        try {
            while(rs.next()){
                Product prod = new Product(
                        rs.getString("Name"),
                        rs.getDouble("SellPrice"),
                        rs.getInt("Quantity"),
                        new ProductDAO().getCategoryName(rs.getInt("CategoryID")),
                        rs.getString("Description")
                );

                listProd.add(prod);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProd;
    }

    private void prodShow() throws SQLException{
        if(!searchFlag){
            prod_TableShow.getItems().clear();
            prod_TableShow.setItems(getSampleProductData());
        }
        else {
            prod_TableShow.getItems().clear();
            prod_TableShow.setItems(getSearchProductData(txt_Search.getText()));
        }
    }

    private void setTab_DeleteProd(){
        Node content = tab_DeleteProd.getContent();
        AnchorPane pane = (AnchorPane) content.lookup("#pane_DeleteProd");
        Button del =(Button) pane.lookup("#btn_DeleteProd");
        TableView<String> tableDel =(TableView<String>) pane.lookup("#prod_ChooseToDel");
        TableColumn<String, String> chooseToDel = (TableColumn<String, String>) tableDel.getColumns().get(0);

        tableDel.setRowFactory(tv -> {
            TableRow<String> row1 = new TableRow<>();
            row1.setPrefHeight(25); // Set preferred height for each row
            return row1;
        });

        chooseToDel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

        prod_TableShow.getSelectionModel().getSelectedItems().addListener((ListChangeListener<Product>) change -> {
            tableDel.getItems().clear();
            for (Product chooseProd : prod_TableShow.getSelectionModel().getSelectedItems()) {
                tableDel.getItems().add(chooseProd.getName());
            }
        });

        del.setOnAction(event -> {
            ObservableList<String> items = tableDel.getItems();
            String[] nameprods = new String[items.size()];
            for (int i = 0; i < items.size(); i++) {
                nameprods[i] = items.get(i);
            }
            try {
                new ProductDAO().delProducts(nameprods);
                prodShow();
                tableDel.getItems().clear();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });

    }
}
