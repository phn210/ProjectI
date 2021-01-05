package app.controller.product;

import app.controller.Main;
import app.controller.home.HomeController;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import repository.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsController implements Initializable {

    @FXML
    private Tab tab_Products;

    @FXML
    private Tab tab_Imports;

    @FXML
    private TableView<Product> table_Product;

    @FXML
    private TableColumn<Product, Integer> col_ID;

    @FXML
    private TableColumn<Product, String> col_Name;

    @FXML
    private TableColumn<Product, String> col_Type;

    @FXML
    private TableColumn<Product, String> col_Brand;

    @FXML
    private TableColumn<Product, Integer> col_Amount;

    @FXML
    private TableColumn<Product, String> col_Description;

    @FXML
    private TableColumn<Product, Double> col_Price;

    @FXML
    private TableColumn<Product, Integer> col_Discount;

    @FXML
    private TableView<Import> table_Import;

    @FXML
    private TableColumn<Import, Integer> col_ImportID;

    @FXML
    private TableColumn<Import, String> col_Supplier;

    @FXML
    private TableColumn<Import, Date> col_Date;

    @FXML
    private TableColumn<Import, Double> col_TotalMoney;

    @FXML
    private GridPane searchPane_Products;

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_Brand;

    @FXML
    private ComboBox<Integer> comboBox_ProductID;

    @FXML
    private ComboBox<String> comboBox_Type;

    @FXML
    private CheckBox checkBox_Discount;

    @FXML
    private GridPane searchPane_Imports;

    @FXML
    private ComboBox<Integer> comboBox_ImportID;

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
    private ObservableList<Product> productObservableList;
    private ObservableList<Import> importObservableList;

    private Account account;
    private ProductRepo productRepo;
    private ImportRepo importRepo;
    private TypeRepo typeRepo;
    private BrandRepo brandRepo;
    private SupplierRepo supplierRepo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.account = HomeController.account;
        this.productRepo = new ProductRepo();
        this.importRepo = new ImportRepo();
        this.brandRepo = new BrandRepo();
        this.typeRepo = new TypeRepo();
        this.supplierRepo = new SupplierRepo();
        this.viewMode = 0;
        this.accountMode = account.getEmployeeID();

        col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Type.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(typeRepo.getType(t.getValue()).getName()));
        col_Brand.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(brandRepo.getBrand(t.getValue()).getName()));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        col_Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        col_ImportID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Supplier.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        col_TotalMoney.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));

        this.updateTabProduct();
    }

    public void updateTabProduct(){
        searchPane_Products.setVisible(true);
        searchPane_Imports.setVisible(false);

        List<Product> products = productRepo.getAllProduct();
        productObservableList = FXCollections.observableList(products);
        table_Product.setItems(productObservableList);

        List<Integer> idList = new ArrayList<>();
        for (Product product : products){
            idList.add(product.getId());
        }
        idObservableList = FXCollections.observableList(idList);
        comboBox_ProductID.setItems(idObservableList);

        typeObservableList = FXCollections.observableList(typeRepo.getAllTypeName());
        comboBox_Type.setItems(typeObservableList);
    }

    public void updateTabImport(){
        searchPane_Products.setVisible(false);
        searchPane_Imports.setVisible(true);

        List<Import> imports = importRepo.getAllImport();
        importObservableList = FXCollections.observableList(imports);
        table_Import.setItems(importObservableList);

        List<Integer> idList = new ArrayList<>();
        for (Import imprt : imports){
            idList.add(imprt.getId());
        }
        idObservableList = FXCollections.observableList(idList);
        comboBox_ImportID.setItems(idObservableList);

        supplierObservableList = FXCollections.observableList(supplierRepo.getAllSupplierName());
        comboBox_Supplier.setItems(supplierObservableList);
    }

    @FXML
    void checkDetail(ActionEvent event) throws IOException {
        if (viewMode == 0){
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/app/UI/product/ProductDetail.fxml"));
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
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/app/UI/product/ImportDetail.fxml"));
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
        if (viewMode == 0){

        } else if (viewMode == 1){

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
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/app/UI/product/ProductDetail.fxml"));
            Parent root = loader.load();
            ProductDetailController productDetailController = loader.getController();
            productDetailController.initialize();


            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông tin sản phẩm");
            stage.show();
        } else if (viewMode == 1){
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/app/UI/product/ImportDetail.fxml"));
            Parent root = loader.load();
            ImportDetailController importDetailController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông tin đơn nhập");
            stage.show();
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
            int id = 0;
            try {
                id = comboBox_ProductID.getValue().intValue();
            } catch (NullPointerException e){}
            String name = textField_Name.getText();
            String type = new String();
            try {
                type = comboBox_Type.getValue();
            } catch (NullPointerException e) {}
            String brand = textField_Brand.getText();
            boolean discount = checkBox_Discount.isSelected();

            List<Product> list = productRepo.search(id, name, type, brand, discount);
            productObservableList = FXCollections.observableList(list);
            table_Product.setItems(productObservableList);

        } else if(viewMode == 1){
            int id = 0;
            try {
                id = comboBox_ImportID.getValue().intValue();
            } catch (NullPointerException e) {}
            String supplier = new String();
            try {
                supplier = comboBox_Supplier.getValue();
            } catch (NullPointerException e){}
            Date importDate = null;
            try {
                importDate = Date.valueOf(datePicker_ImportDate.getValue());
            } catch (NullPointerException e){}

            List<Import> list = importRepo.search(id, supplier, importDate);
            importObservableList = FXCollections.observableList(list);
            table_Import.setItems(importObservableList);
        }
    }


}
