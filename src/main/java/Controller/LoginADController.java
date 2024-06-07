package Controller;

import Model.Admin;
import Model.Employee;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static DAO.AdminDAO.checkAdminAccount;

public class LoginADController implements Initializable {
    @FXML
    private Button btn_Switch;
    @FXML private Button btn_ADLogin;
    @FXML private TextField txt_Account;
    @FXML private PasswordField txt_Password;
    @FXML
    private Label lb_AlertAccount;
    @FXML
    private AnchorPane loginADScene;

    @FXML
    protected void onSwitchClick(ActionEvent event) {
        Node node = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
            node = fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException();
        }
        loginADScene.getChildren().setAll(node);
    }

    @FXML protected void onADLoginClick(ActionEvent event) {
        String acc = txt_Account.getText();
        String pass = txt_Password.getText();
//        pass = mdConverter(pass);
        Admin admin = checkAdminAccount(acc, pass);
        changeScene(event, admin);
    }

    public void initialize(URL url, ResourceBundle rb) {
        // Ẩn Label khi Scene được load
        lb_AlertAccount.setVisible(false);
    }

    public void changeScene(ActionEvent event, Admin admin){
        Parent root = null;
        if(admin!=null){
            try {
                FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/View/home.fxml"));
                root = loader.load();
//                HomeController homeController = loader.getController();
                // truyền dữ liệu của emp cho class HomeEmployeeController

            }catch (IOException e){
                e.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Home");
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setScene(new Scene(root,1200, 800));
            stage.setResizable(true);
            stage.centerOnScreen();
            stage.show();
        } else {
            alertLabel();
            return;
        }
    }
    protected void alertLabel(){
        lb_AlertAccount.setVisible(true);
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e -> lb_AlertAccount.setVisible(false));
        pause.play();
    }

    public void changeScene(ActionEvent event){
        Parent root = null;

            try {
                FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/View/home.fxml"));
                root = loader.load();
                HomeEmployeeController homeEmployeeController = loader.getController();

            }catch (IOException e){
                e.printStackTrace();
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Home");
            stage.setMaximized(true);
            stage.setFullScreen(true);
            stage.setScene(new Scene(root,1200, 800));
            stage.setResizable(true);
            stage.centerOnScreen();
            stage.show();

    }
}
