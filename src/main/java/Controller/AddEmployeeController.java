package Controller;

import DAO.SQLOperation;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEmployeeController implements Initializable {
    @FXML private TextField txt_Name;
    @FXML private TextField txt_Email;
    @FXML private TextField txt_Phone;
    @FXML private TextField txt_Account;
    @FXML private TextField txt_Password;
    @FXML private TextField txt_Salary;
    @FXML private ImageView btn_back;
    @FXML private Button btnSaveNewEmp;
    @FXML private ChoiceBox choice_Gender;
    @FXML private DatePicker daypick_Birth;
    @FXML private Label lbl_Complete;
    @FXML private Label lbl_Error;
    @FXML private AnchorPane addEmp_Box;

    private String[] Gender= {"Male", "Female"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choice_Gender.getItems().addAll(Gender);
        lbl_Complete.setVisible(false);
        lbl_Error.setVisible(false);
        btn_back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                BacktoEmployee();
            }
        });
    }

    public Boolean validate() {
        String name = txt_Name.getText();
        String email = txt_Email.getText();
        String phone = txt_Phone.getText();
        String account = txt_Account.getText();
        String password = txt_Password.getText();
        String salary = txt_Salary.getText();
        String gender = choice_Gender.getSelectionModel().getSelectedItem().toString();
        String birth = daypick_Birth.getValue().toString();

        // Name validation (6-30 characters, A-Z, a-z, 0-9, and space)
        if (!name.matches("[A-Za-z0-9 ]{6,30}")) {
            lbl_Error.setVisible(true);
            lbl_Error.setText("Please enter a valid name. Use character in (a-z, A-Z, 0-9)");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> lbl_Error.setVisible(false));
            pause.play();
            return false;
        }

        // Password validation (<= 6 characters, A-Z, a-z, 0-9)
        if (!password.matches("[A-Za-z0-9]{1,30}")) {
            lbl_Error.setVisible(true);
            lbl_Error.setText("Please enter a valid password. Use character in (a-z, A-Z, 0-9)");
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
            ResultSet rs = new SQLOperation().GetDatabase("SELECT count(*) FROM Employee WHERE Email = '"+ email +"' ");
            try {
                if(rs.next()){
                    int count = rs.getInt(1);
                    System.out.println("So luong :"+count);
                    if(count > 0){
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
            ResultSet rs = new SQLOperation().GetDatabase("SELECT count(*) FROM Employee WHERE Phone = '"+ phone +"' ");
            try {
                if(rs.next()){
                    int count = rs.getInt(1);
                    if(count > 0){
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

        // Account validation (6-30 characters, A-Z, a-z, 0-9)
        if (!account.matches("[A-Za-z0-9]{6,30}")) {

            lbl_Error.setVisible(true);
            lbl_Error.setText("Please enter a valid account.(6-30 characters, A-Z, a-z, 0-9)");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> lbl_Error.setVisible(false));
            pause.play();
            return false;
        }
        else {
            ResultSet rs = new SQLOperation().GetDatabase("SELECT count(*) FROM Employee WHERE Account = '"+ account +"' ");
            try {
                if(rs.next()){
                    int count = rs.getInt(1);
                    if(count > 0){
                        lbl_Error.setVisible(true);
                        lbl_Error.setText("Account already exists.");
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

        return true;
    }

    @FXML
    protected void onSaveClicked(ActionEvent event) {
        String name = txt_Name.getText();
        String email = txt_Email.getText();
        String phone = txt_Phone.getText();
        String account = txt_Account.getText();
        String password = txt_Password.getText();
        String salary = txt_Salary.getText();
        String choiceGender = choice_Gender.getSelectionModel().getSelectedItem().toString();
        int gender = 0;
        if (choiceGender.equals("Female")) {gender = 1;}
        String birth = daypick_Birth.getValue().toString();
        LocalDate birthDate = LocalDate.parse(birth, DateTimeFormatter.ISO_DATE);

        Boolean check = validate();

        if(check) {
            Boolean Complete = new SQLOperation().SetDatabase(
                    "INSERT INTO Employee(Name, Email, Phone, Account, Password, Salary, Gender, Birthdate, HireDate)" +
                            "VALUES ('"+ name +"', '"+ email +"', '"+ phone +"', '"+ account +"', '"+ password +"', " +
                            "'"+ salary +"', '"+ gender +"', '"+ birthDate +"','"+ LocalDate.now() +"');",
                    "Account add completed");
            if(Complete) {
                lbl_Complete.setVisible(true);
                lbl_Complete.setText("Employee Added Successfully");
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(e -> lbl_Complete.setVisible(false));
                pause.play();
                BacktoEmployee();
            }
        }

    }

    private void BacktoEmployee() {
        Node node = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/employee.fxml"));
            node = fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException();
        }
        addEmp_Box.getChildren().setAll(node);
    }


}
