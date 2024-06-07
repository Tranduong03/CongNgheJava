package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML private Button btn_Employee;
    @FXML private Button btn_Customer;
    @FXML private Button btn_Product;
    @FXML private Button btn_Dashboard;
    @FXML private Label lblClock;
    public AnchorPane paneRight;
    @FXML private Button btn_Bill;
    @FXML private Label lb_Name;
    @FXML private Button btn_Exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/dashboard.fxml"));

        Node node = null;
        try {
            node = loader.load();
            paneRight.getChildren().add(0, node);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        btn_Dashboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/dashboard.fxml"));
                Node node = null;
                try {
                    node = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                paneRight.getChildren().setAll(node);
            }
        });

        btn_Bill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/bill.fxml"));
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

        btn_Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                changeScene(event);
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
    public void changeScene(ActionEvent event) {
        Parent root = null;

        try {
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource("/View/loginAD.fxml"));
            root = loader.load();
            LoginADController loginADController = loader.getController();

        }catch (IOException e){
            e.printStackTrace();
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Home");
        stage.setMaximized(true);
        stage.setFullScreen(true);
        stage.setScene(new Scene(root,800, 650));
        stage.setResizable(true);
        stage.centerOnScreen();
        stage.show();
    }
}
