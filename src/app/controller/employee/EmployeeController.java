package app.controller.employee;

import app.controller.CommonController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.form.EmployeeDetailForm;
import service.employee.EmployeeService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    EmployeeService employeeService;

    CommonController commonController;

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
}
