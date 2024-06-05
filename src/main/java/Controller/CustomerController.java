package Controller;

import DAO.CustomerDAO;
import DAO.EmployeeDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerController  implements Initializable {
    @FXML private AnchorPane cus_ShowBox;
    @FXML private TextField txt_Search;
    @FXML private ImageView btn_Search;
    @FXML private Button btn_PrevPage;
    @FXML private ImageView img_next;
    @FXML private Button btn_NextPage;
    @FXML private ImageView img_prev;
    @FXML private ChoiceBox cb_Customer;
    @FXML private Label lbl_CurPage;

    private Boolean searchFlag = false;
    private Boolean sortFlag = false;
    private int curPage = 1;
    private int numOfPages;
    private int numSearchCus = 0;
    private String[] choice = {"A-Z Name", "No Sort"};

    @Override public void initialize(URL location, ResourceBundle resources) {
        int numOfCus = new CustomerDAO().getNumCustomers();
        cb_Customer.getItems().addAll(choice);
        cb_Customer.setValue("No Sort");
        numOfPages = setNumOfPage(numOfCus);

        setCurPage();

        try {
            cusCurShow();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        btn_Search.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                cb_Customer.setValue("No Sort");
                try {
                    onSearchClicked();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

        cb_Customer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                onChoiceBoxClicked();
            }
        });
    }

    private void setCurPage() {
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

    private int setNumOfPage(int numOfEmp){
        if (numOfEmp % 8 == 0) {return numOfEmp/8;}
        else return numOfEmp / 8 +1;
    }

    private void showCurrent(ResultSet rs) throws SQLException {
        boolean hasData = true;

        for (int i = 1; i <= 8; i++) {
            String anchorPaneId = "#cus_ShowBox" + i;
            AnchorPane anchorPane = (AnchorPane) cus_ShowBox.lookup(anchorPaneId);

            try {
                if (hasData && rs != null && rs.next()) {
                    anchorPane.setVisible(true);

                    Label name = (Label) anchorPane.lookup("#cus_name" + i);
                    Label gender = (Label) anchorPane.lookup("#cus_Gender" + i);
                    Label phone = (Label) anchorPane.lookup("#cus_Phone" + i);

                    anchorPane.setUserData(rs.getString("CustomerID"));
                    name.setText(rs.getString("Name"));
                    gender.setText(rs.getBoolean("Gender") ? "Female" : "Male");
                    phone.setText(rs.getString("Phone"));

                    System.out.println(anchorPane.getUserData().toString() + " " + name.getText() + " " + gender.getText() + " " + phone.getText());
                } else {
                    hasData = false;
                    anchorPane.setVisible(false);
                    System.out.println("No more data available for cus_ShowBox" + i);
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Error while processing ResultSet: " + ex.getMessage(), ex);
            }
        }

        if (rs != null) rs.close();
    }


    protected void clearCurShow(){
        for (int i = 0; i < 8; i++){
            AnchorPane anchorPane = (AnchorPane) cus_ShowBox.lookup("#cus_ShowBox" + (i + 1));
            Label name = (Label) anchorPane.lookup("#cus_name" + (i + 1));
            Label gender = (Label) anchorPane.lookup("#cus_Gender" + (i + 1));
            Label phone = (Label) anchorPane.lookup("#cus_Phone" + (i + 1));

            anchorPane.setUserData("");
            name.setText("");
            gender.setText("");
            phone.setText("");
        }
    }

    protected void cusCurShow() throws SQLException {
        ResultSet rs = null;
        try {
            rs = new CustomerDAO().getAllCusInPage(curPage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        showCurrent(rs);
    }

    @FXML protected void onBtnPrevPage() {
        curPage-=1;
        setCurPage();
        clearCurShow();
        try {
            if (searchFlag == false && sortFlag == false) {
                cusCurShow();
            } else if(searchFlag) {
                cusSearchShow(txt_Search.getText());
            } else if(sortFlag){
                if(cb_Customer.getSelectionModel().getSelectedItem().toString().equals("A-Z Name")){
                    cusCurShowSortedByName();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML protected void onBtnNextPage() {
        curPage+=1;
        setCurPage();
        clearCurShow();
        try {
            if (searchFlag == false && sortFlag == false) {
                cusCurShow();
            } else if(searchFlag) {
                cusSearchShow(txt_Search.getText());
            } else if(sortFlag){
                if(cb_Customer.getSelectionModel().getSelectedItem().toString().equals("A-Z Name")){
                    cusCurShowSortedByName();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void cusSearchShow(String searchName) throws SQLException {
        ResultSet rs = null;
        try {
            rs = new CustomerDAO().getSearchedCusInPage(curPage, searchName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        showCurrent(rs);
    }

    private void cusCurShowSortedByName() throws Exception {
        ResultSet rs = new CustomerDAO().name_getSortCusInPage(curPage);
        // Process the result set and update the UI
        showCurrent(rs);
    }

    protected void onSearchClicked() throws Exception {
        String txtSearch = txt_Search.getText();
        searchFlag = true;
        curPage =1;
        numSearchCus = new CustomerDAO().getSearchedListCustomer(txtSearch);
        numOfPages = setNumOfPage(numSearchCus);
        System.out.println(numOfPages + " " + numSearchCus + "\n");
        clearCurShow();
        cusSearchShow(txtSearch);
        setCurPage();
    }

    protected void onChoiceBoxClicked(){
        String selectedChoice = (String) cb_Customer.getSelectionModel().getSelectedItem();
        if (selectedChoice != null) {
            if (selectedChoice.equals("A-Z Name")) {
                sortFlag = true;
                try {
                    cusCurShowSortedByName();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else sortFlag = false;
        }
    }
}
