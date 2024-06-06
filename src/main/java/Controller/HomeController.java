package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML private Button btn_Employee;
    @FXML private Button btn_Customer;
    @FXML private Button btn_Product;
    @FXML private Label lblClock;
    public AnchorPane paneRight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_Customer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/customer.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                paneRight.getChildren().setAll(node);
            }
        });

        btn_Employee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/View/employee.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                paneRight.getChildren().setAll(node);
            }
        });

        btn_Product.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("/View/product.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                paneRight.getChildren().setAll(node);
            }
        });

        //thiết lập đồng hồ hiển thị thời gian trên máy
        Runnable clock = new Runnable() {
            @Override
            public void run() {
                runClock();
            }
        };
        Thread newClock = new Thread(clock);
        newClock.setDaemon(true);
        newClock.start();
    }
    //Setting Clock within a new Thread
    public void runClock() {
        while (true) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Getting the system time in a string
                    String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
                    // Setting the time in a label
                    lblClock.setText(time);
                }
            });

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
