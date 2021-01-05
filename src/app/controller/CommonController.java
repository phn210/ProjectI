package app.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class CommonController {
    public static Stage primaryStage;

    public Scene makeScene(String fxmlpath){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(fxmlpath));
            return new Scene(root);
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    public void switchScene(Scene scene){
        try{
            primaryStage.setScene(scene);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static void resultNoti(boolean res){
        if (res){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thao tác thành công!");
            alert.setHeaderText("Completed!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Có lỗi xảy ra, thay đổi không thể thực hiện!");
            alert.setHeaderText("Error!");
            alert.show();
        }
    }

    public static void resultNoti(boolean res, String content){
        if (res){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(content);
            alert.setHeaderText("Completed!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(content);
            alert.setHeaderText("Error!");
            alert.show();
        }
    }

    public  void toHome(){
        switchScene(makeScene("../UI/home/HomeUI.fxml"));
    }

    public void toProductTab(){
        switchScene(makeScene("../UI/products/Products.fxml"));
    }

    public void toCustomerTab(){

    }

    public void toEmployeeTab(){

    }

    public void toDutyTab(){

    }

    public void toInvoiceTab(){

    }

    public void toRevenueTab(){

    }
}
