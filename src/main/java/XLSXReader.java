import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XLSXReader {

    public static List<Record> readXLSX(String fileName) throws IOException {
        List<Record> recordList = new ArrayList<Record>();
        File excelFile = new File(fileName);
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);

        // we iterate on rows
        Iterator<Row> rowIt = sheet.iterator();
        Row row = rowIt.next();

        for (int i = 0; i < 1540; i++) {
            row = rowIt.next();

            recordList.add(new Record(
                    (int) row.getCell(3).getNumericCellValue(),
                    row.getCell(4).getNumericCellValue()/1000.0,
                    row.getCell(5).getNumericCellValue()/1000.0,
                    row.getCell(6).getNumericCellValue()/1000.0,
                    row.getCell(7).getNumericCellValue()/1000.0
            ));
        }
        workbook.close();
        fis.close();
        return recordList;
    }

    public static void writeToXLSX(String fileName, List<Double> results) throws IOException {
        File excelFile = new File(fileName);
        FileInputStream fis = new FileInputStream(excelFile);

        // we create an XSSF Workbook object for our XLSX Excel File
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        // we get first sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIt = sheet.iterator();
        Row row = rowIt.next();
        for (int i = 0; i < 3080; i = i + 2) {
            row = rowIt.next();
            row.createCell(13).setCellValue(results.get(i));
            row.createCell(14).setCellValue(results.get(i + 1));
        }
        fis.close();
        FileOutputStream outputStream = new FileOutputStream(fileName);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}



