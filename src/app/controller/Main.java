package app.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.net.URL;

public class Main extends Application {

    public static Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/app/UI/Main.fxml"));

        this.primaryStage.setTitle("Cong ty NNN - Nhom N07");
        this.primaryStage.setScene(new Scene(root));
        this.primaryStage.resizableProperty().setValue(false);
        this.primaryStage.show();
    }

    public static Pane getView(String fileName) throws FileNotFoundException {
        Pane view = new Pane();
        try {
            URL fileURL = Main.class.getResource("/app/UI/" + fileName + ".fxml");
            if(fileURL == null)
                throw new java.io.FileNotFoundException("FXML file can't be found!");
            FXMLLoader loader = new FXMLLoader();
            view = loader.load(fileURL);
        }
        catch (Exception e) {
            System.out.println("Can't file fxml page named " + "/app/UI/" + fileName + ".fxml" + "!");
            e.printStackTrace();
        }
        return view;
    }

    public static void changeScene(Stage primaryStage, String fileName) throws Exception{
        Parent root = getView(fileName);
        primaryStage.setTitle("Phần mềm quản lý thư viện - 20183602 - Phạm Hồ Nguyên");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void exportNoti(boolean res){
        if (res){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Xuất file thành công!");
            alert.setHeaderText("Completed!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Có lỗi xảy ra, không thể xuất file!");
            alert.setHeaderText("Error!");
            alert.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
