package Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class demo extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo VBox chính
        VBox mainLayout = new VBox();

        // Tạo HBox cho thanh header-bar
        HBox headerBar = new HBox();
        headerBar.setStyle("-fx-padding: 10px; -fx-spacing: 10px;");

        // Tạo VBox cho phần nội dung
        VBox contentBox = new VBox();
        contentBox.setStyle("-fx-padding: 20px; -fx-background-color: #FFFFFF;");

        // Tạo các nút trên thanh header-bar
        Button redButton = new Button("Red");
        Button greenButton = new Button("Green");
        Button blueButton = new Button("Blue");

        // Thêm các nút vào headerBar
        headerBar.getChildren().addAll(redButton, greenButton, blueButton);

        // Thiết lập sự kiện khi nhấn vào các nút
        redButton.setOnAction(event -> contentBox.setStyle("-fx-background-color: red;"));
        greenButton.setOnAction(event -> contentBox.setStyle("-fx-background-color: green;"));
        blueButton.setOnAction(event -> contentBox.setStyle("-fx-background-color: blue;"));

        // Thêm headerBar và contentBox vào mainLayout
        mainLayout.getChildren().addAll(headerBar, contentBox);

        // Tạo Scene và thiết lập Stage
        Scene scene = new Scene(mainLayout, 400, 300);
        primaryStage.setTitle("JavaFX Header Bar Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
