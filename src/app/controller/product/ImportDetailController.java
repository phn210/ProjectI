package app.controller.product;

import app.controller.CommonController;
import app.controller.invoice.FindProductController;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Supplier;
import model.form.ImportDetailForm;
import model.form.ImportForm;
import model.form.InvoiceDetailForm;
import model.form.InvoiceForm;
import service.product.ProductsService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ImportDetailController {

    @FXML
    private TextField textField_TotalMoney;

    @FXML
    private ComboBox<String> comboBox_Supplier;

    @FXML
    private TextField textField_Supplier;

    @FXML
    private TextField textField_ID;

    @FXML
    private DatePicker datePicker_Date;

    @FXML
    private TableView<ImportDetailForm> table_ImportDetail;

    @FXML
    private TableColumn<ImportDetailForm, Integer> col_ID;

    @FXML
    private TableColumn<ImportDetailForm, String > col_Name;

    @FXML
    private TableColumn<ImportDetailForm, Date> col_importDate;

    @FXML
    private TableColumn<ImportDetailForm, Integer> col_Amount;

    @FXML
    private TableColumn<ImportDetailForm, Double> col_ImportPrice;

    @FXML
    private Button button_FindProduct;

    @FXML
    private Button button_Submit;

    private Supplier supplier;
    private Import anImport;
    private ProductsService productsService;
    private CommonController commonController;

    private ObservableList<ImportDetailForm> importDetailFormObservableList;
    private ObservableList<String> supplierObservableList;

    public static ImportDetailForm newImportDetailForm = null;

    public ImportDetailController(){
        this.supplier = new Supplier();
        this.productsService = new ProductsService();
        this.commonController = new CommonController();
        this.anImport = new Import();
    }

    public void initialize(){
        button_FindProduct.setVisible(true);

        comboBox_Supplier.setDisable(false);
        textField_TotalMoney.setText("0.0");
        initForm();
        updateSupplier();
    }

    public void initialize(ImportForm importForm){
        try {
            this.anImport = productsService.importRepo.findByID(importForm.getId());
            this.supplier = productsService.supplierRepo.findByID(anImport.getSupplierID());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        button_FindProduct.setVisible(false);

        textField_ID.setText(String.valueOf(importForm.getId()));
        textField_Supplier.setText(importForm.getSupplier());
        comboBox_Supplier.setValue(importForm.getSupplier());
        comboBox_Supplier.setDisable(true);
        datePicker_Date.setValue(importForm.getImportDate().toLocalDate());
        datePicker_Date.setDisable(true);

        initForm();
        updateTable();
    }

    public void initForm(){
        col_ID.setCellValueFactory(new PropertyValueFactory<>("productID"));
        col_Name.setCellValueFactory(new PropertyValueFactory<>("productName"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_ImportPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));

        importDetailFormObservableList = FXCollections.observableList(new ArrayList<>());
    }

    public void updateTable(){
        try {
            ArrayList<ImportDetailForm> list = productsService.getAllImportDetailForm(anImport);
            importDetailFormObservableList = FXCollections.observableList(list);
            table_ImportDetail.setItems(importDetailFormObservableList);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void updateSupplier(){
        List<String> list = new ArrayList<>();
        try {
            list = productsService.getAllSupplierName();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        list.add("Thêm mới");
        supplierObservableList = FXCollections.observableList(list);
        comboBox_Supplier.setItems(supplierObservableList);
    }

    public void updateCost(){
        double totalMoney = 0.0;
        if(importDetailFormObservableList.size() > 0){
            List<ImportDetailForm> list = importDetailFormObservableList;
            for (ImportDetailForm importDetailForm: list){
                totalMoney += importDetailForm.getAmount()*importDetailForm.getImportPrice();
            }
        }
        textField_TotalMoney.setText(String.valueOf(totalMoney));
    }

    @FXML
    void addProduct(ActionEvent event) {
        try {
            newImportDetailForm = null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/UI/product/FindProduct.fxml"));
            Parent root = loader.load();
            ImportProductController importProductController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thêm sản phẩm");
            stage.show();
            stage.setOnCloseRequest(e -> {
                try {
                    boolean existed = false;
                    if(importDetailFormObservableList.size() > 0){
                        for(ImportDetailForm importDetailForm: importDetailFormObservableList){
                            if(importDetailForm.getProductID() == newImportDetailForm.getProductID()){
                                importDetailForm.setAmount(newImportDetailForm.getAmount());
                                importDetailForm.setImportPrice(newImportDetailForm.getImportPrice());
                                existed = true;
                                break;
                            }
                        }
                    }
                    if (!existed) this.importDetailFormObservableList.add(newImportDetailForm);
                    table_ImportDetail.setItems(importDetailFormObservableList);
                    table_ImportDetail.refresh();
                    updateCost();
                } catch (NullPointerException nullPointerException){
                    System.out.println("Chưa thêm sản phẩm nào");
                }
            });

        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @FXML
    void selectSupplier(ActionEvent event){
        try {
            if (comboBox_Supplier.getSelectionModel().getSelectedItem().equals("Thêm mới")){
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/UI/products/BrandDetail.fxml"));
//                    Parent root = loader.load();
//                    BrandDetailController brandDetailController = new BrandDetailController();
//                    brandDetailController.initialize();
//
//                    Stage stage = new Stage();
//                    stage.setScene(new Scene(root));
//                    stage.setTitle("Thông tin nhãn hàng");
//                    stage.show();
//                    stage.setOnCloseRequest(e -> {
//                        updateBrand();
//                        comboBox_Brand.setValue(null);
//                    });
//                } catch (IOException ioException){
//                    ioException.printStackTrace();
//                }
            } else {
                int supplierIndex = comboBox_Supplier.getSelectionModel().getSelectedIndex();
                this.supplier = productsService.supplierRepo.findAll().get(supplierIndex);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    void handleSupplier(MouseEvent event){

    }

    @FXML
    void export(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {
        if(importDetailFormObservableList.size() == 0){
            commonController.resultNoti(false, "Chưa thêm sản phẩm");
        } else if(datePicker_Date.getValue() == null || datePicker_Date.getValue().isAfter(LocalDate.now())){
            commonController.resultNoti(false, "Ngày không hợp lệ");
        } else {
            anImport.setSupplierID(supplier.getId());
            anImport.setImportDate(Date.valueOf(datePicker_Date.getValue()));
            anImport.setTotalMoney(Double.parseDouble(textField_TotalMoney.getText()));
            try{
                productsService.addImport(anImport, importDetailFormObservableList);
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
                commonController.resultNoti(false);
                return;
            }
            commonController.resultNoti(true, "Thêm thành công lô hàng");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.fireEvent(
                    new WindowEvent(((Node) event.getSource()).getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST)
            );
        }
    }

}
