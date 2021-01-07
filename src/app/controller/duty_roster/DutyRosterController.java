package app.controller.duty_roster;

import app.controller.CommonController;
import app.controller.employee.EmployeeDetailController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.entity.DutyRoster;
import model.entity.Employee;
import model.form.DutyRosterDetailForm;
import model.form.EmployeeDetailForm;
import service.duty_roster.DutyRosterService;
import service.excel.ExcelService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;
import java.util.ResourceBundle;

public class DutyRosterController implements Initializable {

    DutyRosterService dutyRosterService;

    CommonController commonController;

    ExcelService<DutyRosterDetailForm> excelService;
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

    String[] titles;

    ObservableList<DutyRosterDetailForm> dutyRosterDetailFormObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dutyRosterService = new DutyRosterService();
        commonController = new CommonController();

        excelService = new ExcelService<>();
        titles = new String[]{"Họ và tên", "Ngày làm việc", "Số giờ làm", "Ghi chú"};

        initTable();
        loadData();
    }

    private void initTable(){
        initColumns();
        table.setRowFactory(RowFactory -> {
            TableRow<DutyRosterDetailForm> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    DutyRosterDetailForm rowData = row.getItem();
                    Dialog<ButtonType> editDialog = new Dialog<>();
                    String header = "Sửa ca làm việc ngày "+rowData.getDate()+" của nhân viên có ID: "+rowData.getEmployeeId();
                    editDialog.setHeaderText(header);
                    ButtonType acceptButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
                    ButtonType cancelButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
                    editDialog.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType);


                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(20, 150, 10, 10));

                    TextField totalHoursTextField = new TextField();
                    totalHoursTextField.setPromptText("Số giờ làm việc");
                    totalHoursTextField.textProperty().addListener((observable, oldValue, newValue) -> {
                        try{
                            if(!newValue.isEmpty()){
                                double hours = Double.parseDouble(newValue);
                                if(hours > 24){
                                    commonController.resultNoti(false, "Số giờ không thể lớn hơn 24 giờ");
                                    totalHoursTextField.setText("");
                                }
                            }
                        }catch (NumberFormatException ex){
                            commonController.resultNoti(false, "Số giờ làm không hợp lệ");
                            totalHoursTextField.setText("");
                        }
                    });


                    TextField noteTextField = new TextField();
                    noteTextField.setPromptText("Ghi chú");

                    grid.add(new Label("Số giờ làm việc: "), 0, 0);
                    grid.add(totalHoursTextField, 1, 0);
                    grid.add(new Label("Ghi chú: "), 0, 1);
                    grid.add(noteTextField, 1, 1);
                    editDialog.getDialogPane().setContent(grid);

                    editDialog.getDialogPane().setContent(grid);
                    Optional<ButtonType> result = editDialog.showAndWait();
                    if(result.get() == acceptButtonType){
                        double totalHours = 0;
                        if(!totalHoursTextField.getText().isEmpty()){
                            totalHours = Double.parseDouble(totalHoursTextField.getText());
                        }
                        String note = noteTextField.getText();
                        DutyRoster dutyRoster = new DutyRoster();
                        dutyRoster.setId(rowData.getEmployeeId());
                        dutyRoster.setDate(rowData.getDate());
                        dutyRoster.setNote(note);
                        dutyRoster.setTotalHour(totalHours);
                        try {
                            dutyRosterService.updateDutyRoster(dutyRoster);
                            loadData();
                        } catch (SQLException exception) {
                            exception.printStackTrace();
                        }
                    }
                }
            });
            return row;
        });
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
                    double hours = Double.parseDouble(newValue);
                    if(hours > 24){
                        commonController.resultNoti(false, "Số giờ không thể lớn hơn 24 giờ");
                        totalHoursTextField.setText("");
                    }
                }
            }catch (NumberFormatException ex){
                commonController.resultNoti(false, "Số giờ làm không hợp lệ");
                totalHoursTextField.setText("");
            }
        });

        TextField noteTextField = new TextField();
        noteTextField.setPromptText("Ghi chú");


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
        grid.add(noteTextField, 1, 3);

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
                Date startDate = employee.getStartDay();
                Date date = Date.valueOf(datePicker.getValue().toString());
                if(date.before(startDate)){
                    commonController.resultNoti(false, "Ca làm việc không thể có trước ngày vào làm việc");
                    return;
                }
                double totalHours = 0;
                if(!totalHoursTextField.getText().isEmpty()){
                     totalHours = Double.parseDouble(totalHoursTextField.getText());
                }
                String note = noteTextField.getText();
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

    public void calculateSalary(){
        Dialog<ButtonType> chooseDate = new Dialog<>();
        ButtonType acceptButtonType = new ButtonType("Xác nhận", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Thoát", ButtonBar.ButtonData.CANCEL_CLOSE);
        chooseDate.getDialogPane().getButtonTypes().addAll(acceptButtonType, cancelButtonType);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        LocalDate now = LocalDate.now();

        ComboBox<Integer> monthComboBox = new ComboBox<>();
        ObservableList<Integer> monthList = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12);
        int nowMonth = now.getMonthValue();
        monthComboBox.setItems(monthList);
        monthComboBox.setValue(nowMonth);

        ComboBox<Integer> yearComboBox = new ComboBox<>();
        ObservableList<Integer> yearList = FXCollections.observableArrayList();
        int nowYear = now.getYear();

        for (int i = 2010; i <= nowYear; i++) {
            yearList.add(i);
        }
        yearComboBox.setItems(yearList);
        yearComboBox.setValue(nowYear);

        grid.add(new Label("Tháng: "), 0, 0);
        grid.add(monthComboBox, 1, 0);
        grid.add(new Label("Năm: "), 0, 1);
        grid.add(yearComboBox, 1, 1);

        chooseDate.getDialogPane().setContent(grid);
        Optional<ButtonType> result = chooseDate.showAndWait();
        if(result.get() == acceptButtonType){
            int month = monthComboBox.getValue();
            int year = yearComboBox.getValue();
            if(year == nowYear && month >= nowMonth){
                commonController.resultNoti(false, "Tháng và năm không hợp lệ");
            }else{
                Calendar calendar = new GregorianCalendar(year, month-1, 1);
                int numberOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//                LocalDate date = new LocalDate(year, month, numberOfDays);
                LocalDate localDate = LocalDate.of(year, month, numberOfDays);
                SalaryTableController.localDate = localDate;
                Stage stage = new Stage();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("../../UI/duty_roster/SalaryTable.fxml"));
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void exportFile(){
        String filePath = commonController.chooseDirectory();
        Calendar calendar = Calendar.getInstance();
        String fileName = "DutyRoster_"+ calendar.getTimeInMillis()+".xlsx";
        filePath += "\\"+fileName;
        excelService.writeToExcel(titles, filePath, dutyRosterDetailFormObservableList);
    }
}
