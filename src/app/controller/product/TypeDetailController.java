package app.controller.product;

import app.controller.CommonController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.entity.Type;
import repository.TypeRepo;
import service.product.ProductsService;

import java.sql.SQLException;

/*
    Chưa xóa được type
*/

public class TypeDetailController {
    @FXML
    private Button button_Submit;

    @FXML
    private TextField textField_ID;

    @FXML
    private TextField textField_Name;

    @FXML
    private TextArea textField_Description;

    private int mode;
    private Type type;
    private ProductsService productsService;
    private CommonController commonController;

    public TypeDetailController(){
        this.type = new Type();
        this.productsService = new ProductsService();
        this.commonController = new CommonController();
    }

    public void initialize(){
        this.mode = 0;
        textField_ID.setEditable(false);
        textField_Name.setEditable(true);
        textField_Description.setEditable(true);

        button_Submit.setText("Thêm");
    }

    public void initialize(Type type){
        this.mode = 1;
        this.type = type;
        textField_ID.setText(String.valueOf(type.getId()));
        textField_Name.setText(type.getName());
        textField_Description.setText(type.getDescription());

        textField_ID.setEditable(false);
        textField_Name.setEditable(true);
        textField_Description.setEditable(true);

        button_Submit.setText("Cập nhật");
    }

    @FXML
    void submit(ActionEvent event) {
        int id = Integer.parseInt(textField_ID.getText().trim());
        String name = textField_Name.getText();
        String description = textField_Description.getText();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (name.equals("")){
            alert.setContentText("Vui lòng điền đủ thông tin!");
            alert.setHeaderText("Warning!");
            alert.show();
            return;
        } else {
            int res = 0;
            this.type.setName(name);
            this.type.setDescription(description);
            try {
                if (mode == 0) {
                    res = productsService.typeRepo.insert(type);
                    if (res > 0) {
                        button_Submit.setVisible(false);
                        commonController.resultNoti(true);
                    } else commonController.resultNoti(false);
                } else if (mode == 1) {
                    res = productsService.typeRepo.update(type);
                    if (res > 0) commonController.resultNoti(true);
                    else commonController.resultNoti(false);
                }
            } catch (SQLException sqlException){
                sqlException.printStackTrace();
            }
        }
    }
}
