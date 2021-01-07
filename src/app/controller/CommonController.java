package app.controller;

import app.controller.home.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.entity.Account;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CommonController {
    public static Stage primaryStage;

    private StringConverter<LocalDate> converter;

    public CommonController() {
        String pattern = "dd-MM-yyyy";
        converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
    }

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
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            primaryStage.setScene(scene);
            primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
            primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
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

    public boolean confirmAlert(String content){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(content);
        alert.setHeaderText("Confirm!");
        ButtonType xacNhanButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType thoatButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(xacNhanButtonType, thoatButtonType);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == xacNhanButtonType){
            return true;
        }
        return false;
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

    public StringConverter<LocalDate> getConverter() {
        return converter;
    }
}
