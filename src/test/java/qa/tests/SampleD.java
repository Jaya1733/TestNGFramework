package qa.tests;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class SampleD {
 
	public static String TESTDATA_SHEET_PATH = "src\\main\\resources\\Datasheet.xlsx";
	static XSSFWorkbook book;
	static XSSFSheet sheet;
	 
	 
	// @DataProvider
	public static Object[][] ReadVariant() throws IOException
	{
	FileInputStream fileInputStream= new FileInputStream(TESTDATA_SHEET_PATH); //Excel sheet file location get mentioned here
		XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream); //get my workbook 
		XSSFSheet worksheet=workbook.getSheet("Sheet1");// get my sheet from workbook
   	    XSSFRow Row=worksheet.getRow(0);   	 //get my Row which start from 0   
   	
    	int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
    	int ColNum= Row.getLastCellNum(); // get last ColNum 
    	Object Data[][]= new Object[RowNum-1][ColNum];// pass my  count data in array
    	
    		for(int i=0; i<RowNum-1; i++) //Loop work for Rows
    		{  
    			XSSFRow row= worksheet.getRow(i+1);
    			
    			for (int j=0; j<ColNum; j++) //Loop work for colNum
    			{
    				if(row==null)
    					Data[i][j]= "";
    				else 
    				{
    					XSSFCell cell= row.getCell(j);
    					if(cell==null)
    						Data[i][j]= ""; //if it get Null value it pass no data 
    					else
    					{
    						String value1=cell.getStringCellValue();
    						Data[i][j]=value1; //This formatter get my all values as string i.e integer, float all type data value
    					}
    				}
    			}
    		}
 
    	return Data;
    }
	
	

}
