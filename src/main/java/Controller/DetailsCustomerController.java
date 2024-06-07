package Controller;

import DAO.EmployeeDAO;
import DAO.SQLOperation;
import Model.Customer;
import Model.Employee;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DetailsCustomerController implements Initializable {
    @FXML
    private Label lb_Account;
    @FXML private TextField txt_Name;
    @FXML private ChoiceBox cb_Gender;
    @FXML private TextField txt_Phone;
    @FXML private TextField txt_Email;
    @FXML private DatePicker dp_Birthdate;
    @FXML private Button btn_SaveCusInfo;
    @FXML private Button btn_DeleteCus;
    @FXML private Label lbl_Error;
    @FXML private Label lbl_Complete;
    @FXML private TextField txt_Address;
    @FXML private ImageView img_save;
    @FXML private ImageView img_del;

    private ResultSet rs;
    private int id;
    private Customer cus = new Customer();
    private Customer customer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_Error.setVisible(false);
        lbl_Complete.setVisible(false);
        cb_Gender.getItems().addAll("Male", "Female");
        btn_SaveCusInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    onSaveClicked();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btn_DeleteCus.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                try {
                    onDeleteClicked();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        });


    }

    protected void setResultSet(ResultSet resultSet) {
        this.rs = resultSet;
        try {
            if (rs.next()) {
                id = rs.getInt(1);
                cus.setName(rs.getString(2)) ;
                cus.setGender(rs.getBoolean(3));
                cus.setBirthdate(rs.getDate(4).toLocalDate());
                cus.setPhone(rs.getString(5));
                cus.setEmail(rs.getString(6));
                cus.setAddress(rs.getString(10));

                txt_Name.setText(cus.getName());
                cb_Gender.setValue(cus.getGender() ? "Female" : "Male");
                txt_Phone.setText(cus.getPhone());
                txt_Email.setText(cus.getEmail());
                dp_Birthdate.setValue(cus.getBirthdate());
                txt_Address.setText(cus.getAddress());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void onDeleteClicked() {
    }

    private void onSaveClicked() {
            String name = txt_Name.getText();
            String email = txt_Email.getText();
            String phone = txt_Phone.getText();
            LocalDate birthdate = dp_Birthdate.getValue();
            int gender = cb_Gender.getSelectionModel().getSelectedItem().equals("Female") ? 1 : 0;
            String address = txt_Address.getText();

            Boolean complete = new SQLOperation().SetDatabase("String sql = \"INSERT INTO Customer (Name, Address, Phone, Gender, Email, BirthDate) " +
                    "VALUES ('" + name + "', '" + address + "', '" + phone + "', " + gender + ", '" + email + "', '" + birthdate + "');",
                    "Customer details");

//            if (complete) {
//                lbl_Complete.setVisible(true);
//                lbl_Complete.setText("Employee Saved Successfully");
//                PauseTransition pause = new PauseTransition(Duration.seconds(3));
//                pause.setOnFinished(e -> lbl_Complete.setVisible(false));
//                pause.play();
//                setResultSet(new EmployeeDAO().getCustomer(id));
//            }
    }
}
