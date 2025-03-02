package util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtil {

    public List<String> getData(String testcase, String sheetName) throws IOException, InvalidFormatException {
        List<String> valueArr = new ArrayList<>();
        File file = new File("src/test/resources/DemoData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        int numOfSheets = workbook.getNumberOfSheets();
        System.out.println("numOfSheets " + numOfSheets);
        //XSSFSheet sheet = workbook.getSheetAt(0);
        //System.out.println("sheet.getSheetName() " + sheet.getSheetName());
        XSSFSheet mySheet = workbook.getSheet(sheetName);
        int numOfRows = mySheet.getPhysicalNumberOfRows();
        int numOfCols = mySheet.getLastRowNum();
        System.out.println("numOfCols " + numOfCols);
        System.out.println("numOfRows " + numOfRows);
        //Identify testcases column by  scanning entire 1st row
        //once column where Testcases is present is identified then scan the comeplete column to find the item - Purchase
        int k = 0;
        int column = 0;
        Iterator<Row> rows = mySheet.iterator();
        Row firstrow = rows.next();//1st row
        Iterator<Cell> cells = firstrow.cellIterator();
        while (cells.hasNext()) {
            if (cells.next().getStringCellValue().equalsIgnoreCase("Testcases")) {
                column = k;
            }
            k++;
        }
        while (rows.hasNext()) {
            Row r = rows.next();
            Cell value = r.getCell(column);
            if (value.getStringCellValue().equalsIgnoreCase(testcase)) {
                //grab all the cell contents of that row
                Iterator<Cell> cv = r.cellIterator();
                while (cv.hasNext()) {
                    Cell c =cv.next();
                    if(c.getCellType().equals(CellType.STRING)) {
                        valueArr.add(c.getStringCellValue());
                    }else {
                        valueArr.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                    }

                    }
                }
            }
        return valueArr;
        }

    }
