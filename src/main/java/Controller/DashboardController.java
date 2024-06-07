package Controller;

import DAO.BillDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.ProductDAO;
import Model.Invoice;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML private Label lb_numberRevenue;
    @FXML private Label lb_numberProduct;
    @FXML private Label lb_numberEmployee;
    @FXML private AnchorPane pane_staff;
    @FXML private AnchorPane pane_Customer;

    @FXML private TableView<Product> table_ProdSold;
    @FXML private TableColumn<Product, Integer> prod_Ordinal;
    @FXML private TableColumn<Product, String> prod_Name;
    @FXML private TableColumn<Product, Integer> prod_QuantitySold;
    @FXML private TableColumn<Product, Double> prod_Total;

    @FXML private LineChart chart_Revenue;

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

        prod_Total.setCellFactory(column -> new TableCell<Product, Double>() {
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


        table_ProdSold.setRowFactory(tv -> {
            TableRow<Product> row = new TableRow<>();
            row.setPrefHeight(30); // Set preferred height for each row
            return row;
        });

        prod_Name.setCellValueFactory(new PropertyValueFactory<Product, String >("name"));
        prod_QuantitySold.setCellValueFactory(new PropertyValueFactory<Product, Integer>("quantity"));
        prod_Total.setCellValueFactory(new PropertyValueFactory<Product, Double>("total"));

        try {
            lb_numberProduct.setText(String.valueOf(new ProductDAO().getNumProduct()));
            lb_numberEmployee.setText(String.valueOf(new EmployeeDAO().getNumEmployees()));
            lb_numberRevenue.setText("$ " + new BillDAO().getTotalRevenue());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            set_PaneStaff();
            set_PaneCustomer();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            table_ProdSold.setItems(getSampleProductData());
            drawChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    private void drawChart() throws SQLException {
        chart_Revenue.setTitle("Revenue by month");

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        series.setName("Revenue");
        ResultSet rs = new BillDAO().getRevenueByMonth();
        while (rs.next()){
            String month = rs.getString("Month");
            double revenue = rs.getDouble("Revenue");
            series.getData().add(new XYChart.Data<>(month, revenue));
        }
        chart_Revenue.getData().add(series);
    }

    private ObservableList<Product> getSampleProductData() throws SQLException {
        ResultSet rs = new ProductDAO().getRevenueDetails();
        ObservableList<Product> topProdShow = FXCollections.observableArrayList();
        while (rs.next()){
            Product prod = new Product(
                    rs.getString("Name"),
                    rs.getInt("QuantitySold"),
                    rs.getDouble("TotalValueSold")
            );
            topProdShow.add(prod);
        }
        return topProdShow;
    }

    private void set_PaneStaff() throws Exception {
        VBox vbox = (VBox) pane_staff.lookup("#vbox_Staff");
        ResultSet rs = new EmployeeDAO().getTop5Employee();

        for(int i=1; i<=5; i++){
            HBox hbox = (HBox) vbox.lookup("#hb_Staff" + i);
            Label label = (Label) hbox.lookup("#lb_topStaff" + i);
            try {
                if (rs.next())
                    label.setText(rs.getString("Name"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void set_PaneCustomer() throws Exception {
        VBox vbox = (VBox) pane_Customer.lookup("#vbox_Cus");
        ResultSet rs = new CustomerDAO().getTop5Customer();
        for(int i=1; i<=5; i++){
            HBox hbox = (HBox) vbox.lookup("#hb_TopCus" + i);
            AnchorPane show = (AnchorPane) hbox.lookup("#show_Cus" + i);
            Label label = (Label) show.lookup("#lb_topCus" + i);
            try {
                if (rs.next())
                    label.setText(rs.getString("Name"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
