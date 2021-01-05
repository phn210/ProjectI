package app.controller.home;

import app.controller.CommonController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import model.entity.Account;
import model.form.EmployeeDetailForm;
import service.home.HomeService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public static Account account;

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

    @FXML
    private Button revenueButton;

    private CommonController commonController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        homeService = new HomeService();
        try {
            employeeDetailForm = homeService.getEmployeeDetail(account);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void toHome(){

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
            mainPane.setBottom(commonController.getPane("/invoice/Invoices"));
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
