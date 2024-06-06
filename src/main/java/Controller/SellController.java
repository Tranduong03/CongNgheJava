package Controller;

import DAO.ProductDAO;
import Model.Product;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SellController implements Initializable {
    @FXML private TextField txt_ProdSearch;
    @FXML private ImageView btn_ProdSearch;

    @FXML private Button btn_AddNewCus;
    @FXML private Button btn_CompleteTrade;
    @FXML private Button btn_unselected;

    @FXML private TableView<Product> prod_ProductShow;
    @FXML private TableColumn<Product, Integer> prod_Ordinal;
    @FXML private TableColumn<Product, String> prod_name;
    @FXML private TableColumn<Product, Double> prod_Category;
    @FXML private TableColumn<Product, Integer> prod_Quantity;
    @FXML private TableColumn<Product, Double> prod_SellPrice;

    @FXML private TableView<Product> prod_ProductChoose;
    @FXML private TableColumn<Product, Integer> prodC_Ordinal;
    @FXML private TableColumn<Product, String> prodC_name;
    @FXML private TableColumn<Product, Integer> prodC_PurchaseQuantity;

    @FXML private TextField txt_CusName;
    @FXML private Label lb_totalRevenue;
    @FXML private Label lb_tax;
    @FXML private TextField txt_Received;
    @FXML private Label lb_refund;
    @FXML private Label lb_Alert;

    private boolean searchFlag = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lb_Alert.setVisible(false);
        //List Product Setup
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

        prod_ProductShow.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setPrefHeight(30); // Set preferred height for each row
            return row;
        });

        prod_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        prod_Category.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        prod_SellPrice.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        prod_Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //Choose Product Setup
        prodC_Ordinal.setCellFactory(new Callback<TableColumn<Product, Integer>, TableCell<Product, Integer>>() {
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

        prodC_name.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Cấu hình cột PurchaseQuantity
        prodC_PurchaseQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        prodC_PurchaseQuantity.setOnEditCommit(event -> {
            Product product = event.getRowValue();
            product.setPurchaseQuantity(event.getNewValue());
            calculateTotal();
        });
        prodC_PurchaseQuantity.setCellValueFactory(new PropertyValueFactory<>("purchaseQuantity"));

        prod_ProductChoose.setEditable(true);

        try {
            prodShow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        prod_ProductShow.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                try {
                    setTable_ProductChoose(newSelection);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        
        btn_CompleteTrade.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                completeTrade();
            }
        });

    }

    private void completeTrade() {
        try {
            double refund = Double.parseDouble(txt_Received.getText()) - calculateTotal();
            if(refund<0){
                lb_Alert.setVisible(true);
                lb_Alert.setText("*  The money you give us is not enough to pay the bill");
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(ev -> lb_Alert.setVisible(false));
                pause.play();
                txt_Received.setText("");
                lb_refund.setText("");
                return;
            }
        } catch (NumberFormatException e) {
            lb_Alert.setVisible(true);
            lb_Alert.setText("* Please enter a positive number");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(ev -> lb_Alert.setVisible(false));
            pause.play();
            txt_Received.setText("");
            lb_refund.setText("");
            return;
        } finally {

        }
    }

    private double calculateTotal() {
        double total = 0.0;
        for (Product product : prod_ProductChoose.getItems()) {
            total += product.getSellPrice() * product.getPurchaseQuantity();
        }
        double tax = total / 10;

        lb_totalRevenue.setText(String.format("$%.2f", total));
        lb_tax.setText(String.format("$%.2f", tax));

        return total+tax;
    }

    private void unselectProduct() {
        Product selectedProduct = prod_ProductChoose.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            prod_ProductChoose.getItems().remove(selectedProduct);
        }
        calculateTotal();
    }

    private void setTable_ProductChoose(Product selectedProduct) throws SQLException {
        if (selectedProduct != null) {
            ObservableList<Product> chosenProducts = prod_ProductChoose.getItems();
            boolean alreadyExists = chosenProducts.stream().anyMatch(p -> p.getName().equals(selectedProduct.getName()));
            if (!alreadyExists) {
                Product newProduct = new Product(
                        selectedProduct.getName(),
                        selectedProduct.getSellPrice(),
                        selectedProduct.getQuantity(),
                        selectedProduct.getCategoryName(),
                        selectedProduct.getDescription()
                );
                chosenProducts.add(newProduct);
                calculateTotal();
            }
        }
        btn_unselected.setOnAction(event -> unselectProduct());
    }

    private void prodShow() throws SQLException{
        if(!searchFlag){
            prod_ProductShow.getItems().clear();
            prod_ProductShow.setItems(getSampleProductData());
        }
        else {
            prod_ProductShow.getItems().clear();
            prod_ProductShow.setItems(getSearchProductData(txt_ProdSearch.getText()));
        }
    }

    private ObservableList<Product> getSearchProductData(String text) throws SQLException {
        ObservableList<Product> products = FXCollections.observableArrayList();
        ResultSet rs = new ProductDAO().getSearchProduct(text);
        while(rs.next()){
            Product prod = new Product(
                    rs.getString("Name"),
                    rs.getDouble("SellPrice"),
                    rs.getInt("Quantity"),
                    rs.getString("CategoryName"),
                    rs.getString("Description")
            );

            products.add(prod);
        }
        return products;
    }

    private ObservableList<Product> getSampleProductData() throws SQLException {
        ObservableList<Product> products = FXCollections.observableArrayList();
        ResultSet rs = new ProductDAO().getAllProducts();
        while(rs.next()){
            Product prod = new Product(
                    rs.getString("Name"),
                    rs.getDouble("SellPrice"),
                    rs.getInt("Quantity"),
                    rs.getString("CategoryName"),
                    rs.getString("Description")
            );

            products.add(prod);
        }
        return products;
    }
}
