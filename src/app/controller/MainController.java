package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    void toCustomerTab(ActionEvent event) {

    }

    @FXML
    void toDutyTab(ActionEvent event) {

    }

    @FXML
    void toEmployeeTab(ActionEvent event) {

    }

    @FXML
    void toHistoryTab(ActionEvent event) throws FileNotFoundException {
        Pane view = Main.getView("invoices/Invoices");
        mainPane.setBottom(view);
    }

    @FXML
    void toProductTab(ActionEvent event) throws FileNotFoundException {
        Pane view = Main.getView("products/Products");
        mainPane.setBottom(view);
    }

    @FXML
    void toRevenueTab(ActionEvent event) {

    }

}
