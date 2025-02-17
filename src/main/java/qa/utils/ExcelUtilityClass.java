package qa.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

public class ExcelUtilityClass {
	
	
	  private XSSFWorkbook workbook;
	    private XSSFSheet sheet;

	    public ExcelUtilityClass(String filePath, String sheetName) throws IOException {
	        FileInputStream fileInputStream = new FileInputStream(filePath);
	        workbook = new XSSFWorkbook(fileInputStream);
	        sheet = workbook.getSheet(sheetName);
	    }
	
	
	public List<Map<String, String>> getRowData(String testCaseName) {
	    List<Map<String, String>> rowDataList = new ArrayList<>();
	    XSSFRow headerRow = sheet.getRow(0); // Assuming the first row contains headers

	    for (Row row : sheet) {
	        Cell cell = row.getCell(0); // Assuming the test case name is in the first column
	        if (cell != null && cell.getStringCellValue().equalsIgnoreCase(testCaseName)) {
	            Map<String, String> rowData = new HashMap<>();
	            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
	                String header = headerRow.getCell(i).getStringCellValue();
	                Cell valueCell = row.getCell(i);
	                String value = (valueCell != null) ? valueCell.toString() : "";
	                rowData.put(header, value);
	            }
	            rowDataList.add(rowData); // Add matching row to the list
	        }
	    }
	    return rowDataList;
	}
	 public void close() throws IOException {
	        workbook.close();
	    }
	
	 @DataProvider(name = "testDataProvider")
	    public static Object[][] getTestData(Method method) throws IOException {
	        // Get the test case name from the @Test method name
	        String testCaseName = method.getName(); // Use the method name as the test case name

	        // Fetch data from Excel
	        ExcelUtilityClass excelReader = new ExcelUtilityClass("src\\main\\resources\\Datasheet.xlsx", "Sheet1");
	        List<Map<String, String>> testDataList = excelReader.getRowData(testCaseName);
	        excelReader.close();

	        // Convert the list of maps to a 2D Object array
	        Object[][] data = new Object[testDataList.size()][1];
	        for (int i = 0; i < testDataList.size(); i++) {
	            data[i][0] = testDataList.get(i);
	        }
	        return data;
	    }
        /*Row headerRow = sheet.getRow(0); // Assuming the first row contains headers
        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = headerRow.getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][1]; // Exclude header row

        for (int i = 1; i < rowCount; i++) { // Start from row 1 (skip header)
            Row row = sheet.getRow(i);
            Map<String, String> rowData = new HashMap<>();

            for (int j = 0; j < colCount; j++) {
                String header = headerRow.getCell(j).getStringCellValue();
                Cell cell = row.getCell(j);
                String value = (cell != null) ? cell.toString() : "";
                rowData.put(header, value);
            }

            data[i - 1][0] = rowData; // Add row data to the array
        }

        workbook.close();
        fileInputStream.close();

        return data;
    }
	*/
	
       /* XSSFRow headerRow = sheet.getRow(0); // Assuming the first row contains headers
        List<Map<String, String>> testDataList = new ArrayList<>();

        for (Row row : sheet) {
            XSSFCell cell = (XSSFCell) row.getCell(0); // Assuming the test case name is in the first column
            if (cell != null && cell.getStringCellValue().equalsIgnoreCase(testCaseName)) {
                Map<String, String> rowData = new HashMap<>();
                for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                    String header = headerRow.getCell(i).getStringCellValue();
                    Cell valueCell = row.getCell(i);
                    String value = (valueCell != null) ? valueCell.toString() : "";
                    rowData.put(header, value);
                }
                testDataList.add(rowData);
            }
        }

        workbook.close();
        fileInputStream.close();

        // Convert the list of maps to a 2D Object array
        Object[][] data = new Object[testDataList.size()][1];
        for (int i = 0; i < testDataList.size(); i++) {
            data[i][0] = testDataList.get(i);
        }
        return data;
    }	
	
	
	*/
	
	
	
	
	
	
	
	/*
	 * // private static final String TEST_DATA_FILE =
	 * "src\\main\\resources\\Datasheet.xlsx"; // Update with actual file path
	 * 
	 * public Object[][] getTestData() throws IOException { List<List<Object>>
	 * allData = readExcelData("src\\main\\resources\\Datasheet.xlsx");
	 * 
	 * // Filter data for "LoginTest" test case name List<List<Object>> filteredData
	 * = new ArrayList<>(); for (List<Object> row : allData) { if
	 * (row.get(0).equals("Test1")) { filteredData.add(row); } }
	 * 
	 * // Convert filtered data into a 2D array for TestNG Object[][] data = new
	 * Object[filteredData.size()][filteredData.get(0).size()]; for (int i = 0; i <
	 * filteredData.size(); i++) { data[i] = filteredData.get(i).toArray(); }
	 * 
	 * return data; }
	 * 
	 * private List<List<Object>> readExcelData(String filePath) throws IOException
	 * { List<List<Object>> rowData = new ArrayList<>();
	 * 
	 * // Open the Excel file FileInputStream file = new FileInputStream(filePath);
	 * XSSFWorkbook workbook = new XSSFWorkbook(file); XSSFSheet sheet =
	 * workbook.getSheetAt(0); // Get the first sheet
	 * 
	 * // Loop through rows (starting from row 1 to skip header) for (int rowIndex =
	 * 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) { XSSFRow row =
	 * sheet.getRow(rowIndex); List<Object> rowList = new ArrayList<>(); // Loop
	 * through each cell in the row for (int colIndex = 0; colIndex <
	 * row.getPhysicalNumberOfCells(); colIndex++) {
	 * rowList.add(row.getCell(colIndex).toString()); // Add cell value as a String
	 * } rowData.add(rowList); }
	 * 
	 * workbook.close(); file.close();
	 * 
	 * return rowData; }
	 * 
	 * 
	 * 
	 * public static void gettestdata(String firstName) throws IOException {
	 * 
	 * System.out.println("Running login test with:");
	 * System.out.println("Username: " + firstName); //
	 * System.out.println("Password: " + password); //
	 * System.out.println("Expected Result: " + expectedResult);
	 * 
	 * }
	 * 
	 */
	    
	    
	    
	}
