package app.controller.Products;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.entity.Import;
import model.entity.ImportDetail;

import java.awt.event.MouseEvent;

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
    private TableView<Import> table_ImportRecord;

    @FXML
    private TableColumn<ImportDetail, Integer> col_ID;

    @FXML
    private TableColumn<ImportDetail, ?> col_Supplier;

    @FXML
    private TableColumn<?, ?> col_Date;

    @FXML
    private TableColumn<?, ?> col_Amount;

    @FXML
    private TableColumn<?, ?> col_ImportPrice;

    @FXML
    private Button button_Delete;

    @FXML
    private Button button_Submit;

    @FXML
    private Button button_Export;

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

    }

    @FXML
    void selectType(ActionEvent event) {

    }

    @FXML
    void submit(ActionEvent event) {

    }

}
