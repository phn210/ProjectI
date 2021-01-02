package app.controller.Products;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.entity.Import;
import model.entity.ImportDetail;
import model.entity.Product;
import repository.ImportRepo;
import repository.ProductRepo;

import java.net.URL;
import java.sql.Date;
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

    private ObservableList<Integer> idObservableList;
    private ObservableList<String> typeObservableList;
    private ObservableList<Product> productObservableList;
    private ObservableList<ImportDetail> importDetailObservableList;

    private ProductRepo productRepo;
    private ImportRepo importRepo;

    public ProductController(){
        this.productRepo = new ProductRepo();
        this.importRepo = new ImportRepo();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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

    }

    @FXML
    void selectedProduct(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event){

    }


}
