package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    @FXML private Button btn_Exit;
    @FXML private Button btn_Bill;
    @FXML private Label lblClock;
    public AnchorPane paneRight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up event handlers for buttons
        btn_Customer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                loadView("/View/customer.fxml");
            }
        });

        btn_Dashboard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadView("/View/Dashboard.fxml");
            }
        });

        btn_Employee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadView("/View/employee.fxml");
            }
        });

        btn_Product.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadView("/View/product.fxml");
            }
        });

        btn_Bill.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadView("/View/bill.fxml");
            }
        });

        // Set up the exit button handler for logout functionality
        btn_Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadLoginView(event);
            }
        });

        // Set up the clock
        startClock();

        // Load the default view (Dashboard)
        loadView("/View/Dashboard.fxml");
    }

    // Method to load the view into the paneRight
    private void loadView(String fxmlPath) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
        Node node = null;
        try {
            node = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        paneRight.getChildren().setAll(node);
    }

    // Method to load the login view
    private void loadLoginView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/loginAD.fxml"));
            Node node = loader.load();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            // Set the new scene with the login view
            stage.setScene(new Scene((AnchorPane) node));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to start the clock
    private void startClock() {
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

    // Method to run the clock
    public void runClock() {
        while (true) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    String time = LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss a"));
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
