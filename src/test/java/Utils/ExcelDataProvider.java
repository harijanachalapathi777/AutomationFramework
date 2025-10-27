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
        Object[][] data = new Object[dataList.size()][2];
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, String> row = dataList.get(i);
            data[i][0] = row.get("username");
            data[i][1] = row.get("password");
        }
        return data;

    }

}
