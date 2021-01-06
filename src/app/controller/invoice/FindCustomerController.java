package app.controller.invoice;

import app.controller.CommonController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.entity.Customer;
import model.entity.InvoiceDetail;
import service.customer.CustomerService;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class FindCustomerController implements Initializable {

    @FXML
    private TableView<Customer> table;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> addressColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private GridPane searchPane_Products;

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_Email;

    @FXML
    private ComboBox<Integer> comboBox_ID;

    @FXML
    private TextField textField_Phone;

    CommonController commonController;

    CustomerService customerService;

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
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    Customer rowData = row.getItem();
                    Dialog<ButtonType> editCustomerDialog = new Dialog<>();
                    ButtonType acceptButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
                    ButtonType cancelButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
                    editCustomerDialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType);

                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    TextField nameTextField = new TextField();
                    nameTextField.setPromptText("Tên khách hàng");
                    nameTextField.setText(rowData.getName());

                    TextField phoneTextField = new TextField();
                    phoneTextField.setPromptText("Số điện thoại khách hàng");
                    phoneTextField.setText(rowData.getPhone());

                    TextField addressTextField = new TextField();
                    addressTextField.setPromptText("Địa chỉ khách hàng");
                    addressTextField.setText(rowData.getAddress());

                    TextField emailTextField = new TextField();
                    emailTextField.setPromptText("Email khách hàng");
                    emailTextField.setText(rowData.getEmail());

                    grid.add(new Label("Tên khách hàng: "), 0, 0);
                    grid.add(nameTextField, 1, 0);
                    grid.add(new Label("Số điện thoại: "), 0, 1);
                    grid.add(phoneTextField, 1, 1);
                    grid.add(new Label("Địa chỉ: "), 0, 2);
                    grid.add(addressTextField, 1, 2);
                    grid.add(new Label("Email"), 0, 3);
                    grid.add(emailTextField, 1, 3);

                    Node acceptButton = editCustomerDialog.getDialogPane().lookupButton(acceptButtonType);
                    acceptButton.setDisable(false);
                    nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue.trim().isEmpty()) {
                            acceptButton.setDisable(true);
                        } else {
                            if (phoneTextField.getText().trim().isEmpty()) {
                                acceptButton.setDisable(true);
                            } else {
                                acceptButton.setDisable(false);
                            }
                        }
                    });
                    phoneTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue.trim().isEmpty()) {
                            acceptButton.setDisable(true);
                        } else {
                            if (nameTextField.getText().trim().isEmpty()) {
                                acceptButton.setDisable(true);
                            } else {
                                acceptButton.setDisable(false);
                            }
                        }
                    });
                    editCustomerDialog.getDialogPane().setContent(grid);

                    Optional<ButtonType> result = editCustomerDialog.showAndWait();
                    if (result.get() == acceptButtonType) {
                        String name = nameTextField.getText();
                        String phone = phoneTextField.getText();
                        String address = addressTextField.getText();
                        String email = emailTextField.getText();
                        Customer customer = new Customer();
                        customer.setName(name);
                        customer.setEmail(email);
                        customer.setPhone(phone);
                        customer.setAddress(address);
                        customer.setId(rowData.getId());
                        try {
                            customerService.updateCustomer(customer);
                            loadData();
                        } catch (SQLException exception) {
                            commonController.resultNoti(false, "Số điện thoại đã trùng");
                        }
                    }
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
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData(){
        try {
            customerObservableList = FXCollections.observableArrayList(customerService.getAllCustomer());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(customerObservableList);
    }

    public void addCustomer(){
        Dialog<ButtonType> addCustomerDialog = new Dialog<>();
        ButtonType acceptButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        addCustomerDialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Tên khách hàng");

        TextField phoneTextField = new TextField();
        phoneTextField.setPromptText("Số điện thoại khách hàng");

        TextField addressTextField = new TextField();
        addressTextField.setPromptText("Địa chỉ khách hàng");

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Email khách hàng");

        grid.add(new Label("Tên khách hàng: "), 0, 0);
        grid.add(nameTextField, 1, 0);
        grid.add(new Label("Số điện thoại: "), 0, 1);
        grid.add(phoneTextField, 1, 1);
        grid.add(new Label("Địa chỉ: "), 0, 2);
        grid.add(addressTextField, 1, 2);
        grid.add(new Label("Email"), 0, 3);
        grid.add(emailTextField, 1, 3);

        Node acceptButton = addCustomerDialog.getDialogPane().lookupButton(acceptButtonType);
        acceptButton.setDisable(true);
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.trim().isEmpty()){
                acceptButton.setDisable(true);
            }else{
                if(phoneTextField.getText().trim().isEmpty()){
                    acceptButton.setDisable(true);
                }else{
                    acceptButton.setDisable(false);
                }
            }
        });
        phoneTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.trim().isEmpty()){
                acceptButton.setDisable(true);
            }else{
                if(nameTextField.getText().trim().isEmpty()){
                    acceptButton.setDisable(true);
                }else{
                    acceptButton.setDisable(false);
                }
            }
        });
        addCustomerDialog.getDialogPane().setContent(grid);

        Optional<ButtonType> result = addCustomerDialog.showAndWait();
        if(result.get() == acceptButtonType){
            String name = nameTextField.getText();
            String phone = phoneTextField.getText();
            String address = addressTextField.getText();
            String email = emailTextField.getText();
            Customer customer = new Customer();
            customer.setName(name);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setAddress(address);
            try {
                customerService.addCustomer(customer);
                loadData();
            } catch (SQLException exception) {
                commonController.resultNoti(false, "Số điện thoại đã trùng");
            }
        }
    }

    @FXML
    void select(ActionEvent event) {
        try{
            InvoiceDetailController.newCustomer = table.getSelectionModel().getSelectedItem();
        } catch (NullPointerException nullPointerException){
            nullPointerException.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vui lòng chọn sản phẩm!");
            alert.setHeaderText("Warning!");
            alert.show();
            return;
        }
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.fireEvent(
                new WindowEvent(((Node) event.getSource()).getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST)
        );
    }

}
