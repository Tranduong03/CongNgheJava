package Controller;

import DAO.EmployeeDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    @FXML
    private Label lbl_CurPage;
    @FXML
    private ImageView img_next;
    @FXML
    private ImageView img_prev;
    private int curPage = 1;
    @FXML
    private Button btn_NextPage;
    @FXML
    private Button btn_PrevPage;
    private int numOfPages;
    @FXML
    private AnchorPane emp_ShowBox;
    @FXML private ImageView btn_AddEmployee;
    @FXML private ChoiceBox choiceBox_Employee;

    @FXML private TextField txt_Search;
    @FXML private ImageView btn_Search;
    private int numSearchEmp = 0;
    private Boolean searchFlag = false;

    private String[] choice = {"A-Z Name", "Salary Desc", "HireDate Desc"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox_Employee.getItems().addAll(choice);
        int numOfEmp = new EmployeeDAO().getNumEmployees();
        System.out.println(numOfEmp);
        numOfPages = setNumOfPage(numOfEmp);
        setCurPage();
        try {
            empCurShow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        btn_AddEmployee.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                onAddEmployeeClicked();
            }
        });

        btn_Search.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    onSearchClicked();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    // Lấy số trang hiện tại áp đặt lại vào Label
    public void setCurPage() {
        lbl_CurPage.setText(String.valueOf(curPage));

        // Kiểm tra và cập nhật trạng thái của nút btn_NextPage và img_next
        if (curPage >= numOfPages) {
            btn_NextPage.setVisible(false);
            img_next.setVisible(false);
        } else {
            btn_NextPage.setVisible(true);
            img_next.setVisible(true);
        }

        // Kiểm tra và cập nhật trạng thái của nút btn_PrevPage và img_prev
        if (curPage <= 1) {
            btn_PrevPage.setVisible(false);
            img_prev.setVisible(false);
        } else {
            btn_PrevPage.setVisible(true);
            img_prev.setVisible(true);
        }

        System.out.println(curPage);
    }

    // Lấy số lượng trang
    public int setNumOfPage(int numOfEmp){
        if (numOfEmp % 8 == 0) {return numOfEmp/8;}
        else return numOfEmp / 8 +1;
    }

    // Xử lí sự kiện phím chuyển trang bị nhấn
    @FXML
    protected void onBtnNextPage(ActionEvent event){
        curPage+=1;
        setCurPage();
        clearCurShow();
        try {
            if (searchFlag == false) {
                empCurShow();
            } else {
                empSearchShow(txt_Search.getText());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onBtnPrevPage(ActionEvent event) {
        curPage-=1;
        setCurPage();
        clearCurShow();
        try {
            if (searchFlag == false) {
                empCurShow();
            } else {
                empSearchShow(txt_Search.getText());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected void empCurShow() throws SQLException {//
        ResultSet rs = null;
        try {
            rs = new EmployeeDAO().getAllEmpInPage(curPage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 8; i++) {
            String anchorPaneId = "#emp_ShowBox" + i;
            AnchorPane anchorPane = (AnchorPane) emp_ShowBox.lookup(anchorPaneId);
            if(rs==null) continue;

            try {
                if (rs.next()) {
                    Label name = (Label) anchorPane.lookup("#emp_name" + i);
                    Label hireDate = (Label) anchorPane.lookup("#emp_hireDate" + i);
                    Label salary = (Label) anchorPane.lookup("#emp_salary" + i);

                    anchorPane.setUserData(rs.getString("EmployeeID"));
                    name.setText(rs.getString("Name"));
                    hireDate.setText(rs.getDate("HireDate").toString());
                    salary.setText(String.valueOf(rs.getDouble("Salary")));

                    System.out.println(anchorPane.getUserData().toString() + " " + name.getText() + " " + hireDate.getText() + " " + salary.getText());
                } else {
                    System.out.println("No more data available.");
                    return;
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Error while processing ResultSet: " + ex.getMessage(), ex);
            }
        }
        if(rs!=null) rs.close();
    }

    protected void clearCurShow(){
        for (int i = 0; i < 8; i++){
            AnchorPane anchorPane = (AnchorPane) emp_ShowBox.lookup("#emp_ShowBox" + (i + 1));
            Label name = (Label) anchorPane.lookup("#emp_name" + (i + 1));
            Label hireDate = (Label) anchorPane.lookup("#emp_hireDate" + (i + 1));
            Label salary = (Label) anchorPane.lookup("#emp_salary" + (i + 1));

            anchorPane.setUserData("");
            name.setText("");
            hireDate.setText("");
            salary.setText("");
        }
    }

    protected void onAddEmployeeClicked(){
        Node node = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/View/addEmployee.fxml"));
            node = fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException();
        }
        emp_ShowBox.getChildren().setAll(node);
    }

    protected void empSearchShow(String searchName) throws SQLException {
        ResultSet rs = null;
        try {
            rs = new EmployeeDAO().getSearchedEmpInPage(curPage, searchName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (int i = 1; i <= 8; i++) {
            String anchorPaneId = "#emp_ShowBox" + i;
            AnchorPane anchorPane = (AnchorPane) emp_ShowBox.lookup(anchorPaneId);
            if(rs==null) continue;

            try {
                if (rs.next()) {
                    Label name = (Label) anchorPane.lookup("#emp_name" + i);
                    Label hireDate = (Label) anchorPane.lookup("#emp_hireDate" + i);
                    Label salary = (Label) anchorPane.lookup("#emp_salary" + i);

                    anchorPane.setUserData(rs.getString("EmployeeID"));
                    name.setText(rs.getString("Name"));
                    hireDate.setText(rs.getDate("HireDate").toString());
                    salary.setText(String.valueOf(rs.getDouble("Salary")));

                    System.out.println(anchorPane.getUserData().toString() + " " + name.getText() + " " + hireDate.getText() + " " + salary.getText());
                } else {
                    System.out.println("No more data available.");
                    return;
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Error while processing ResultSet: " + ex.getMessage(), ex);
            }
        }
        if(rs!=null) rs.close();
    }

    protected void onSearchClicked() throws Exception {
        String txtSearch = txt_Search.getText();
        searchFlag = true;
        curPage =1;
        numSearchEmp = new EmployeeDAO().getSearchedEmployee(txtSearch);
        numOfPages = setNumOfPage(numSearchEmp);
        System.out.println(numOfPages + " " + numSearchEmp + "\n");
        clearCurShow();
        empSearchShow(txtSearch);
        setCurPage();
    }
}
