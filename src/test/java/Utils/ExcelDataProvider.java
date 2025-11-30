package Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "loginDataExcel")
    public static Object[][] getLoginData_excel() throws IOException {
        List<Map<String, String>> dataList = ExcelUtil.ReadExcelAsMap(
                ".\\test-data\\excel_data.xlsx", "logindata");
        System.out.println("*****************************taking data from Excel***********************************");
        int columnCount = dataList.get(0).size();
        Object[][] data = new Object[dataList.size()][columnCount];
        int rowIndex = 0;

        for (Map<String, String> row : dataList) {
            int columnIndex = 0;
            for (String key : row.keySet()) {
                data[rowIndex][columnIndex] = row.get(key);
                columnIndex++;
            }
            rowIndex++;
        }
        return data;

    }

}
