package task3;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonTests = parseJSON(args[0]);
        JSONObject jsonValues = parseJSON(args[1]);
        List<JSONObject> listTests = (List<JSONObject>) jsonTests.get("tests");
        createReportJson(getValues(listTests, jsonValues));
    }


    /**
     * Метод, предназначенный для парсингп JSON в JSONObject
     * @param path - путь до файла
     * @return - возвращает jsonObject в случае успеха и null в случае неудачи
     */
    public static JSONObject parseJSON(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            Object obj = new JSONParser().parse(fileReader);
            JSONObject json = (JSONObject) obj;
            fileReader.close();
            return json;
        } catch (IOException | ParseException e) {
            System.out.println("Ошибка парсинга JSON " + path);
            return null;
        }
    }

    /**
     * Метод, предназначенный для парсинга двух JSON для добавления ключ-значения в соответтсвующий JSON
     * @param listTests - лист с JSON от результатов теста
     * @param jsonValues - JSONObject со значениями
     * @return - возвращает итоговый JSONObject
     */
    public static List<JSONObject> getValues(List<JSONObject> listTests, JSONObject jsonValues) {
        for (JSONObject test : listTests) {
            if (test.containsKey("value")) {
                String test_id = test.get("id").toString();
                List<JSONObject> values = (List<JSONObject>) jsonValues.get("values");
                for (JSONObject test_value : values) {
                    if (test_value.get("id").toString().equals(test_id)) {
                        test.put("value", test_value.get("value"));
                    }
                }
            }
            if (test.containsKey("values")) {
                List<JSONObject> nested_test_list = (List<JSONObject>) test.get("values");
                getValues(nested_test_list, jsonValues);
            }
        }
        return listTests;
    }

    /**
     * Метод, записывающий итоговый JSON
     * @param reportList - лист с JSONObject
     */
    public static void createReportJson(List<JSONObject> reportList) {
        JSONObject reportDict = new JSONObject();
        reportDict.put("reports", reportList);
        String reportPath = Paths.get("").toAbsolutePath().resolve("report.json").toString();
        try (FileWriter fileWriter = new FileWriter(reportPath)) {
            fileWriter.write(reportDict.toJSONString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи файла report.json");
        }
    }
}