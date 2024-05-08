package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button btn_Switch;

    @FXML
    private AnchorPane loginScene;

    @FXML
    protected void onSwitchClick(ActionEvent event) {
        Node node = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/loginAD.fxml"));
            node = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loginScene.getChildren().setAll(node);
    }
}
