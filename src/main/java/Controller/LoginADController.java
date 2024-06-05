package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginADController {
    @FXML
    private Button btn_Switch;
    @FXML private Button btn_ADLogin;
    @FXML private TextField txt_Account;
    @FXML private PasswordField txt_Password;

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


}
