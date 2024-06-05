package Controller;

import DAO.EmployeeDAO;
import DAO.SQLOperation;
import Model.Employee;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jetbrains.annotations.Nullable;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailsEmployeeController implements Initializable {
    @FXML private Label lb_Account;
    @FXML private TextField txt_Name;
    @FXML private ChoiceBox cb_Gender;
    @FXML private TextField txt_Phone;
    @FXML private TextField txt_Email;
    @FXML private TextField txt_Salary;
    @FXML private DatePicker dp_Birthdate;
    @FXML private Label lb_HireDate;
    @FXML private Button btn_SaveEmpInfo;
    @FXML private Button btn_DeleteEmp;
    @FXML private Label lbl_Error;
    @FXML private Label lbl_Complete;
    @FXML private TextField txt_Address;

    private ResultSet rs;
    private int id;
    private Employee emp = new Employee();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_Error.setVisible(false);
        lbl_Complete.setVisible(false);
        cb_Gender.getItems().addAll("Male", "Female");

        btn_SaveEmpInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    onSaveClicked();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        btn_DeleteEmp.setOnAction(new EventHandler() {
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
                emp.setName(rs.getString(2)) ;
                emp.setGender(rs.getBoolean(3));
                emp.setBirthDate(rs.getDate(4).toLocalDate());
                emp.setPhone(rs.getString(5));
                emp.setEmail(rs.getString(6));
                emp.setSalary(rs.getDouble(7));
                emp.setHireDate(rs.getDate(8).toLocalDate());
                emp.setAccount(rs.getString(9));
                emp.setAddress(rs.getString(10));

                txt_Name.setText(emp.getName());
                cb_Gender.setValue(emp.getGender() ? "Female" : "Male");
                txt_Phone.setText(emp.getPhone());
                txt_Email.setText(emp.getEmail());
                txt_Salary.setText(emp.getSalary()+"");
                lb_Account.setText("Account: "+emp.getAccount());
                dp_Birthdate.setValue(emp.getBirthDate());
                lb_HireDate.setText(""+ emp.getHireDate());
                txt_Address.setText(emp.getAddress());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Boolean validate() {
        String name = txt_Name.getText();
        String email = txt_Email.getText();
        String phone = txt_Phone.getText();
        String salary = txt_Salary.getText();
        String birth = null;
        try {
            birth = dp_Birthdate.getValue().toString();
        } catch (NullPointerException ex) {
            lbl_Error.setVisible(true);
            lbl_Error.setText("The field(s) cannot be empty.");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> lbl_Error.setVisible(false));
            pause.play();
            return false;
        }

        if (name == null || email.isEmpty() || phone.isEmpty() || salary.isEmpty() || birth.isEmpty() || cb_Gender.getValue() == null) {
            lbl_Error.setVisible(true);
            lbl_Error.setText("The field(s) cannot be empty.");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> lbl_Error.setVisible(false));
            pause.play();
            return false;
        }
        // Name validation (6-30 characters, A-Z, a-z, 0-9, and space)
        if (!name.matches("[A-Za-z0-9 ]{6,30}")) {
            lbl_Error.setVisible(true);
            lbl_Error.setText("Please enter a valid name. Use character in (a-z, A-Z, 0-9)");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> lbl_Error.setVisible(false));
            pause.play();
            return false;
        }


        // Email validation (10-50 characters, pattern %_@%_._%)
        Pattern emailPattern = Pattern.compile("^[\\w.%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
        Matcher emailMatcher = emailPattern.matcher(email);
        if (email.length() < 8 || email.length() > 50 || !emailMatcher.matches()) {

            lbl_Error.setVisible(true);
            lbl_Error.setText("Please enter a valid email.");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> lbl_Error.setVisible(false));
            pause.play();
            return false;
        }
        else {
            if (email.equals(emp.getEmail())) {
                ;
            } else {
                ResultSet checkrs = new SQLOperation().GetDatabase("SELECT count(*) FROM Employee WHERE Email = '" + email + "' ");
                try {
                    if (checkrs.next()) {
                        int count = rs.getInt(1);
                        if (count > 0) {
                            lbl_Error.setVisible(true);
                            lbl_Error.setText("Email already exists.");
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(e -> lbl_Error.setVisible(false));
                            pause.play();
                            return false;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        // Salary validation (salary > 0)
        try {
            double salaryValue = Double.parseDouble(salary);
            if (salaryValue <= 0) {
                lbl_Error.setVisible(true);
                lbl_Error.setText("Please enter a valid salary.");
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> lbl_Error.setVisible(false));
                pause.play();
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        // Birth date validation (Birth < 1/1/1964)
        LocalDate birthDate = LocalDate.parse(birth, DateTimeFormatter.ISO_DATE);
        LocalDate cutoffDate = LocalDate.of(1964, 1, 1);
        LocalDate _18Years = LocalDate.now().minusYears(18);
        if (birthDate.isBefore(cutoffDate)) {
            lbl_Error.setVisible(true);
            lbl_Error.setText("Please enter a valid birth date.");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> lbl_Error.setVisible(false));
            pause.play();
            return false;
        }
        else if (birthDate.isAfter(_18Years))
        {
            lbl_Error.setVisible(true);
            lbl_Error.setText("You must be over 18 years old to work here");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> lbl_Error.setVisible(false));
            pause.play();
            return false;
        }


        // Phone number validation (10 or 11 digits, starts with 0)
        if (!phone.matches("0[0-9]{9,10}")) {

            lbl_Error.setVisible(true);
            lbl_Error.setText("Please enter a valid phone number.");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> lbl_Error.setVisible(false));
            pause.play();
            return false;
        }
        else {
            if (phone.equals(emp.getPhone())) {
            } else {
                ResultSet checkrs = new SQLOperation().GetDatabase("SELECT count(*) FROM Employee WHERE Phone = '" + phone + "' ");
                try {
                    if (checkrs.next()) {
                        int count = rs.getInt(1);
                        if (count > 0) {
                            lbl_Error.setVisible(true);
                            lbl_Error.setText("Phone number already exists.");
                            PauseTransition pause = new PauseTransition(Duration.seconds(3));
                            pause.setOnFinished(e -> lbl_Error.setVisible(false));
                            pause.play();
                            return false;
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return true;
    }

    protected void onSaveClicked() throws Exception {
        if (validate()) {
            String name = txt_Name.getText();
            String email = txt_Email.getText();
            String phone = txt_Phone.getText();
            Double salary = Double.parseDouble(txt_Salary.getText());
            LocalDate birthdate = dp_Birthdate.getValue();
            int gender = cb_Gender.getSelectionModel().getSelectedItem().equals("Female") ? 1 : 0;
            String address = txt_Address.getText();

            Boolean complete = new SQLOperation().SetDatabase(
                    "UPDATE Employee " +
                            "SET Name = '" + name + "', Email ='" + email + "', Gender = '" + gender + "', Phone = '" + phone + "'," +
                            " Birthdate = '" + birthdate + "', Salary = '" + salary + "', Address = '"+ address +"' " +
                            "WHERE EmployeeID = '" + id + "'",
                    "Account fix completed");

            if (complete) {
                lbl_Complete.setVisible(true);
                lbl_Complete.setText("Employee Saved Successfully");
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> lbl_Complete.setVisible(false));
                pause.play();
                setResultSet(new EmployeeDAO().getEmployee(id));
            }
        }
    }

    protected void onDeleteClicked() throws Exception {
        new EmployeeDAO().deleteEmployee(id);

        Stage stage = (Stage) btn_DeleteEmp.getScene().getWindow();
        stage.close();
    }
}
