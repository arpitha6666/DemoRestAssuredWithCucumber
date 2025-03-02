package restassured.restassuredTests;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import util.ExcelUtil;

import java.io.IOException;
import java.util.List;

public class ExcelIntegration {

    @Test
    public void excelDrivenTest() throws IOException, InvalidFormatException {
        ExcelUtil util = new ExcelUtil();
        List<String> arr = util.getData("Testcases","testData");
        arr.stream().forEach(System.out::println);
    }

}
