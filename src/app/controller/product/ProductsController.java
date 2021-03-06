package app.controller.product;

import app.controller.CommonController;
import app.controller.home.HomeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entity.Account;
import model.entity.Import;
import model.entity.Product;
import model.form.ImportForm;
import model.form.ProductForm;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.excel.ExcelService;
import service.product.ProductsService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {

    @FXML
    private Tab tab_Products;

    @FXML
    private Tab tab_Imports;

    @FXML
    private TableView<ProductForm> table_Product;

    @FXML
    private TableColumn<ProductForm, Integer> col_ID;
    ExcelService<ProductForm> excelServiceProduct;
    ExcelService<ImportForm> excelServiceImport;
    @FXML
    private TableColumn<ProductForm, String> col_Name;

    @FXML
    private TableColumn<ProductForm, String> col_Type;

    @FXML
    private TableColumn<ProductForm, String> col_Brand;

    @FXML
    private TableColumn<ProductForm, Integer> col_Amount;

    @FXML
    private TableColumn<ProductForm, String> col_Description;

    @FXML
    private TableColumn<ProductForm, Double> col_Price;

    @FXML
    private TableColumn<ProductForm, Integer> col_Discount;

    @FXML
    private TableView<ImportForm> table_Import;

    @FXML
    private TableColumn<ImportForm, Integer> col_ImportID;

    @FXML
    private TableColumn<ImportForm, String> col_Supplier;

    @FXML
    private TableColumn<ImportForm, Date> col_Date;

    @FXML
    private TableColumn<ImportForm, Double> col_TotalMoney;

    @FXML
    private GridPane searchPane_Products;

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_Brand;

    @FXML
    private TextField textField_ProductID;

    @FXML
    private ComboBox<String> comboBox_Type;

    @FXML
    private CheckBox checkBox_Discount;

    @FXML
    private GridPane searchPane_Imports;

    @FXML
    private TextField textField_ImportID;

    @FXML
    private ComboBox<String> comboBox_Supplier;

    @FXML
    private DatePicker datePicker_ImportDate;

    //viewMode = 0: product
    //viewMode = 1: import
    private int viewMode;

    //accountMode = 1: manager
    //accountMode = 2: sale
    private int accountMode;

    private ObservableList<Integer> idObservableList;
    private ObservableList<String> typeObservableList;
    private ObservableList<String> supplierObservableList;
    private ObservableList<ProductForm> productObservableList;
    private ObservableList<ImportForm> importObservableList;

    private Account account;
    private ProductsService productsService;

    private CommonController commonController;
    String[] titlesProduct;
    String[] titlesImport;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.account = HomeController.account;
        this.commonController = new CommonController();
        this.productsService = new ProductsService();
        this.viewMode = 0;
        this.accountMode = account.getEmployeeID();
        excelServiceProduct = new ExcelService<>();
        excelServiceImport = new ExcelService<>();
        titlesProduct= new String[]{"ID", "Tên sản phẩm", "Loại", "Hãng", "Số lượng", "Mô tả", "Giá bán lẻ", "Khuyến mãi"};
        titlesImport= new String[]{"ID đơn nhập", "Nhà cung cấp", "Ngày nhập", "Thành tiền"};
        initTable();

        this.updateTabProduct();
    }

    private void initTable(){
        initColumn();
    }

    private void initColumn(){
        col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_Brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        col_Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        col_ImportID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        col_TotalMoney.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));
    }

    public void updateTabProduct(){
        searchPane_Products.setVisible(true);
        searchPane_Imports.setVisible(false);
        List<ProductForm> list = new ArrayList<>();
        try {
            list = productsService.getAllProductForm();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        productObservableList = FXCollections.observableList(list);
        table_Product.setItems(productObservableList);
        table_Product.refresh();

        try {
            typeObservableList = FXCollections.observableList(productsService.getAllTypeName());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        comboBox_Type.setItems(typeObservableList);
    }

    public void updateTabImport(){
        searchPane_Products.setVisible(false);
        searchPane_Imports.setVisible(true);
        List<ImportForm> list = new ArrayList<>();

        try {
            list = productsService.getAllImportForm();
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        importObservableList = FXCollections.observableList(list);
        table_Import.setItems(importObservableList);
        table_Import.refresh();

        try {
            supplierObservableList = FXCollections.observableList(productsService.getAllSupplierName());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        comboBox_Supplier.setItems(supplierObservableList);
    }

    @FXML
    void checkDetail(ActionEvent event) throws IOException {
        if (viewMode == 0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/UI/product/ProductDetail.fxml"));
            Parent root = loader.load();
            ProductDetailController productDetailController = loader.getController();
            try {
                productDetailController.initialize(table_Product.getSelectionModel().getSelectedItem());
            } catch(NullPointerException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Vui lòng chọn sản phẩm!");
                alert.setHeaderText("Warning!");
                alert.show();
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông tin sản phẩm");
            stage.show();
        } else if (viewMode == 1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/UI/product/ImportDetail.fxml"));
            Parent root = loader.load();
            ImportDetailController importDetailController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông tin đơn nhập");
            stage.show();
        }
    }

    @FXML
    void export(ActionEvent event) {
        if (viewMode == 0) {
            String filePath = commonController.chooseDirectory();
            Calendar calendar = Calendar.getInstance();
            String fileName = "Product_" + calendar.getTimeInMillis() + ".xlsx";
            filePath += "\\" + fileName;
            excelServiceProduct.writeToExcel(titlesProduct, filePath, productObservableList);
        } else if (viewMode == 1) {
            String filePath = commonController.chooseDirectory();
            Calendar calendar = Calendar.getInstance();
            String fileName = "Import_"+ calendar.getTimeInMillis()+".xlsx";
            filePath += "\\"+fileName;
            excelServiceImport.writeToExcel(titlesImport, filePath, importObservableList);
        }
    }

    @FXML
    void help(ActionEvent event) {

    }

    @FXML
    void importFile(ActionEvent event){
        if (viewMode == 0){

        } else if (viewMode == 1){

        }
    }

    @FXML
    void importManually(ActionEvent event) throws IOException {
        if (viewMode == 0){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/UI/product/ProductDetail.fxml"));
            Parent root = loader.load();
            ProductDetailController productDetailController = loader.getController();
            productDetailController.initialize();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông tin sản phẩm");
            stage.show();
            stage.setOnCloseRequest(e -> updateTabProduct());

        } else if (viewMode == 1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/UI/product/ImportDetail.fxml"));
            Parent root = loader.load();
            ImportDetailController importDetailController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông tin đơn nhập");
            stage.show();
            stage.setOnCloseRequest(e -> updateTabImport());
        }
    }

    @FXML
    void selectedImport() {
        this.viewMode = 1;
        updateTabImport();
    }

    @FXML
    void selectedProduct() {
        this.viewMode = 0;
        updateTabProduct();
    }

    @FXML
    void search(ActionEvent event){
        if(viewMode == 0){
            int productID = 0;
            String type = new String();
            try {
                productID = Integer.parseInt(textField_ProductID.getText());
            } catch (NumberFormatException numberFormatException){
                System.out.println("No ID input!");
            }
            try {
                type = comboBox_Type.getValue();
            } catch (NullPointerException nullPointerException) {
                System.out.println("No type input");
            }
            String name = textField_Name.getText();
            String brand = textField_Brand.getText();
            boolean discount = checkBox_Discount.isSelected();

            try {
                ArrayList<Product> list = productsService.productRepo.search(productID, name, type, brand, discount);
                productObservableList = FXCollections.observableList(productsService.toProductForm(list));
                table_Product.setItems(productObservableList);
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
            }

        } else if(viewMode == 1){
            int importID = 0;
            String supplier = new String();
            Date importDate = null;
            try {
                importID = Integer.parseInt(textField_ImportID.getText());
            } catch (NumberFormatException numberFormatException){
                System.out.println("No ID input!");
            }
            try {
                supplier = comboBox_Supplier.getValue();
            } catch (NullPointerException nullPointerException) {
                System.out.println("No supplier input");
            }
            try {
                importDate = Date.valueOf(datePicker_ImportDate.getValue());
            } catch (NullPointerException nullPointerException) {
                System.out.println("No date input");
            }

            try {
                ArrayList<Import> list = productsService.importRepo.search(importID, supplier, importDate);
                importObservableList = FXCollections.observableList(productsService.toImportForm(list));
                table_Import.setItems(importObservableList);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

}
