package app.controller.duty_roster;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.form.SalaryDetailForm;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class SalaryTableController implements Initializable {
    public static Date date;

    @FXML
    TableView<SalaryDetailForm> table;

    @FXML
    TableColumn<SalaryDetailForm, Integer> idColumn;

    @FXML
    TableColumn<SalaryDetailForm, String> nameColumn;

    @FXML
    TableColumn<SalaryDetailForm, Integer> monthColumn;

    @FXML
    TableColumn<SalaryDetailForm, Integer> yearColumn;

    @FXML
    TableColumn<SalaryDetailForm, Double> salaryLevelColumn;

    @FXML
    TableColumn<SalaryDetailForm, Double> totalHoursColumn;

    @FXML
    TableColumn<SalaryDetailForm, Double> totalSalaryColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void initTable(){}

    private void initColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        salaryLevelColumn.setCellValueFactory(new PropertyValueFactory<>("salaryLevel"));
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
        totalSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("totalSalary"));


    }

    private void loadData(){

    }
}
