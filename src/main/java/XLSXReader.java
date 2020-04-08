import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
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

        while (rowIt.hasNext()) {
            row = rowIt.next();
            // iterate on cells for the current row
            //Iterator<Cell> cellIterator = row.cellIterator();
            recordList.add(new Record(
                    (int) row.getCell(3).getNumericCellValue(),
                    row.getCell(4).getNumericCellValue(),
                    row.getCell(5).getNumericCellValue(),
                    row.getCell(6).getNumericCellValue(),
                    row.getCell(7).getNumericCellValue()
            ));
        }
        workbook.close();
        fis.close();
        return recordList;
    }
}



