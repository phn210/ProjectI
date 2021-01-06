package app.controller.invoice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.form.InvoiceForm;
import service.invoice.InvoicesService;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InvoicesController implements Initializable {

    @FXML
    private TableView<InvoiceForm> table_Invoice;

    @FXML
    private TableColumn<InvoiceForm, Integer> col_ID;

    @FXML
    private TableColumn<InvoiceForm, Date> col_SoldDate;

    @FXML
    private TableColumn<InvoiceForm, String> col_Customer;

    @FXML
    private TableColumn<InvoiceForm, String> col_Employee;

    @FXML
    private TableColumn<InvoiceForm, String> col_PayMethod;

    @FXML
    private TableColumn<InvoiceForm, Double> col_TotalMoney;

    @FXML
    private TextField textField_ID;

    @FXML
    private DatePicker datePicker_SoldDate;

    @FXML
    private ComboBox<String> comboBox_PayMethod;

    @FXML
    private TextField textField_Customer;

    @FXML
    private TextField textField_Employee;

    private ObservableList<InvoiceForm> invoiceFormObservableList;

    private InvoicesService invoicesService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.invoicesService = new InvoicesService();
    }

    public void initTable(){
        comboBox_PayMethod.setItems(FXCollections.observableArrayList("Tiền mặt", "Thẻ tín dụng", "Ví điện tử", "Chuyển khoản"));

        col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_SoldDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_Customer.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        col_Employee.setCellValueFactory(new PropertyValueFactory<>("employee"));
        col_PayMethod.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        col_TotalMoney.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));
    }

    public void updateTable(){
        try {
            ArrayList<InvoiceForm> list = invoicesService.getAllInvoiceForm();
            invoiceFormObservableList = FXCollections.observableList(list);
            table_Invoice.setItems(invoiceFormObservableList);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    @FXML
    void checkDetail(MouseEvent event) {

    }

    @FXML
    void export(ActionEvent event) {

    }

    @FXML
    void help(ActionEvent event) {

    }

    @FXML
    void importFile(ActionEvent event) {

    }

    @FXML
    void importManually(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/UI/invoice/InvoiceDetail.fxml"));
            Parent root = loader.load();
            InvoiceDetailController invoiceDetailController = loader.getController();
            invoiceDetailController.initialize();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thêm hóa đơn");
            stage.show();
            Stage bigStage = (Stage)(table_Invoice.getParent().getScene().getWindow());
            bigStage.setOnCloseRequest(event1 -> {
                stage.close();
            });

            stage.setOnCloseRequest(e -> updateTable());

        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @FXML
    void quotation(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }
}
