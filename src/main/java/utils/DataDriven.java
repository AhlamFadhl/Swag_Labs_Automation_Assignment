package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.UserData;
import com.fasterxml.jackson.core.type.TypeReference;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataDriven {
    static String  PATH="testData/testData.json";
   static ObjectMapper mapper = new ObjectMapper();
    //read json file
    static JsonNode rootNode ;
    @DataProvider(name = "getValidJsonData")
    public static Iterator<Object[]> getValidData() throws IOException {
        rootNode = mapper.readTree(
                new File(PATH));
        //conver json to list of UserData
        List<UserData> dataList = mapper.convertValue(
                rootNode.get("validusers"),
                new TypeReference<List<UserData>>() {}
        );

        // Convert List<UserData> to List<Object[]> for TestNG DataProvider
        List<Object[]> dataToReturn = new ArrayList<>();
        for (UserData data : dataList) {
            dataToReturn.add(new Object[] { data });
        }

        return dataToReturn.iterator();
    }

    @DataProvider(name = "getInValidJsonData")
    public static Iterator<Object[]> getInvalidData()  {
        try{
        rootNode = mapper.readTree(
                new File(PATH));
        //conver json to list of UserData
        List<UserData> dataList = mapper.convertValue(
                rootNode.get("invalidusers"),
                new TypeReference<List<UserData>>() {}
        );

        // Convert List<UserData> to List<Object[]> for TestNG DataProvider
        List<Object[]> dataToReturn = new ArrayList<>();
        for (UserData data : dataList) {
            dataToReturn.add(new Object[] { data });
        }

        return dataToReturn.iterator();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read testData.json", e);
        }
    }

    @DataProvider(name = "getCartItems")
    public static Object[][] getCartItems() {
        try {
            JsonNode rootNode = mapper.readTree(new File(PATH));
            List<String> items = mapper.convertValue(
                    rootNode.get("cart_items"),
                    new TypeReference<List<String>>() {
                    });
            return new Object[][]{{items}};
        } catch (IOException e) {
            throw new RuntimeException("Cannot read testData.json", e);
        }
    }

    public static String getString(String key) {
        try {
            JsonNode rootNode = mapper.readTree(new File(PATH));
            JsonNode value = rootNode.get(key);
            if (value == null) {
                throw new RuntimeException("Key not found in testData.json: " + key);
            }
            return value.asText();
        } catch (IOException e) {
            throw new RuntimeException("Cannot read testData.json", e);
        }
    }
}
