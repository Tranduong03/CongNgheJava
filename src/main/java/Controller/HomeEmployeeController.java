package Controller;

import Model.Employee;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class HomeEmployeeController {
    @FXML private Circle details;
    @FXML private Button btn_Exit;
    @FXML private Label lblClock;
    @FXML private Label lb_Name;

    private Employee employee;

    @FXML
    public void initialize() {
        /*
        * Thêm event handler cho details
        * Tạo cửa sổ hiển thị chi tiết employee
        * Hiển thị thông tin của tài khoản đăng nhập
        *  */
        details.setOnMouseClicked(event -> {
            try {
                // Tải detailsEmployee.fxml
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/detailsEmployee.fxml"));
                Stage primaryStage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 700, 700);

                // Lấy controller từ FXMLLoader và truyền ResultSet vào DetailsEmployeeController
                DetailsEmployeeController controller = fxmlLoader.getController();
                controller.setEmployeeFromLogin(employee);

                // Cài đặt các thuộc tính khác cho cửa sổ mới
                primaryStage.setScene(scene);
                primaryStage.setTitle("Employee Detail");
                Image icon = new Image(getClass().getResource("/ImageSource/farvicon.png").toExternalForm());
                primaryStage.getIcons().add(icon);
                primaryStage.centerOnScreen();

                // Hiển thị cửa sổ mới
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Could not load the Employee page.");
            }
        });
        // Thêm event cho Exit
        btn_Exit.setOnMouseClicked(event -> {
            // Lấy stage hiện tại từ button
            Stage stage = (Stage) btn_Exit.getScene().getWindow();
            stage.close();
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
    } // end initialize

    /*
    * lấy dữ liệu từ của Employee khi đăng nhập từ LoginController
    * */
    public void setEmployee(Employee emp) {
        this.employee = emp;
        // Cập nhật giao diện với thông tin employee
        if (lb_Name != null && employee != null) {
            lb_Name.setText(employee.getName());
        }
    }
    // hiển thị hộp thoại thông báo nếu lỗi
    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
