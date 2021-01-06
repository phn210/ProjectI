
import app.controller.CommonController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.DBConnector;

public class Main extends Application {

    public static Stage primaryStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        DBConnector myConnector = new DBConnector();
        myConnector.getConnection();
        Parent root = FXMLLoader.load(getClass().getResource("/app/UI/Login.fxml"));
        primaryStage.setTitle("Công ty NNN - Nhóm N07");
        primaryStage.setScene(new Scene(root));
        primaryStage.resizableProperty().setValue(false);
        CommonController.primaryStage = primaryStage;
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
