package app.controller.Products;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.Import;
import model.entity.Product;
import repository.ImportRepo;
import repository.ProductRepo;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {

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
    private TextField textField_Name;

    @FXML
    private TextField textField_Brand;

    @FXML
    private ComboBox<Integer> comboBox_ID;

    @FXML
    private ComboBox<String> comboBox_Type;

    @FXML
    private CheckBox checkBox_Discount;

    //mode = 0: product
    //mode = 1: import
    private int mode;

    private ObservableList<Integer> idObservableList;
    private ObservableList<String> typeObservableList;
    private ObservableList<Product> productObservableList;
    private ObservableList<Import> importObservableList;

    private ProductRepo productRepo;
    private ImportRepo importRepo;

    public ProductController(){
        this.productRepo = new ProductRepo();
        this.importRepo = new ImportRepo();
        this.mode = 0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col_ID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_Type.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(productRepo.getType(t.getValue()).getName()));
        col_Brand.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(productRepo.getBrand(t.getValue()).getName()));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_Description.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_Price.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        col_Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));

        col_ImportID.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_Supplier.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        col_TotalMoney.setCellValueFactory(new PropertyValueFactory<>("totalMoney"));

        this.updateTableProduct();
    }

    public void updateTableProduct(){
        List<Product> products = productRepo.getAllProduct();
        productObservableList = FXCollections.observableList(products);
        table_Product.setItems(productObservableList);
    }

    public void updateTableImport(){
        List<Import> imports = importRepo.getAllImport();
        importObservableList = FXCollections.observableList(imports);
        table_Import.setItems(importObservableList);
    }

    @FXML
    void checkDetail(ActionEvent event) {

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

    }

    @FXML
    void selectedImport(ActionEvent event) {
        this.mode = 1;
    }

    @FXML
    void selectedProduct(ActionEvent event) {
        this.mode = 0;
    }

    @FXML
    void search(ActionEvent event){
        
    }


}
