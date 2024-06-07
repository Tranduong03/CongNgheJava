package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
        Scene scene;
        scene = new Scene(fxmlLoader.load(), 800, 650);
        primaryStage.setScene(scene);
        // set title
        primaryStage.setTitle("DK Convenience Store");
        // set farvicon
        Image icon = new Image(getClass().getResource("/ImageSource/DK.png").toExternalForm());
        primaryStage.getIcons().add(icon);
        // show
        primaryStage.show();
    }
}
