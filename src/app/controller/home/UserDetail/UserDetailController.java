package app.controller.home.UserDetail;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entity.Account;
import model.form.EmployeeDetailForm;
import service.home.HomeService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserDetailController implements Initializable {

    public static Account account;

    private EmployeeDetailForm employeeDetailForm;

    private HomeService homeService;

    @FXML
    Label nameLabel;

    @FXML
    TextField idTextField;

    @FXML
    TextField branchNameTextField;

    @FXML
    TextField citizenIdTextField;

    @FXML
    TextField roleTextField;

    @FXML
    TextField dobTextField;

    @FXML
    TextField startDateTextField;

    @FXML
    TextField insuranceIdTextField;

    @FXML
    TextField salaryLevelTextField;

    @FXML
    TextField phoneTextField;

    @FXML
    TextField addressTextField;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homeService = new HomeService();
        try {
            employeeDetailForm = homeService.getEmployeeDetail(account);
            nameLabel.setText(employeeDetailForm.getName());
            idTextField.setText(String.valueOf(employeeDetailForm.getEmployeeId()));
            branchNameTextField.setText(employeeDetailForm.getBranchName());
            citizenIdTextField.setText(employeeDetailForm.getCitizenId());
            String roleName = "";
            int role = employeeDetailForm.getRole();
            if(role == 1){
                roleName = "Quản lí";
            }else if(role == 2){
                roleName = "Nhân viên bán hàng";
            }else{
                roleName = "Nhân viên kỹ thuật";
            }
            roleTextField.setText(roleName);
            dobTextField.setText(String.valueOf(employeeDetailForm.getDob()));
            startDateTextField.setText(String.valueOf(employeeDetailForm.getStartDate()));
            phoneTextField.setText(String.valueOf(employeeDetailForm.getPhone()));
            insuranceIdTextField.setText(employeeDetailForm.getInsuranceId());
            salaryLevelTextField.setText(String.valueOf(employeeDetailForm.getSalaryLevel()));
            addressTextField.setText(employeeDetailForm.getAddress());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
