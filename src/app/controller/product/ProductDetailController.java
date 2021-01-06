package app.controller.product;

import app.controller.CommonController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.entity.*;
import model.form.ProductDetailForm;
import service.product.ProductsService;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDetailController {

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_ID;

    @FXML
    private TextField textField_Amount;

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
    private TableView<ProductDetailForm> table_ImportRecord;

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

    private int mode;

    private Product product;
    private Type type;
    private Brand brand;
    private ProductsService productsService;

    private ObservableList<ProductDetailForm> productDetailFormObservableList;
    private ObservableList<String> typeObservableList;
    private ObservableList<String> brandObservableList;

    private CommonController commonController;

    public ProductDetailController(){
        this.product = new Product();
        this.type = new Type();
        this.brand = new Brand();
        this.productsService = new ProductsService();
        this.commonController = new CommonController();
    }

    public void initialize(){
        this.mode = 0;
        textField_ID.setEditable(false);
        textField_Name.setEditable(true);
        textField_Amount.setText("0");
        textField_Amount.setEditable(false);
        textArea_Description.setEditable(true);
        comboBox_Type.setDisable(false);
        comboBox_Brand.setDisable(false);
        button_Export.setVisible(false);
        button_Delete.setVisible(false);
        button_Submit.setText("Thêm");

        col_ID.setCellValueFactory(new PropertyValueFactory<>("importID"));
        col_Supplier.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>();
        col_Date.setCellValueFactory(t -> new ReadOnlyObjectWrapper<>();
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_ImportPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));

        updateTable();
        updateType();
        updateBrand();
    }

    public void initialize(Product product){
        this.mode = 1;
        this.product = product;
        try {
            this.type = productsService.typeRepo.findByID(product.getTypeID());
            this.brand = productsService.brandRepo.findByID(product.getBrandID());
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        textField_ID.setText(String.valueOf(product.getId()));
        textField_Name.setText(product.getName());
        textField_Amount.setText(String.valueOf(product.getAmount()));
        textArea_Description.setText(product.getDescription());
        comboBox_Type.setValue(type.getName());
        comboBox_Brand.setValue(brand.getName());
        textField_Price.setText(String.valueOf(product.getRetailPrice()));
        textField_Discount.setText(String.valueOf(product.getDiscount()));

        textField_ID.setEditable(false);
        textField_Name.setEditable(false);
        textField_Amount.setEditable(false);
        textArea_Description.setEditable(true);
        comboBox_Type.setDisable(true);
        comboBox_Brand.setDisable(false);
        comboBox_Type.setDisable(false);
        button_Export.setVisible(true);
        button_Delete.setVisible(true);
        button_Submit.setText("Cập nhật");

        initTable();

        updateTable();
        updateType();
        updateBrand();
    }

    public void initTable(){
        col_ID.setCellValueFactory(new PropertyValueFactory<>("importID"));
        col_Supplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        col_Date.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        col_Amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_ImportPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));
    }

    public void updateTable(){
        List<ProductDetailForm> list = new ArrayList<>();
        try {
           list = productsService.getAllProductDetailForm(this.product);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        productDetailFormObservableList = FXCollections.observableList(list);
        table_ImportRecord.setItems(productDetailFormObservableList);
    }

    public void updateType(){
        List<String> list = new ArrayList<>();
        try {
            list = productsService.getAllTypeName();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        list.add("Thêm mới");
        typeObservableList = FXCollections.observableList(list);
        comboBox_Type.setItems(typeObservableList);
    }

    public void updateBrand(){
        List<String> list = new ArrayList<>();
        try {
            list = productsService.getAllBrandName();;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        list.add("Thêm mới");
        brandObservableList = FXCollections.observableList(list);
        comboBox_Brand.setItems(brandObservableList);
    }

    @FXML
    void delete(ActionEvent event) {
        boolean res;
        if (product.getAmount() > 0)
            res = false;
        else {
            res = productRepo.deleteProduct(product);
            if (res) {
                button_Delete.setVisible(false);
                button_Submit.setVisible(false);
            }
        }
        CommonController.resultNoti(res);
    }

    @FXML
    void handleBrand(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/products/BrandDetail.fxml"));
            Parent root = loader.load();
            BrandDetailController brandDetailController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông tin nhãn hàng");
            stage.show();

            if (this.mode == 0) {
                brandDetailController.initialize();
            } else if (this.mode == 1) {
                brandDetailController.initialize(this.brand);
            }

            stage.setOnCloseRequest(e -> updateBrand());
        }
    }

    @FXML
    void handleType(MouseEvent event) throws IOException {
        if (event.getClickCount() == 2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UI/products/TypeDetail.fxml"));
            Parent root = loader.load();
            TypeDetailController typeDetailController = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Thông tin loại sản phẩm");
            stage.show();

            if (this.mode == 0) {
                typeDetailController.initialize();
            } else if (this.mode == 1) {
                typeDetailController.initialize(this.type);
            }

            stage.setOnCloseRequest(e -> updateType());
        }
    }

    @FXML
    void export(ActionEvent event) {

    }

    @FXML
    void selectBrand(ActionEvent event) {
        int brandIndex = comboBox_Brand.getSelectionModel().getSelectedIndex();
        try {
            this.brand = productsService.brandRepo.findAll().get(brandIndex);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    @FXML
    void selectType(ActionEvent event) {
        int typeIndex = comboBox_Type.getSelectionModel().getSelectedIndex();
        try{
            this.type = productsService.typeRepo.findAll().get(typeIndex);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    void submit(ActionEvent event) {
        String name = textField_Name.getText();
        String description = textArea_Description.getText();
        String retailPrice = textField_Price.getText();
        String discount = textField_Discount.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (name.equals("") || comboBox_Brand.getSelectionModel().getSelectedItem() == null
            || comboBox_Type.getSelectionModel().getSelectedItem() == null){
            alert.setContentText("Vui lòng điền đủ thông tin!");
            alert.setHeaderText("Warning!");
            alert.show();
        } else {
            boolean res = false;
//            if (mode == 0){
//                product.setName(name);
//                product.setDescription(description);
//                if (retailPrice.equals("")) product.setRetailPrice(0.0);
//                else product.setRetailPrice(Double.parseDouble(retailPrice));
//                if (discount.equals("")) product.setDiscount(0);
//                else product.setDiscount(Integer.parseInt(discount));
//                product.setTypeID(this.type.getId());
//                product.setBrandID(this.brand.getId());
//
//                res = productRepo.addProduct(product);
//                if (res) button_Submit.setVisible(false);
//            } else if (mode == 1) {
//                product.setDescription(description);
//                if (retailPrice.equals("")) product.setRetailPrice(0.0);
//                else product.setRetailPrice(Double.parseDouble(retailPrice));
//                if (discount.equals("")) product.setDiscount(0);
//                else product.setDiscount(Integer.parseInt(discount));
//
//                res = productRepo.updateProduct(product);
//            }
            commonController.resultNoti(res);
        }
    }

}
