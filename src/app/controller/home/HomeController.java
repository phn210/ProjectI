package app.controller.home;

import app.controller.CommonController;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    private CommonController commonController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commonController = new CommonController();
    }

    public void toProductTab(){
        commonController.toProductTab();
    }

    public void toCustomerTab(){
        commonController.toCustomerTab();
    }

    public void toEmployeeTab(){
        commonController.toEmployeeTab();
    }

    public void toDutyTab(){
        commonController.toDutyTab();
    }

    public void toInvoiceTab(){
        commonController.toInvoiceTab();
    }

    public void toRevenueTab(){
        commonController.toRevenueTab();
    }

}
