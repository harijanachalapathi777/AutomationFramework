package Utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class JsonDataProvider {

    @DataProvider(name = "JsonData")
    public static Object[][] getLoginDataJson() throws StreamReadException, DatabindException, IOException {
        List<Map<String, String>> dataList = JsonUtil
                .readJsonAsList(ConfigReader.get("json_data").trim());
        Object[][] dataArray = new Object[dataList.size()][2];
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, String> map = dataList.get(i);
            dataArray[i][0] = map.get("username");
            dataArray[i][1] = map.get("password");
        }
        return dataArray;
    }
}
