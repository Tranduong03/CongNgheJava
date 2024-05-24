package Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class demo extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Main Window");

        // Tạo pane gốc với kích thước 500x500
        Pane rootPane = new Pane();
        rootPane.setPrefSize(500, 500);

        // Tạo nút "Do you love me?"
        Button mainButton = new Button("Do you love me?");
        mainButton.setLayoutX(200);
        mainButton.setLayoutY(200);
        mainButton.setOnAction(e -> showOverlay(rootPane));

        rootPane.getChildren().add(mainButton);

        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showOverlay(Pane rootPane) {
        // Tạo pane đè lên với kích thước 300x300
        Pane overlayPane = new Pane();
        overlayPane.setPrefSize(300, 300);
        overlayPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.75); -fx-background-radius: 10;");

        // Đặt vị trí của pane đè lên
        overlayPane.setLayoutX(100);
        overlayPane.setLayoutY(100);

        // Tạo hai nút "Yes" và "No"
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setLayoutX(100);
        yesButton.setLayoutY(100);
        noButton.setLayoutX(100);
        noButton.setLayoutY(150);

        yesButton.setOnAction(e -> {
            System.out.println("You clicked Yes!");
            rootPane.getChildren().remove(overlayPane);
        });

        noButton.setOnAction(e -> {
            System.out.println("You clicked No!");
            rootPane.getChildren().remove(overlayPane);
        });

        overlayPane.getChildren().addAll(yesButton, noButton);
        rootPane.getChildren().add(overlayPane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
