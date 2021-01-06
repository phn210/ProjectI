package app.controller.invoice;

import app.controller.CommonController;
import app.controller.home.HomeController;
import app.controller.product.ImportDetailController;
import app.controller.product.ProductDetailController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.entity.*;
import model.form.ImportDetailForm;
import model.form.ImportForm;
import model.form.InvoiceDetailForm;
import model.form.ProductForm;
import service.invoice.InvoicesService;
import service.product.ProductsService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FindProductController implements Initializable{

    @FXML
    private GridPane searchPane_Products;

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_Brand;

    @FXML
    private ComboBox<String> comboBox_Type;

    @FXML
    private CheckBox checkBox_Discount;

    @FXML
    private TextField textField_ProductID;

    @FXML
    private TableView<ProductForm> table_Product;

    @FXML
    private TableColumn<ProductForm, Integer> col_ID;

    @FXML
    private TableColumn<ProductForm, String> col_Name;

    @FXML
    private TableColumn<ProductForm, String> col_Type;

    @FXML
    private TableColumn<ProductForm, String> col_Brand;

    @FXML
    private TableColumn<ProductForm, Integer> col_Amount;

    @FXML
    private TableColumn<ProductForm, Double> col_Price;

    @FXML
    private TableColumn<ProductForm, Integer> col_Discount;

    private ProductForm selectedProduct;

    private ObservableList<ProductForm> productObservableList;
    private ObservableList<String> typeObservableList;
    private ProductsService productsService;
    private InvoicesService invoicesService;

    private CommonController commonController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.selectedProduct = new ProductForm();
        this.commonController = new CommonController();
        this.productsService = new ProductsService();
        this.invoicesService = new InvoicesService();

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
        col_Price.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        col_Discount.setCellValueFactory(new PropertyValueFactory<>("discount"));

    }

    public void updateTabProduct(){
        searchPane_Products.setVisible(true);
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

    @FXML
    void checkDetail(MouseEvent event) throws IOException {
        if(event.getClickCount() == 2) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/UI/product/ProductDetail.fxml"));
            Parent root = loader.load();
            ProductDetailController productDetailController = loader.getController();
            try {
                productDetailController.initializeViewOnly(table_Product.getSelectionModel().getSelectedItem());
            } catch (NullPointerException e) {
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
        }
    }

    @FXML
    void search(ActionEvent event) {
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
    }

    @FXML
    void select(ActionEvent event) {
        try {
            selectedProduct = table_Product.getSelectionModel().getSelectedItem();
        } catch(NullPointerException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Vui lòng chọn sản phẩm!");
            alert.setHeaderText("Warning!");
            alert.show();
            return;
        }

        Dialog<ButtonType> addProductDialog = new Dialog<>();

        ButtonType acceptButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        addProductDialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField textField_ID = new TextField();
        textField_ID.setText(String.valueOf(selectedProduct.getId()));
        textField_ID.setEditable(false);

        TextField textField_Amount = new TextField();
        textField_Amount.setPromptText("Số lượng");

        ComboBox<Integer> comboBox_ImportID = new ComboBox();
        try {
            comboBox_ImportID.setItems(FXCollections.observableArrayList(invoicesService.getImportIDs(selectedProduct.getId())));
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        grid.add(new Label("ID: "), 0, 0);
        grid.add(new Label("Mã lô nhập: "), 1, 0);
        grid.add(new Label("Số lượng: "), 2, 0);

        grid.add(textField_ID, 0, 1);
        grid.add(comboBox_ImportID,1,1);
        grid.add(textField_Amount, 2, 1);

        Node acceptButton = addProductDialog.getDialogPane().lookupButton(acceptButtonType);
        acceptButton.setDisable(false);
        textField_Amount.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });

        addProductDialog.getDialogPane().setContent(grid);

        Optional<ButtonType> result = addProductDialog.showAndWait();
        if (result.get() == acceptButtonType) {
            int importID = 0;
            int amount = 0;
            if(comboBox_ImportID.getValue() == null || textField_Amount.getText().isEmpty()){
                commonController.resultNoti(false, "Vui lòng nhập đủ thông tin");
            } else {
                importID = comboBox_ImportID.getValue();
                try {
                    amount = Integer.parseInt(textField_Amount.getText());
                } catch (NumberFormatException numberFormatException) {
                    commonController.resultNoti(false, "Số lượng không hợp lệ");
                }
                if (amount == 0) {
                    commonController.resultNoti(false, "Số lượng không hợp lệ");
                }
                if (amount > selectedProduct.getAmount()) {
                    commonController.resultNoti(false, "Không còn đủ sản phẩm");
                } else {
                    try {
                        ImportDetail importDetail = invoicesService.importDetailRepo.findByID(selectedProduct.getId(), importID);
                        Product product = invoicesService.productRepo.findByID(selectedProduct.getId());
                        InvoiceDetailForm invoiceDetailForm = new InvoiceDetailForm(importDetail, product, amount);
                        InvoiceDetailController.newInvoiceDetailForm = invoiceDetailForm;
                        commonController.resultNoti(true, "Chọn sản phẩm thành công");
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.fireEvent(
                                new WindowEvent(((Node) event.getSource()).getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST)
                        );
                    } catch (SQLException ex) {
                        commonController.resultNoti(false, "Có lỗi xảy ra!");
                    }
                }
            }
        }

    }

}
