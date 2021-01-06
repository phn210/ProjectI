package app.controller.duty_roster;

import app.controller.CommonController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.entity.DutyRoster;
import model.entity.Employee;
import model.form.DutyRosterDetailForm;
import service.duty_roster.DutyRosterService;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class DutyRosterController implements Initializable {

    DutyRosterService dutyRosterService;

    CommonController commonController;
    @FXML
    TableView<DutyRosterDetailForm> table;

    @FXML
    TableColumn<DutyRosterDetailForm, Integer> idColumn;

    @FXML
    TableColumn<DutyRosterDetailForm, String> nameColumn;

    @FXML
    TableColumn<DutyRosterDetailForm, Date> dateColumn;

    @FXML
    TableColumn<DutyRosterDetailForm, Double> totalHoursColumn;

    @FXML
    TableColumn<DutyRosterDetailForm, String> noteColumn;

    ObservableList<DutyRosterDetailForm> dutyRosterDetailFormObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dutyRosterService = new DutyRosterService();
        commonController = new CommonController();

        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
    }

    private void initColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));
        totalHoursColumn.setCellValueFactory(new PropertyValueFactory<>("totalHours"));
    }

    private void loadData(){
        try {
            dutyRosterDetailFormObservableList = FXCollections.observableArrayList(dutyRosterService.getAllDutyRosterDetailForm());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        table.setItems(dutyRosterDetailFormObservableList);
    }

    public void addDutyRoster(){
        Dialog<ButtonType> addDutyRosterDialog = new Dialog<>();
        ButtonType acceptButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        addDutyRosterDialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField idTextField = new TextField();
        idTextField.setPromptText("Mã nhân viên");
        idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                if(!newValue.isEmpty()){
                    Integer.parseInt(newValue);
                }
            }catch (NumberFormatException ex){
                commonController.resultNoti(false, "ID không hợp lệ");
                idTextField.setText("");
            }
        });

        DatePicker datePicker = new DatePicker();
        datePicker.setConverter(commonController.getConverter());
        datePicker.setValue(LocalDate.now());

        TextField totalHoursTextField = new TextField();
        totalHoursTextField.setPromptText("Số giờ làm việc");
        totalHoursTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            try{
                if(!newValue.isEmpty()){
                    Double.parseDouble(newValue);
                }
            }catch (NumberFormatException ex){
                commonController.resultNoti(false, "Số giờ làm không hợp lệ");
                totalHoursTextField.setText("");
            }
        });

        TextField notTextField = new TextField();
        notTextField.setPromptText("Ghi chú");


        Button detailButton = new Button("Chi tiết");
        detailButton.setOnAction(event -> {
            try {
                Employee employee = dutyRosterService.getEmployee(Integer.parseInt(idTextField.getText()));
                String content = "Thông tin nhân viên: \n" +
                        "Tên: "+employee.getName()+"\n"+
                        "Số CMND: "+employee.getCitizenID()+"\n"+
                        "Số điện thoại: "+employee.getPhone()+"\n";
                commonController.resultNoti(true, content);
            } catch (SQLException throwables) {
                commonController.resultNoti(false, "ID nhân viên không chính xác");
            }
        });
        grid.add(new Label("ID nhân viên: "), 0, 0);
        grid.add(idTextField, 1, 0);
        grid.add(detailButton, 2, 0);
        grid.add(new Label("Ngày làm việc: "), 0, 1);
        grid.add(datePicker, 1, 1);
        grid.add(new Label("Số giờ làm việc: "), 0, 2);
        grid.add(totalHoursTextField, 1, 2);
        grid.add(new Label("Ghi chú: "), 0, 3);
        grid.add(notTextField, 1, 3);

        addDutyRosterDialog.getDialogPane().setContent(grid);
        Node acceptButton = addDutyRosterDialog.getDialogPane().lookupButton(acceptButtonType);
        acceptButton.setDisable(true);
        idTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });

        Optional<ButtonType> result = addDutyRosterDialog.showAndWait();
        if(result.get() == acceptButtonType){
            int id = Integer.parseInt(idTextField.getText());
            try {
                Employee employee = dutyRosterService.getEmployee(id);
                Date date = Date.valueOf(datePicker.getValue().toString());
                double totalHours = 0;
                if(!totalHoursTextField.getText().isEmpty()){
                     totalHours = Double.parseDouble(totalHoursTextField.getText());
                }
                String note = notTextField.getText();
                DutyRoster dutyRoster = new DutyRoster();
                dutyRoster.setId(id);
                dutyRoster.setTotalHour(totalHours);
                dutyRoster.setNote(note);
                dutyRoster.setDate(date);
                try{
                    dutyRosterService.addDutyRoster(dutyRoster);
                    loadData();
                }catch (SQLException exception){
                    commonController.resultNoti(false, "Ca làm việc này đã tồn tại");
                }

            } catch (SQLException throwables) {
                commonController.resultNoti(false, "ID nhân viên không tồn tại");
            }
        }
    }
}
