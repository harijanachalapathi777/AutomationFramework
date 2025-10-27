package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    public static List<Map<String, String>> readJsonAsList(String fileName)
            throws StreamReadException, DatabindException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        String path = ConfigReader.get("json_data").trim();
        InputStream is = JsonUtil.class.getClassLoader().getResourceAsStream(
                path);

        if (is == null) {
            throw new RuntimeException("Json file not found " + fileName);
        }
        return mapper.readValue(is, new TypeReference<List<Map<String, String>>>() {
        });
    }
}
