package Controller;

import DAO.BillDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.ProductDAO;
import Model.DetailsInvoice;
import Model.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class BillController implements Initializable {

    @FXML private Button btn_PrintBill;

    @FXML private TextField txt_Search;
    @FXML private ImageView btn_Search;
    @FXML private ChoiceBox<String> cb_Bill;

    @FXML private TableView<Invoice> table_Bill;
    @FXML private TableColumn<Invoice, Integer> bill_Ordinal;
    @FXML private TableColumn<Invoice, String> bill_CodeID;
    @FXML private TableColumn<Invoice, LocalDateTime> bill_Date;
    @FXML private TableColumn<Invoice, Double> bill_Total;

    @FXML private Label lb_BillID;
    @FXML private Label lb_DateBill;
    @FXML private Label lb_EmployeeName;
    @FXML private Label lb_CustomerName;

    @FXML private TableView<DetailsInvoice> table_ProdList;
    @FXML private TableColumn<DetailsInvoice, String> prod_Name;
    @FXML private TableColumn<DetailsInvoice, Double> prod_SellPrice;
    @FXML private TableColumn<DetailsInvoice, Integer> prod_Quantity;
    @FXML private TableColumn<DetailsInvoice, Double> prod_Total;

    @FXML private Label lb_TotalBill;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bill_Ordinal.setCellFactory(new Callback<TableColumn<Invoice, Integer>, TableCell<Invoice, Integer>>() {
            @Override
            public TableCell<Invoice, Integer> call(TableColumn<Invoice, Integer> param) {
                return new TableCell<Invoice, Integer>() {
                    @Override
                    protected void updateItem(Integer item, boolean empty) {
                        super.updateItem(item, empty);
                        if (this.getTableRow() != null && !empty) {
                            setText(String.valueOf(this.getTableRow().getIndex() + 1));
                        } else {
                            setText("");
                        }
                    }
                };
            }
        });

        table_Bill.setRowFactory(tv -> {
            TableRow<Invoice> row = new TableRow<>();
            row.setPrefHeight(30); // Set preferred height for each row
            return row;
        });

        bill_Total.setCellFactory(column -> new TableCell<Invoice, Double>() {
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

        bill_Total.setCellValueFactory(new PropertyValueFactory<>("TotalMoney"));
        bill_CodeID.setCellValueFactory(new PropertyValueFactory<>("InvoiceCode"));
        bill_Date.setCellValueFactory(new PropertyValueFactory<>("DateBill"));

        table_ProdList.setRowFactory(tv -> {
            TableRow<DetailsInvoice> row = new TableRow<>();
            row.setPrefHeight(30); // Set preferred height for each row
            return row;
        });

        table_Bill.setRowFactory(tv -> {
            TableRow<Invoice> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
                    try {
                        update_Info();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        setTable_ProdList();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            return row;
        });


        prod_SellPrice.setCellFactory(column -> new TableCell<DetailsInvoice, Double>() {
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

        prod_Total.setCellFactory(column -> new TableCell<DetailsInvoice, Double>() {
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


        prod_Name.setCellValueFactory(new PropertyValueFactory<>("ProductName"));
        prod_Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        prod_Total.setCellValueFactory(new PropertyValueFactory<>("SubTotal"));
        prod_SellPrice.setCellValueFactory(new PropertyValueFactory<>("Value"));

        lb_BillID.setText("");
        lb_DateBill.setText("");
        lb_CustomerName.setText("");
        lb_EmployeeName.setText("");
        lb_TotalBill.setText("");

        try {
            table_Bill.setItems(getSampleBillData());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void update_Info() throws Exception {
        int billID = new BillDAO().getInvoiceID(table_Bill.getSelectionModel().getSelectedItem().getInvoiceCode());
        System.out.print("BillID: "+ billID);
        lb_DateBill.setText(table_Bill.getSelectionModel().getSelectedItem().getDateBill().toString());
        lb_BillID.setText(String.valueOf(billID));

        try {
            ResultSet rs= new BillDAO().getInvoice(billID);
            while (rs.next()){
                String cus_Name = new CustomerDAO().getCustomerName(rs.getInt("CustomerID"));
                System.out.print(" "+ cus_Name);
                String emp_Name = new EmployeeDAO().getEmployeeName(rs.getInt("EmployeeID"));
                System.out.println(" "+ emp_Name);
                lb_CustomerName.setText(cus_Name);
                lb_EmployeeName.setText(emp_Name);
            }
        } catch (SQLException e) {
            return;
        } catch (NullPointerException e){
            return;
        }
    }

    private void setTable_ProdList() throws SQLException {
        String invoiceCode = table_Bill.getSelectionModel().getSelectedItem().getInvoiceCode();;

        table_ProdList.getItems().clear();
        table_ProdList.setItems(getDetailsBillData(invoiceCode));

        double totalBill = 0.0;
        for(DetailsInvoice detail: table_ProdList.getItems()){
            totalBill += detail.getSubTotal();

        }

        lb_TotalBill.setText("$" + String.valueOf(totalBill));
    }

    private ObservableList<DetailsInvoice> getDetailsBillData(String IDCode) throws SQLException {
        ObservableList<DetailsInvoice> detailsInvoices = FXCollections.observableArrayList();
        ResultSet rs = new BillDAO().getDetailsBillByIDCode(IDCode);

        while (rs.next()){
            int productID = rs.getInt("ProductID");
            String productName = new ProductDAO().getProductName(productID);
            System.out.println(rs.getDouble("SubTotal"));

            DetailsInvoice detail = new DetailsInvoice(
                    productName,
                    rs.getInt("Quantity"),
                    rs.getDouble("Value"),
                    rs.getDouble("SubTotal")
            );

            detailsInvoices.add(detail);
        }
        return detailsInvoices;
    }

    private ObservableList<Invoice> getSampleBillData() throws SQLException {
        ObservableList<Invoice> bill = FXCollections.observableArrayList();

        ResultSet rs = new BillDAO().getAllBills();
        while (rs.next()){
            Timestamp dateBill = rs.getTimestamp("DateBill");
            Invoice invoice = new Invoice(
                    rs.getString("InvoiceCode"),
                    dateBill.toLocalDateTime(),
                    rs.getDouble("TotalMoney")
            );
            bill.add(invoice);
        }
        return bill;
    }


}
