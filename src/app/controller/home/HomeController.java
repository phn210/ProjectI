package app.controller.home;

import app.controller.CommonController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.entity.Account;
import model.form.EmployeeDetailForm;
import service.home.HomeService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public static Account account;

    public static Pane userDetailPane;

    private HomeService homeService;

    private EmployeeDetailForm employeeDetailForm;

    @FXML
    private BorderPane mainPane;

    @FXML
    private Button productButton;

    @FXML
    private Button customerButton;

    @FXML
    private Button employeeButton;

    @FXML
    private Button dutyRosterButton;

    @FXML
    private Button invoiceButton;


    private CommonController commonController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        homeService = new HomeService();
        mainPane.setBottom(userDetailPane);

        try {
            employeeDetailForm = homeService.getEmployeeDetail(account);
            int role = employeeDetailForm.getRole();
            if(role == 2){
                dutyRosterButton.setVisible(false);
                employeeButton.setVisible(false);
            }else if(role == 3){
                dutyRosterButton.setVisible(false);
                employeeButton.setVisible(false);
                productButton.setVisible(false);
                customerButton.setVisible(false);
                invoiceButton.setVisible(false);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void toHome(){
        Pane userDetailPane = null;
        try {
            userDetailPane = commonController.getPane("home/UserDetail/UserDetail.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainPane.setBottom(userDetailPane);
    }

    public void toProductTab(){
        try {
            mainPane.setBottom(commonController.getPane("/product/Products.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toCustomerTab(){
        try {
            mainPane.setBottom(commonController.getPane("/customer/Customer.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toEmployeeTab(){
        try {
            mainPane.setBottom(commonController.getPane("/employee/Employee.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toDutyTab(){
        try {
            mainPane.setBottom(commonController.getPane("/duty_roster/DutyRoster.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toInvoiceTab(){
        try {
            mainPane.setBottom(commonController.getPane("/invoice/Invoices.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toRevenueTab(){
        try {
            mainPane.setBottom(commonController.getPane("/revenue/Revenue.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
