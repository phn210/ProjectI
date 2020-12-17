package app.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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

    public static void main(String[] args) {
        launch(args);
    }

}
