package app.controller.employee;

import app.controller.CommonController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.entity.Account;
import model.entity.Branch;
import model.entity.Employee;
import model.form.EmployeeDetailForm;
import service.employee.EmployeeService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeDetailController implements Initializable {

    public static EmployeeDetailForm employeeDetailForm;

    EmployeeService employeeService;

    ArrayList<Branch> branchArrayList;

    CommonController commonController;

    @FXML
    Label nameLabel;

    @FXML
    TextField nameTextField;

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
    ComboBox<String> workingComboBox;

    ObservableList<String> workingComboBoxList;

    @FXML
    Button informationButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        employeeService = new EmployeeService();
        commonController = new CommonController();
        roleComboBoxList = FXCollections.observableArrayList("Quản lí", "Nhân viên bán hàng", "Nhân viên kĩ thuật");
        roleComboBox.setItems(roleComboBoxList);
        workingComboBoxList = FXCollections.observableArrayList("Đang đi làm", "Đã nghỉ việc");
        workingComboBox.setItems(workingComboBoxList);
        try {
            branchArrayList = employeeService.getAllBranch();
            branchComboBoxList = FXCollections.observableArrayList(employeeService.getAllBranchName());
            branchComboBox.setItems(branchComboBoxList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        nameTextField.setText(employeeDetailForm.getName());
        nameLabel.textProperty().bindBidirectional(nameTextField.textProperty());
        idTextField.setText(String.valueOf(employeeDetailForm.getEmployeeId()));
        branchComboBox.setValue(employeeDetailForm.getBranchName());
        citizenIdTextField.setText(employeeDetailForm.getCitizenId());
        String roleName = "";
        int role = employeeDetailForm.getRole();
        if (role == 1) {
            roleName = "Quản lí";
        } else if (role == 2) {
            roleName = "Nhân viên bán hàng";
        } else {
            roleName = "Nhân viên kỹ thuật";
        }
        roleComboBox.setValue(roleName);
        dobDatePicker.setConverter(commonController.getConverter());
        if (employeeDetailForm.getDob() != null) {
            Date date = employeeDetailForm.getDob();
            dobDatePicker.setValue(LocalDate.parse(date.toString()));
        } else {
            dobDatePicker.setValue(null);
        }
        startDateTextField.setText(String.valueOf(employeeDetailForm.getStartDate()));
        phoneTextField.setText(String.valueOf(employeeDetailForm.getPhone()));
        insuranceIdTextField.setText(employeeDetailForm.getInsuranceId());
        salaryLevelTextField.setText(String.valueOf(employeeDetailForm.getSalaryLevel()));
        salaryLevelTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.isEmpty()) {
                    Double.parseDouble(newValue);
                }
            } catch (NumberFormatException ex) {
                commonController.resultNoti(false, "Mức lương phải là số");
                salaryLevelTextField.setText("");
            }
        });
        addressTextField.setText(employeeDetailForm.getAddress());
        if (employeeDetailForm.isWorking()) {
            workingComboBox.setValue("Đang đi làm");
        } else {
            workingComboBox.setValue("Đã nghỉ việc");
        }
    }

    public void save(ActionEvent event) {
        String name = nameTextField.getText();
        if (name.trim().equals("")) {
            commonController.resultNoti(false, "Tên nhân viên không thể để trống");
            return;
        }
        int id = Integer.parseInt(idTextField.getText());
        String citizenId = citizenIdTextField.getText();
        int role;
        if (roleComboBox.getValue().equalsIgnoreCase("Quản lí")) {
            role = 1;
        } else if (roleComboBox.getValue().equalsIgnoreCase("Nhân viên bán hàng")) {
            role = 2;
        } else {
            role = 3;
        }
        Date date;
        if (dobDatePicker.getValue() != null) {
            date = Date.valueOf(dobDatePicker.getValue());
        } else {
            date = null;
        }
        String phone = phoneTextField.getText();
        String insuranceId = insuranceIdTextField.getText();
        double salaryLevel = 0;
        if (!salaryLevelTextField.getText().isEmpty()) {
            salaryLevel = Double.parseDouble(salaryLevelTextField.getText());
        }
        String address = addressTextField.getText();
        int branchId = branchArrayList.get(branchComboBox.getSelectionModel().getSelectedIndex()).getId();
        boolean working = false;
        if (workingComboBox.getValue().equalsIgnoreCase("Đang đi làm")) {
            working = true;
        }
        String content = "Bạn có muốn cập nhật thông tin của nhân viên có id là :" + id + " Không?";
        boolean confirm = commonController.confirmAlert(content);
        if (confirm) {
            Employee employee = new Employee();
            employee.setId(id);
            employee.setName(name);
            employee.setDob(date);
            employee.setPhone(phone);
            employee.setAddress(address);
            employee.setSalaryLevel(salaryLevel);
            employee.setCitizenID(citizenId);
            employee.setInsuranceID(insuranceId);
            employee.setRole(role);
            employee.setBranchID(branchId);
            employee.setWorking(working);
            try {
                employeeService.updateEmployee(employee);
                Branch branch = employeeService.getBranchById(employee.getBranchID());
                employeeDetailForm = new EmployeeDetailForm(employee, branch);
                commonController.resultNoti(true, "Bạn đã cập nhật thông tin thành công");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                commonController.resultNoti(false);
            }
        }
    }

    public void informationAccount() {
        try {
            Account account = employeeService.getAccount(employeeDetailForm.getEmployeeId());
            Dialog<ButtonType> informationAccountDialog = new Dialog<>();

            ButtonType acceptButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
            informationAccountDialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField usernameTextField = new TextField();
            usernameTextField.setPromptText("Tài khoản");


            TextField passwordTextField = new TextField();
            passwordTextField.setPromptText("Mật khẩu");
            if(account != null){
                usernameTextField.setText(account.getUsername());
                passwordTextField.setText(account.getPassword());
            }


            grid.add(new Label("Tài khoản: "), 0, 0);
            grid.add(usernameTextField, 1, 0);
            grid.add(new Label("Mật khẩu: "), 0, 1);
            grid.add(passwordTextField, 1, 1);

            Node acceptButton = informationAccountDialog.getDialogPane().lookupButton(acceptButtonType);
            acceptButton.setDisable(false);
            usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                acceptButton.setDisable(newValue.trim().isEmpty());
            });

            informationAccountDialog.getDialogPane().setContent(grid);
            Optional<ButtonType> result = informationAccountDialog.showAndWait();
            if (result.get() == acceptButtonType) {
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                if (password.trim().isEmpty()) {
                    commonController.resultNoti(false, "Mật khẩu không thể để trống");
                } else {
                    try {
                        if (account == null) {
                            account = new Account();
                            account.setEmployeeID(employeeDetailForm.getEmployeeId());
                            account.setUsername(username);
                            account.setPassword(password);
                            employeeService.registerAccount(account);
                            commonController.resultNoti(true, "Đăng kí tài khoản thành công");
                        } else {
                            account.setUsername(username);
                            account.setPassword(password);
                            employeeService.updateAccount(account);
                            commonController.resultNoti(true, "Chỉnh sửa thông tin tài khoản thành công");
                        }

                    } catch (SQLException ex) {
                        commonController.resultNoti(false, "Tài khoản đã tồn tại!");
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
