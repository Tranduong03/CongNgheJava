package Controller;

import DAO.BillDAO;
import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import DAO.ProductDAO;
import Model.Product;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    }

    private void set_PaneStaff() throws Exception {
        ResultSet rs = new EmployeeDAO().getTop5Employee();
        while (rs.next()) {
            System.out.print(rs.getString(1));
            System.out.println(rs.getDouble(2));
        }
    }

    private void set_PaneCustomer() throws Exception {
        ResultSet rs = new CustomerDAO().getTop5Customer();
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
    }
}
