package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static List<Map<String, String>> ReadExcelAsMap(String filePath, String sheetName) throws IOException {
        List<Map<String, String>> dataList = new ArrayList<>();
        try (
                FileInputStream file = new FileInputStream(filePath);
                XSSFWorkbook wb = new XSSFWorkbook(file)) {

            XSSFSheet ws = wb.getSheet(sheetName);

            if (ws == null) {
                throw new RuntimeErrorException(null, sheetName + " not found");
            }

            // Read header row
            Row headerRow = ws.getRow(0);
            int columnCount = headerRow.getLastCellNum();

            // Read data rows
            for (int r = 1; r <= ws.getLastRowNum(); r++) {
                Row row = ws.getRow(r);
                if (row == null)
                    continue;

                Map<String, String> rowData = new HashMap<>();
                for (int c = 0; c < columnCount; c++) {
                    Cell headerCell = headerRow.getCell(c);
                    Cell dataCell = row.getCell(c);

                    String header = headerCell != null ? headerCell.toString().trim() : "Column" + c;
                    String value = dataCell != null ? dataCell.toString().trim() : "";

                    rowData.put(header, value);
                }
                dataList.add(rowData);
            }
        }

        catch (Exception e) {
            throw new RuntimeException("error reading file: " + e.getMessage(), e);
        }
        return dataList;
    }
}