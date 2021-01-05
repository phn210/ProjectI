package app.controller.employee;

import app.controller.CommonController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.entity.Employee;
import model.form.EmployeeDetailForm;
import service.employee.EmployeeService;
import service.home.HomeService;

import java.io.IOException;
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

    private ObservableList<EmployeeDetailForm> employeeDetailFormObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        employeeService = new EmployeeService();

        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
        table.setRowFactory( RowFactory -> {
            TableRow<EmployeeDetailForm> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    EmployeeDetailForm rowData = row.getItem();
                }
            });
            return row ;
        });
    }

    private void initColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("EmployeeId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dobColumn.setCellValueFactory(new PropertyValueFactory<>("dobColumn"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        citizenIdColumn.setCellValueFactory(new PropertyValueFactory<>("citizenId"));
        roleColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<EmployeeDetailForm, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<EmployeeDetailForm, String> param) {
                int role = param.getValue().getRole();
                String roleName = "";
                if(role == 1){
                    roleName = "Quản lí";
                }else if(role == 2){
                    roleName = "Nhân viên bán hàng";
                }else{
                    roleName = "Nhân viên kỹ thuật";
                }
                return new ReadOnlyStringWrapper(roleName);
            }
        });
        branchNameColumn.setCellValueFactory(new PropertyValueFactory<>("branchName"));
    }

    private void loadData(){
        try {
            employeeDetailFormObservableList = FXCollections.observableArrayList(employeeService.getAllEmployees());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(employeeDetailFormObservableList);
    }
}
