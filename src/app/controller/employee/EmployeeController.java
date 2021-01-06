package app.controller.employee;

import app.controller.CommonController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entity.Branch;
import model.entity.Employee;
import model.form.EmployeeDetailForm;
import service.employee.EmployeeService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    EmployeeService employeeService;

    CommonController commonController;

    @FXML
    TextField idTextField;

    @FXML
    TextField nameTextField;

    @FXML
    TextField ageTextField;

    @FXML
    TextField phoneTextField;

    @FXML
    TextField citizenIdTextField;


    @FXML
    TableView<EmployeeDetailForm> table;

    @FXML
    TableColumn<EmployeeDetailForm, Integer> idColumn;

    @FXML
    TableColumn<EmployeeDetailForm, String> nameColumn;

    @FXML
    TableColumn<EmployeeDetailForm, Date> dobColumn;

    @FXML
    TableColumn<EmployeeDetailForm, String> phoneColumn;

    @FXML
    TableColumn<EmployeeDetailForm, String> citizenIdColumn;

    @FXML
    TableColumn<EmployeeDetailForm, String> roleColumn;

    @FXML
    TableColumn<EmployeeDetailForm, String> branchNameColumn;

    @FXML
    TableColumn<EmployeeDetailForm, String> addressColumn;
    private ObservableList<EmployeeDetailForm> employeeDetailFormObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        employeeService = new EmployeeService();

        idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                if(!newValue.isEmpty()){
                    Integer.parseInt(newValue);
                }
            }catch (NumberFormatException ex){
                commonController.resultNoti(false, "ID không hợp lệ");
                idTextField.setText("");
            }
        });
        ageTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                if(!newValue.isEmpty()){
                    Integer.parseInt(newValue);
                }
            }catch (NumberFormatException ex){
                commonController.resultNoti(false, "Tuổi không hợp lệ");
                ageTextField.setText("");
            }
        });
        initTable();
        loadData();
    }

    private void initTable() {
        initColumns();
        table.setRowFactory(RowFactory -> {
            TableRow<EmployeeDetailForm> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    EmployeeDetailForm rowData = row.getItem();
                    EmployeeDetailController.employeeDetailForm = rowData;

                    Stage stage = new Stage();
                    Scene scene = commonController.makeScene("../UI/employee/EmployeeDetail.fxml");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    Stage bigStage = (Stage)(((Node) event.getSource()).getScene().getWindow());
                    bigStage.setOnCloseRequest(event1 -> {
                        stage.close();
                    });
                    stage.setOnCloseRequest(event1 -> {
                        loadData();
                    });
                }
            });
            return row;
        });
    }

    private void initColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dob"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        citizenIdColumn.setCellValueFactory(new PropertyValueFactory<>("citizenId"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        roleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmployeeDetailForm, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<EmployeeDetailForm, String> param) {
                int role = param.getValue().getRole();
                String roleName = "";
                if (role == 1) {
                    roleName = "Quản lí";
                } else if (role == 2) {
                    roleName = "Nhân viên bán hàng";
                } else {
                    roleName = "Nhân viên kỹ thuật";
                }
                return new ReadOnlyStringWrapper(roleName);
            }
        });
        branchNameColumn.setCellValueFactory(new PropertyValueFactory<>("branchName"));
    }

    private void loadData() {
        try {
            employeeDetailFormObservableList = FXCollections.observableArrayList(employeeService.getAllEmployee());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(employeeDetailFormObservableList);
    }

    public void addEmployee(){
        Dialog<ButtonType> addEmployeeDialog = new Dialog<>();

        ButtonType acceptButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        addEmployeeDialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Tên nhân viên");

        ComboBox<String> branchComboBox = new ComboBox<>();
        ObservableList<String> branchComboBoxList;
        ArrayList<Branch> branchArrayList = new ArrayList<>();
        try {
            branchArrayList = employeeService.getAllBranch();
            branchComboBoxList = FXCollections.observableArrayList(employeeService.getAllBranchName());
            branchComboBox.setItems(branchComboBoxList);
            branchComboBox.setValue(branchComboBoxList.get(0));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        ComboBox<String> roleComboBox = new ComboBox<>();
        ObservableList<String> roleComboBoxList;
        roleComboBoxList = FXCollections.observableArrayList("Quản lí", "Nhân viên bán hàng", "Nhân viên kĩ thuật");
        roleComboBox.setItems(roleComboBoxList);
        roleComboBox.setValue(roleComboBoxList.get(2));

        grid.add(new Label("Tên nhân viên: "), 0, 0);
        grid.add(nameTextField, 1, 0);
        grid.add(new Label("Chi nhánh: "), 0, 1);
        grid.add(branchComboBox, 1, 1);
        grid.add(new Label("Vai trò: "), 0, 2);
        grid.add(roleComboBox, 1, 2);

        Node acceptButton = addEmployeeDialog.getDialogPane().lookupButton(acceptButtonType);
        acceptButton.setDisable(true);
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });
        addEmployeeDialog.getDialogPane().setContent(grid);
        Optional<ButtonType> result = addEmployeeDialog.showAndWait();
        if(result.get() == acceptButtonType){
            String name = nameTextField.getText();
            Branch branch = branchArrayList.get(branchComboBox.getSelectionModel().getSelectedIndex());
            int role;
            if(roleComboBox.getValue().equalsIgnoreCase("Quản lí")){
                role = 1;
            }else if(roleComboBox.getValue().equalsIgnoreCase("Nhân viên bán hàng")){
                role = 2;
            }else{
                role = 3;
            }
            Employee employee = new Employee(name, role, branch.getId());
            try {
                employeeService.addEmployee(employee);
                loadData();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void search(){
        int id = -1;
        if(!idTextField.getText().trim().isEmpty()){
            id = Integer.parseInt(idTextField.getText());
        }
        String name = nameTextField.getText().trim();
        String phone = phoneTextField.getText().trim();
        int age = -1;
        if(!ageTextField.getText().trim().isEmpty()){
            age = Integer.parseInt(ageTextField.getText());
        }
        String citizenId = citizenIdTextField.getText().trim();
        try {
            employeeDetailFormObservableList = FXCollections.observableArrayList(employeeService.search(id, name, phone, age, citizenId));
            table.setItems(employeeDetailFormObservableList);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
