package app.controller.product;

import app.controller.CommonController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.entity.Brand;
import repository.BrandRepo;
import service.product.ProductsService;

import java.sql.SQLException;

public class BrandDetailController {

    @FXML
    private Button button_Submit;

    @FXML
    private TextField textField_ID;

    @FXML
    private TextField textField_Name;

    @FXML
    private TextField textField_Country;

    private int mode;

    private Brand brand;
    private ProductsService productsService;
    private CommonController commonController;

    public BrandDetailController(){
        this.brand = new Brand();
        this.productsService = new ProductsService();
        this.commonController = new CommonController();
    }

    public void initialize(){
        this.mode = 0;
        textField_ID.setEditable(false);
        textField_Name.setEditable(true);
        textField_Country.setEditable(true);

        button_Submit.setText("Thêm");
    }

    public void initialize(Brand brand){
        this.mode = 1;
        this.brand = brand;

        textField_ID.setText(String.valueOf(brand.getId()));
        textField_Name.setText(brand.getName());
        textField_Country.setText(brand.getCountry());

        textField_ID.setEditable(false);
        textField_Name.setEditable(true);
        textField_Country.setEditable(true);

        button_Submit.setText("Cập nhật");
    }

    @FXML
    void submit(ActionEvent event) {
        int id = Integer.parseInt(textField_ID.getText().trim());
        String name = textField_Name.getText();
        String country = textField_Country.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (name.equals("") || country.equals("")){
            alert.setContentText("Vui lòng điền đủ thông tin!");
            alert.setHeaderText("Warning!");
            alert.show();
            return;
        } else {
            try {
                int res = 0;
                this.brand.setName(name);
                this.brand.setCountry(country);
                if (mode == 0) {
                    res = productsService.brandRepo.insert(brand);
                    if (res > 0) {
                        button_Submit.setVisible(false);
                        commonController.resultNoti(true);
                    } else commonController.resultNoti(false);

                } else if (mode == 1) {
                    res = productsService.brandRepo.update(brand);
                    if (res > 0) commonController.resultNoti(true);
                    else commonController.resultNoti(false);
                }
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
            }

        }
    }

}
