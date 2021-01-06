package app.controller.employee;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.form.EmployeeDetailForm;
import service.home.HomeService;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeDetailController implements Initializable {

    public static EmployeeDetailForm employeeDetailForm;

    private HomeService homeService;

    @FXML
    Label nameLabel;

    @FXML
    TextField idTextField;

    @FXML
    ComboBox<String> branchComboBox;

    ObservableList<String> branchComboBoxList;

    @FXML
    TextField citizenIdTextField;

    @FXML
    ComboBox<String> roleComboBox;

    ObservableList<String> roleComboBoxList;

    @FXML
    DatePicker dobDatePicker;

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

    @FXML
    TextField usernameTextField;

    @FXML
    TextField passwordTextField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
