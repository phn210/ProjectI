package app.controller.invoice;

import app.controller.CommonController;
import app.controller.home.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.entity.*;
import model.form.InvoiceDetailForm;
import model.form.InvoiceForm;
import service.invoice.InvoicesService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private TextArea textArea_Note;

    @FXML
    private TextField textField_InvoiceID;

    @FXML
    private Button button_FindProduct;

    @FXML
    private Button button_Submit;

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
        textFiled_SoldDate.setText(String.valueOf(LocalDate.now()));
        initForm();
        initEmployee();
    }

    public void initializeQuotation(){
        textFiled_SoldDate.setText(String.valueOf(LocalDate.now()));
        textField_InvoiceID.setDisable(true);
        button_FindCustomer.setVisible(false);
        button_Submit.setDisable(true);
        initForm();
        initEmployee();
    }

    public void initialize(InvoiceForm invoiceForm){

        this.invoiceForm = invoiceForm;
        try {
            this.invoice = invoicesService.invoiceRepo.findByID(invoiceForm.getId());
            this.customer = invoicesService.customerRepo.findByID(invoice.getCustomerID());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        } catch (NullPointerException nullPointerException){
            nullPointerException.printStackTrace();
        }

        textField_InvoiceID.setText(String.valueOf(invoiceForm.getId()));
        textFiled_SoldDate.setText(String.valueOf(invoiceForm.getDate()));
        textField_Surcharge.setText(String.valueOf(invoiceForm.getSurcharge()));
        textField_PayMethod.setText(invoiceForm.getPaymentMethod());
        comboBox_PayMethod.setValue(invoiceForm.getPaymentMethod());
        textArea_Note.setText(invoiceForm.getNote());

        comboBox_PayMethod.setDisable(true);
        textArea_Note.setEditable(false);
        textField_Surcharge.setEditable(false);
        button_FindCustomer.setVisible(false);
        button_FindProduct.setVisible(false);
        button_Submit.setVisible(false);

        initForm();
        initEmployee();
        updateTable();
        updateCustomer();
        updateCost();
    }

    public void initForm(){
        comboBox_PayMethod.setItems(FXCollections.observableArrayList("Tiền mặt", "Thẻ tín dụng", "Ví điện tử", "Chuyển khoản"));

        col_ImportID.setCellValueFactory(new PropertyValueFactory<>("importID"));
        col_Product.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        col_Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        col_TotalMoney.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));

        invoiceDetailFormObservableList = FXCollections.observableList(new ArrayList<>());
    }

    private void initEmployee(){
        try {
            Employee employee = invoicesService.employeeRepo.findById(this.account.getEmployeeID());
            textField_Employee.setText(employee.getName());
            textField_Branch.setText(invoicesService.branchRepo.findById(employee.getBranchID()).getName());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void updateTable(){
        try {
            ArrayList<InvoiceDetailForm> list = invoicesService.getAllInvoiceDetailForm(this.invoice);
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
                    for(InvoiceDetailForm invoiceDetailForm: invoiceDetailFormObservableList){
                        if (invoiceDetailForm.getProductID() == newInvoiceDetailForm.getProductID() && invoiceDetailForm.getImportID() == newInvoiceDetailForm.getImportID()) {
                            invoiceDetailForm.setAmount(invoiceDetailForm.getAmount()+newInvoiceDetailForm.getAmount());
                        } else this.invoiceDetailFormObservableList.add(newInvoiceDetailForm);
                    }
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
        if(button_Submit.isDisable()){
            //quotation mode


        } else {
            //creating invoice mode

            
        }
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
        if (invoiceDetailFormObservableList.size() == 0){
            commonController.resultNoti(false, "Chưa chọn sản phẩm");
        } else if(customer == null || comboBox_PayMethod.getValue() == null){
            commonController.resultNoti(false, "Hóa đơn không hợp lệ");
        } else {
            invoice.setDate(Date.valueOf(LocalDate.now()));
            invoice.setCustomerID(customer.getId());
            invoice.setEmployeeID(account.getEmployeeID());
            invoice.setPaymentMethod(comboBox_PayMethod.getValue());
            invoice.setTotalMoney(Double.valueOf(textField_TotalMoney.getText()));
            invoice.setTax(Double.valueOf(textField_Tax.getText()));
            if(textField_Surcharge.getText().isEmpty()) invoice.setSurcharge(0.0);
            else invoice.setSurcharge(Double.valueOf(textField_Surcharge.getText()));
            invoice.setNote(textArea_Note.getText());
            try {
                invoicesService.addInvoice(invoice, invoiceDetailFormObservableList);
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
                commonController.resultNoti(false);
                return;
            }
            commonController.resultNoti(true, "Thêm thành công hóa đơn");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.fireEvent(
                    new WindowEvent(((Node) event.getSource()).getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST)
            );
        }
    }

}
