package app.controller.product;

import app.controller.CommonController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entity.Brand;
import model.entity.Supplier;
import service.product.ProductsService;

import java.sql.SQLException;

public class SupplierDetailController {

    @FXML
    private Button button_Submit;

    @FXML
    private TextField textField_ID;

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_Address;

    @FXML
    private TextField textField_Phone;

    private int mode;

    private Supplier supplier;
    private ProductsService productsService;
    private CommonController commonController;

    public SupplierDetailController(){
        this.supplier = new Supplier();
        this.productsService = new ProductsService();
        this.commonController = new CommonController();
    }

    public void initialize(){
        this.mode = 0;
        button_Submit.setText("Thêm");
    }

    public void initialize(Supplier supplier){
        this.mode = 1;
        this.supplier = supplier;

        textField_ID.setText(String.valueOf(supplier.getId()));
        textField_Name.setText(supplier.getName());
        textField_Address.setText(supplier.getAddress());
        textField_Phone.setText(supplier.getPhone());

        button_Submit.setText("Cập nhật");
    }

    @FXML
    void submit(ActionEvent event) {
        int id = Integer.parseInt(textField_ID.getText().trim());
        String name = textField_Name.getText();
        String address = textField_Address.getText();
        String phone = textField_Phone.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (name.equals("") || address.equals("") || phone.equals("")){
            alert.setContentText("Vui lòng điền đủ thông tin!");
            alert.setHeaderText("Warning!");
            alert.show();
            return;
        } else {
            try {
                int res = 0;
                this.supplier.setName(name);
                this.supplier.setAddress(address);
                this.supplier.setPhone(phone);
                if (mode == 0) {
                    res = productsService.supplierRepo.insert(supplier);
                    if (res > 0) {
                        button_Submit.setVisible(false);
                        commonController.resultNoti(true);
                    } else commonController.resultNoti(false);

                } else if (mode == 1) {
                    res = productsService.supplierRepo.update(supplier);
                    if (res > 0) commonController.resultNoti(true);
                    else commonController.resultNoti(false);
                }
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
            }

        }
    }

}
