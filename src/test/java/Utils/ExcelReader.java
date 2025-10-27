package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    public static FileInputStream fi;
    public static FileOutputStream fo;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public static XSSFCell cell;
    public static XSSFCellStyle style;

    public static int getRowCount(String xlfile, String xlsheet) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }

    public static int getCellCount(String xlffile, String xlfsheet, int rownum) throws IOException {
        fi = new FileInputStream(xlffile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlfsheet);
        row = ws.getRow(rownum);
        int cellCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return cellCount;
    }

    public static String getCellData(String xlffile, String xlfsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlffile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlfsheet);
        row = ws.getRow(rownum);
        cell = row.getCell(colnum);
        String data;
        try {
            data = cell.toString();
        } catch (Exception e) {
            data = "";
        }
        wb.close();
        fi.close();
        return data;
    }

    /*
     * reading and writing the data, this is generally to read anything from cell
     * and update (ex: updating results if passes/failed). [suppose if your last
     * column is for updating pass/fail then i will first read the existing sttaus
     * then i will put whatever i want]
     */
    public static void setCellData(String xlffile, String xlfsheet, int rownum, int colnum, String data)
            throws IOException {
        fi = new FileInputStream(xlffile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlfsheet);
        row = ws.getRow(rownum); // taking existing row num

        cell = row.createCell(colnum);
        cell.setCellValue(data);

        fo = new FileOutputStream(xlffile); // fo is for writing & fi is for reading. now i am opening file for writing
                                            // and writing with wb.write() method
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public static void fillGreenColor(String xlffile, String xlfsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlffile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlfsheet);
        row = ws.getRow(rownum);

        style = wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(xlffile); // fo is for writing & fi is for reading. now i am opening file for writing
                                            // and writing with wb.write() method
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

    public static void fillRedColor(String xlffile, String xlfsheet, int rownum, int colnum) throws IOException {
        fi = new FileInputStream(xlffile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlfsheet);
        row = ws.getRow(rownum);

        style = wb.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fo = new FileOutputStream(xlffile); // fo is for writing & fi is for reading. now i am opening file for writing
                                            // and writing with wb.write() method
        wb.write(fo);
        wb.close();
        fi.close();
        fo.close();
    }

}
