package app.controller.invoice;

import app.controller.CommonController;
import app.controller.home.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entity.*;
import model.form.InvoiceDetailForm;
import model.form.InvoiceForm;
import service.invoice.InvoicesService;

import javax.imageio.IIOException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDetailController {

    @FXML
    private TableView<InvoiceDetailForm> table_InvoiceDetail;

    @FXML
    private TableColumn<InvoiceDetailForm, Integer> col_ImportID;

    @FXML
    private TableColumn<InvoiceDetailForm, String> col_Product;

    @FXML
    private TableColumn<InvoiceDetailForm, Integer> col_Amount;

    @FXML
    private TableColumn<InvoiceDetailForm, Double> col_Price;

    @FXML
    private TableColumn<InvoiceDetailForm, Double> col_Discount;

    @FXML
    private TableColumn<InvoiceDetailForm, Double> col_TotalMoney;

    @FXML
    private TextField textField_Phone;

    @FXML
    private TextField textField_Address;

    @FXML
    private TextField textField_Branch;

    @FXML
    private TextField textField_Employee;

    @FXML
    private TextField textFiled_SoldDate;

    @FXML
    private TextField textField_Customer;

    @FXML
    private Button button_FindCustomer;

    @FXML
    private TextField textField_Surcharge;

    @FXML
    private TextField textField_Tax;

    @FXML
    private TextField textField_TotalMoney;

    @FXML
    private TextField textField_PayMethod;

    @FXML
    private ComboBox<String> comboBox_PayMethod;

    @FXML
    private TextField textField_InvoiceID;

    private ObservableList<InvoiceDetailForm> invoiceDetailFormObservableList;

    private InvoicesService invoicesService;
    private InvoiceForm invoiceForm;
    private Invoice invoice;
    private Customer customer;
    private Account account;
    private CommonController commonController;

    static Customer newCustomer = null;
    static InvoiceDetailForm newInvoiceDetailForm = null;

    public InvoiceDetailController(){
        this.invoicesService = new InvoicesService();
        this.invoiceForm = new InvoiceForm();
        this.invoice = new Invoice();
        this.account = HomeController.account;
        this.commonController = new CommonController();
    }

    public void initialize(){
        button_FindCustomer.setVisible(true);
        initForm();
    }

    public void initialize(InvoiceForm invoiceForm){

        this.invoiceForm = invoiceForm;
        try {
            this.invoice = invoicesService.invoiceRepo.findByID(invoiceForm.getId());
            this.customer = invoicesService.customerRepo.findByID(invoice.getCustomerID());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        button_FindCustomer.setVisible(false);
        initForm();
        updateTable();
        updateCustomer();
        updateCost();
    }

    public void initForm(){
        try {
            textField_Employee.setText(invoicesService.employeeRepo.findById(this.account.getEmployeeID()).getName());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        comboBox_PayMethod.setItems(FXCollections.observableArrayList("Tiền mặt", "Thẻ tín dụng", "Ví điện tử", "Chuyển khoản"));

        col_ImportID.setCellValueFactory(new PropertyValueFactory<>("importID"));
        col_Product.setCellValueFactory(new PropertyValueFactory<>("product"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        col_Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        col_TotalMoney.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));

        invoiceDetailFormObservableList = FXCollections.observableList(new ArrayList<>());
    }

    public void updateTable(){
        try {
            ArrayList<InvoiceDetailForm> list = invoicesService.getAllInvoiceDetailForm();
            invoiceDetailFormObservableList = FXCollections.observableList(list);
            table_InvoiceDetail.setItems(invoiceDetailFormObservableList);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void updateCustomer(){
        textField_Customer.setText(customer.getName());
        textField_Phone.setText(customer.getPhone());
        textField_Address.setText(customer.getAddress());
    }

    public void updateCost(){
        double totalMoney = 0.0;
        double tax = 0.0;
        if(invoiceDetailFormObservableList.size() > 0){
            List<InvoiceDetailForm> list = invoiceDetailFormObservableList;
            for (InvoiceDetailForm invoiceDetailForm: list){
                totalMoney += invoiceDetailForm.getTotalMoney();
            }
            tax = totalMoney * 0.1;
        }
        textField_TotalMoney.setText(String.valueOf(totalMoney));
        textField_Tax.setText(String.valueOf(tax));
    }

    @FXML
    void addProduct(ActionEvent event) {
        try {
            newInvoiceDetailForm = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/UI/invoice/FindProduct.fxml"));
            Parent root = loader.load();
            FindProductController findProductController = new FindProductController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thêm sản phẩm");
            stage.show();
            stage.setOnCloseRequest(e -> {
                try {
                    this.invoiceDetailFormObservableList.add(newInvoiceDetailForm);
                } catch (NullPointerException nullPointerException){
                    System.out.println("Chưa thêm sản phẩm nào");
                    return;
                }
                table_InvoiceDetail.setItems(invoiceDetailFormObservableList);
                table_InvoiceDetail.refresh();
                updateCost();
            });

        } catch (IOException ioException){
            ioException.printStackTrace();
        }

    }

    @FXML
    void export(ActionEvent event) {

    }

    @FXML
    void findCustomer(ActionEvent event) {
        newCustomer = null;
        Stage stage = new Stage();
        stage.setScene(commonController.makeScene("/app/UI/invoice/FindCustomer.fxml"));
        stage.setTitle("Tìm khách hàng");
        stage.show();
        stage.setOnCloseRequest(e -> {
            if(newCustomer == null) {
                System.out.println("Chưa thêm khách hàng");
                return;
            } else {
                this.customer = newCustomer;
                updateCustomer();
            }
        });
    }

    @FXML
    void submit(ActionEvent event) {

    }

}
