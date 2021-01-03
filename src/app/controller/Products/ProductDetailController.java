package app.controller.Products;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.entity.*;
import repository.*;

import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.List;

public class ProductDetailController {

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_ID;

    @FXML
    private ComboBox<String> comboBox_Type;

    @FXML
    private ComboBox<String> comboBox_Brand;

    @FXML
    private TextField textField_Price;

    @FXML
    private TextField textField_Discount;

    @FXML
    private TextArea textArea_Description;

    @FXML
    private TableView<ImportDetail> table_ImportRecord;

    @FXML
    private TableColumn<ImportDetail, Integer> col_ID;

    @FXML
    private TableColumn<ImportDetail, String> col_Supplier;

    @FXML
    private TableColumn<ImportDetail, Date> col_Date;

    @FXML
    private TableColumn<ImportDetail, Integer> col_Amount;

    @FXML
    private TableColumn<ImportDetail, Double> col_ImportPrice;

    @FXML
    private Button button_Delete;

    @FXML
    private Button button_Submit;

    @FXML
    private Button button_Export;

    private Product product;
    private Type type;
    private Brand brand;
    private ProductRepo productRepo;
    private ImportRepo importRepo;
    private TypeRepo typeRepo;
    private BrandRepo brandRepo;
    private SupplierRepo supplierRepo;

    private ObservableList<ImportDetail> importDetailObservableList;
    private ObservableList<String> typeObservableList;
    private ObservableList<String> brandObservableList;

    public ProductDetailController(){
        this.product = new Product();
        this.type = new Type();
        this.brand = new Brand();
        this.productRepo = new ProductRepo();
        this.importRepo = new ImportRepo();
        this. typeRepo = new TypeRepo();
        this.brandRepo = new BrandRepo();
        this.supplierRepo = new SupplierRepo();
    }

    public void initialize(){
        textField_ID.setEditable(false);
        textField_Name.setEditable(true);
        textArea_Description.setEditable(true);
        comboBox_Type.arm();
        comboBox_Brand.arm();
        button_Export.setVisible(false);
        button_Delete.setVisible(false);
        button_Submit.setText("Thêm");

        col_ID.setCellValueFactory(new PropertyValueFactory<>("importID"));
        col_Supplier.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(supplierRepo.getSupplier(importRepo.getImport(t.getValue())).getName()));
        col_Date.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(importRepo.getImport(t.getValue()).getImportDate()));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_ImportPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));

        updateTable();
        updateType();
        updateBrand();
    }

    public void initialize(Product product){
        this.product = product;

        textField_ID.setText(String.valueOf(product.getId()));
        textField_Name.setText(product.getName());
        textArea_Description.setText(product.getDescription());
        comboBox_Type.setValue(typeRepo.getType(product).getName());
        comboBox_Brand.setValue(brandRepo.getBrand(product).getName());
        textField_Price.setText(String.valueOf(product.getRetailPrice()));
        textField_Discount.setText(String.valueOf(product.getDiscount()));

        textField_ID.setEditable(false);
        textField_Name.setEditable(false);
        textArea_Description.setEditable(true);
        comboBox_Type.disarm();
        comboBox_Brand.disarm();
        button_Export.setVisible(true);
        button_Delete.setVisible(true);
        button_Submit.setText("Cập nhật");

        col_ID.setCellValueFactory(new PropertyValueFactory<>("importID"));
        col_Supplier.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(supplierRepo.getSupplier(importRepo.getImport(t.getValue())).getName()));
        col_Date.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>(importRepo.getImport(t.getValue()).getImportDate()));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_ImportPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));

        updateTable();
        updateType();
        updateBrand();
    }

    public void updateTable(){
        List<ImportDetail> list = productRepo.getImportDetails(this.product);
        importDetailObservableList = FXCollections.observableList(list);
        table_ImportRecord.setItems(importDetailObservableList);
    }

    public void updateType(){
        List<String> list = typeRepo.getAllTypeName();
        list.add("Thêm mới");
        typeObservableList = FXCollections.observableList(list);
        comboBox_Type.setItems(typeObservableList);
    }

    public void updateBrand(){
        List<String> list = brandRepo.getAllBrandName();
        list.add("Thêm mới");
        brandObservableList = FXCollections.observableList(list);
        comboBox_Brand.setItems(brandObservableList);
    }

    @FXML
    void delete(ActionEvent event) {
        
    }

    @FXML
    void detailBrand(MouseEvent event) {

    }

    @FXML
    void detailType(MouseEvent event) {

    }

    @FXML
    void export(ActionEvent event) {

    }

    @FXML
    void selectBrand(ActionEvent event) {
        int brandIndex = comboBox_Brand.getSelectionModel().getSelectedIndex();
        this.brand = brandRepo.getAllBrand().get(brandIndex);
    }

    @FXML
    void selectType(ActionEvent event) {
        int typeIndex = comboBox_Type.getSelectionModel().getSelectedIndex();
        this.type = typeRepo.getAllType().get(typeIndex);
    }

    @FXML
    void submit(ActionEvent event) {

    }

}
