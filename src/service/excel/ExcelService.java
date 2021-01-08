package service.excel;

import model.ExcelObject;
import model.entity.Customer;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class ExcelService<T> {

    public void writeToExcel(String[] titles, String filePath, List<T> list){
        XSSFWorkbook wb= new XSSFWorkbook();
        XSSFSheet sheet= wb.createSheet();
        int rowIndex=0;
        writeTableHeader(titles, sheet, rowIndex);
        writeTableBody(rowIndex, sheet, list);

        for(int columnIndex=0; columnIndex<=10; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
        try{
            FileOutputStream fos= new FileOutputStream(new File(filePath));
            wb.write(fos);
            fos.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    };

    protected void writeTableHeader(String[] titles, XSSFSheet sheet, int rowIndex){
        Row row= sheet.createRow(rowIndex);
        for(int i=0; i<titles.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }
    }

    protected void writeTableBody(int rowIndex, XSSFSheet sheet, List<T> list){
        int id_row=rowIndex;
        for(T object : list){
            id_row++;
            int id_cell = 0;
            Row row= sheet.createRow(id_row);
            for(Object field: ((ExcelObject)object).getFields()){
                Cell cell= row.createCell(id_cell++);
                if(field == null){
                    cell.setCellValue("");
                }else{
                    cell.setCellValue(field.toString());
                }
            }
        }
    }

    protected void setBorder(BorderStyle borderStyle, CellRangeAddress cellRangeAddress, XSSFSheet sheet){
        RegionUtil.setBorderBottom(borderStyle, cellRangeAddress, sheet);
        RegionUtil.setBorderTop(borderStyle, cellRangeAddress, sheet);
        RegionUtil.setBorderLeft(borderStyle, cellRangeAddress, sheet);
        RegionUtil.setBorderRight(borderStyle, cellRangeAddress, sheet);
    }

}
