package service.excel;

import model.entity.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;

public abstract class BaseExcel<T> {
    private String[] titles;
    private ArrayList<T> objects;
    private String exportPath;
    private String importPath;

    public BaseExcel() {
    }


    protected abstract void writeToExcel();

    protected abstract void writeHeader();

    protected void writeTableHeader(String[] strings, XSSFSheet sheet, int rowIndex){
        Row row= sheet.createRow(rowIndex);
        for(int i=0; i<strings.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(strings[i]);
        }
    }

    protected void writeTableBody(int rowIndex, XSSFSheet sheet){
        int id_row=rowIndex;
        for(T object : objects){
            id_row++;
            int id_cell = 0;
            Row row= sheet.createRow(id_row);
            for(Object field: getFields(object)){
                Cell cell= row.createCell(id_cell++);
                cell.setCellValue(field.toString());
            }
        }
    }
    protected abstract Object[] getFields(T t);
//        return new Object[]{customer.getId(), customer.getName(), customer.getPhone(), customer.getAddress(), customer.getEmail()};

    public void exportFile(){

    }
}
