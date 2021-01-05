package app.controller.customer;

import app.controller.CommonController;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.entity.Customer;
import model.form.EmployeeDetailForm;
import service.customer.CustomerService;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    CommonController commonController;

    CustomerService customerService;

    @FXML
    TableView<Customer> table;

    @FXML
    TableColumn<Customer, Integer> idColumn;

    @FXML
    TableColumn<Customer, String> nameColumn;

    @FXML
    TableColumn<Customer, String> phoneColumn;

    @FXML
    TableColumn<Customer, String> addressColumn;

    @FXML
    TableColumn<Customer, Integer> pointColumn;

    @FXML
    TableColumn<Customer, String> emailColumn;

    private ObservableList<Customer> customerObservableList;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        customerService = new CustomerService();

        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
        table.setRowFactory( RowFactory -> {
            TableRow<Customer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                     Customer rowData = row.getItem();
                }
            });
            return row ;
        });
    }

    private void initColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        pointColumn.setCellValueFactory(new PropertyValueFactory<>("point"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData(){

    }

}
