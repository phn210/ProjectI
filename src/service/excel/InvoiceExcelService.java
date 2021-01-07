package service.excel;

import model.ExcelObject;
import model.entity.Invoice;
import model.entity.InvoiceDetail;
import model.form.InvoiceDetailForm;
import model.form.InvoiceForm;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class InvoiceExcelService extends ExcelService<InvoiceDetailForm>{

    public void writeToExcel(String[] titles, String filePath, List<InvoiceDetailForm> list, InvoiceForm invoiceForm){
        XSSFWorkbook wb= new XSSFWorkbook();
        XSSFSheet sheet= wb.createSheet();

        writeHeader(invoiceForm, sheet);

        int rowIndex=18;
        writeTableHeader(titles, sheet, rowIndex);
        writeTableBody(rowIndex, sheet, list);

        writeFooter(invoiceForm, sheet, rowIndex + list.size()+1);

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
    }

    protected void writeHeader(InvoiceForm invoiceForm, XSSFSheet sheet){
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(3);
        cell.setCellValue("HÓA ĐƠN BÁN HÀNG");
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 6));

        row = sheet.createRow(2);
        cell = row.createCell(3);
        cell.setCellValue(invoiceForm.getDate().toLocalDate().toString());
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 3, 6));

        row = sheet.createRow(3);
        cell = row.createCell(3);
        cell.setCellValue("Mã hóa đơn");
        sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 4));

        cell = row.createCell(5);
        cell.setCellValue(String.valueOf(invoiceForm.getId()));

        row = sheet.createRow(6);
        cell = row.createCell(1);
        cell.setCellValue("Đơn vị bán hàng:");
        cell = row.createCell(2);
        cell.setCellValue("Công ty NNN");
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 6));

        row = sheet.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("Chi nhánh:");
        cell = row.createCell(2);
        cell.setCellValue(invoiceForm.getBranch());
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 2, 6));


        row = sheet.createRow(8);
        cell = row.createCell(1);
        cell.setCellValue("Điện thoại:");
        cell = row.createCell(2);
        cell.setCellValue("0123456789");

        sheet.addMergedRegion(new CellRangeAddress(8, 8, 2, 6));


        row = sheet.createRow(9);
        cell = row.createCell(1);
        cell.setCellValue("Nhân viên bán hàng:");
        cell = row.createCell(2);
        cell.setCellValue(invoiceForm.getEmployee());
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 2, 6));


        row = sheet.createRow(11);
        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng:");
        cell = row.createCell(2);
        cell.setCellValue(invoiceForm.getCustomerName());
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 2, 6));


        row = sheet.createRow(12);
        cell = row.createCell(1);
        cell.setCellValue("SĐT:");
        cell = row.createCell(2);
        cell.setCellValue(invoiceForm.getCustomerPhone());
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 2, 6));


        row = sheet.createRow(13);
        cell = row.createCell(1);
        cell.setCellValue("Địa chỉ:");
        cell = row.createCell(2);
        cell.setCellValue(invoiceForm.getCustomerAddress());
        sheet.addMergedRegion(new CellRangeAddress(13, 13, 2, 6));


        row = sheet.createRow(14);
        cell = row.createCell(1);
        cell.setCellValue("Ghi chú:");
        cell = row.createCell(2);
        cell.setCellValue(invoiceForm.getNote());
        sheet.addMergedRegion(new CellRangeAddress(14, 14, 2, 6));

    }

    protected void writeFooter(InvoiceForm invoiceForm, XSSFSheet sheet, int rowIndex){
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue("Phương thức thanh toán");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
        cell = row.createCell(6);
        cell.setCellValue(invoiceForm.getPaymentMethod());
        rowIndex++;

        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Thuế");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
        cell = row.createCell(6);
        cell.setCellValue(invoiceForm.getTax().toString());
        rowIndex++;

        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Tổng tiền thanh toán");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
        cell = row.createCell(6);
        cell.setCellValue(invoiceForm.getTotalMoney().toString());
        rowIndex++;

        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Số tiền bằng chữ:");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 5));
        rowIndex+=2;

        row = sheet.createRow(rowIndex);
        cell = row.createCell(0);
        cell.setCellValue("Người bán hàng");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 0, 1));

        cell = row.createCell(2);
        cell.setCellValue("Kế toán");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 2, 4));

        cell = row.createCell(5);
        cell.setCellValue("Khách hàng");
        sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 5, 6));
    }

    @Override
    protected void writeTableHeader(String[] titles, XSSFSheet sheet, int rowIndex){
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue("STT");
        for(int i=0; i<titles.length;i++){
            cell = row.createCell(i+1);
            cell.setCellValue(titles[i]);
        }
    }

    @Override
    protected void writeTableBody(int rowIndex, XSSFSheet sheet, List<InvoiceDetailForm> list){
        CellRangeAddress title = new CellRangeAddress(rowIndex, rowIndex, 0, list.get(0).getFields().length);
        setBorder(BorderStyle.THIN, title, sheet);



        int id_row=rowIndex;
        int tableIndex = 0;
        for(InvoiceDetailForm invoiceDetailForm : list){
            id_row++;
            tableIndex++;
            int id_cell = 1;
            Row row= sheet.createRow(id_row);
            for(Object field: invoiceDetailForm.getFields()){
                Cell cell = row.createCell(0);
                cell.setCellValue(String.valueOf(tableIndex));

                cell= row.createCell(id_cell++);
                cell.setCellValue(field.toString());
            }
        }

        CellRangeAddress table = new CellRangeAddress(rowIndex, id_row + 5, 0, list.get(0).getFields().length);
        setBorder(BorderStyle.MEDIUM, table, sheet);

    }
}
