package app.controller;

import app.controller.home.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.entity.Account;

import java.io.IOException;
import java.net.URL;

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

    public void resultNoti(boolean res){
        if (res){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Thao tác thành công!");
            alert.setHeaderText("Completed!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Có lỗi xảy ra, thay đổi không thể thực hiện!");
            alert.setHeaderText("Error!");
            alert.show();
        }
    }

    public void resultNoti(boolean res, String content){
        if (res){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(content);
            alert.setHeaderText("Completed!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(content);
            alert.setHeaderText("Error!");
            alert.show();
        }
    }

    public  void toHome(){
        switchScene(makeScene("../UI/home/HomeUI.fxml"));
    }

    public Pane getPane(String fileName) throws IOException {
        Pane pane;
        URL fileURL = getClass().getResource("../UI/" + fileName);
        FXMLLoader loader = new FXMLLoader();
        pane = loader.load(fileURL);
        return pane;
    }
}
