package app.controller.login;

import app.controller.CommonController;
import app.controller.home.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entity.Account;
import repository.AccountRepo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    CommonController commonController;

    AccountRepo accountRepo;

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
        accountRepo = new AccountRepo();
        loginButton.setDisable(true);
        usernameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });
    }

    public void login(ActionEvent event){
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        try {
            Account account = accountRepo.login(username, password);
            if(account != null){
                Stage stage = (Stage)(((Node) event.getSource()).getScene().getWindow());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("..\\..\\UI\\home\\HomeUI.fxml"));
                try {
                    Parent root = loader.load();
                    HomeController homeController = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                commonController.resultNoti(false,"Tài khoản hoặc mật khẩu không chính xác");
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
            commonController.resultNoti(false,"Tài khoản hoặc mật khẩu không chính xác");
        }
    }
}
