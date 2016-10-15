package com.SBIXDDF.Util;

import java.util.HashMap;

import net.sourceforge.htmlunit.corejs.javascript.ObjArray;

import org.testng.SkipException;

import com.SBIXDDF.Core.PageBase;
import com.gargoylesoftware.htmlunit.Page;

public class TestUtil {

	// Verify, Is test Executable
	public static boolean isTestExecuatble(Xls_Reader xls_reader,
			String sheetName, String testName) {
		try {
			for (int rowNum = 2; rowNum <= xls_reader.getRowCount(sheetName); rowNum++) {
				if (xls_reader.getCellData(sheetName, "TestCase", rowNum)
						.equalsIgnoreCase(testName)) {
					if (xls_reader.getCellData(sheetName, "RunMode", rowNum)
							.equalsIgnoreCase("yes")) {
						return true;
					} else {
						return false;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			PageBase.APP_LOGS.debug("Error : ", e);
		}
		return false;
	}

	// Function to check Test case Run Mode
	public static void checkTestRunMode(String sheetName, String testName) {
		// Create the object of the XLS Reader class
		Xls_Reader xls = new Xls_Reader(Constants.TESTCASESLIST_FILE_PATH);

		if (!TestUtil.isTestExecuatble(xls, sheetName, testName)) {
			PageBase.APP_LOGS.debug("Error : " + testName + ", Test Not found");
			throw new SkipException("Test Not Found : "+testName);
		}
	}

	// Function to get the Data from Excel file
	public static Object[][] getTestData(String sheetName, String testName){
		//Read xls file
		Xls_Reader XlsReader = new Xls_Reader(Constants.TESTCASEDATA_FILE_PATH);
		
		//Declare varaible to read the row from which Test Start
		int test_start_row = 0;
		
		//Find the row number from which Test Starts
		for(int rowNum=0; rowNum<= XlsReader.getRowCount(sheetName); rowNum++){
			if(XlsReader.getCellData(sheetName, 0, rowNum).equalsIgnoreCase(testName)){
				test_start_row = rowNum;
				
				
				//exit the loop if you find the row of your test case
				break;
			}
		}
			//Declare varaible to store form where columns start for test
			int colstartTest = test_start_row + 1;
			
			//Declare total columns in the Test
			int totalTestDataCol = 0;
			
			//Find number of columns in the Test
			while(!XlsReader.getCellData(sheetName, totalTestDataCol, colstartTest).equals("")){
				totalTestDataCol++;
			}
			
			
			//Declare Test Data Start Row
			int testDataStartRow = test_start_row + 2;
			int totalTestDataRows =0;
			
			//Find number of Test Data Rows for your Test
			while (!XlsReader.getCellData(sheetName, 0, testDataStartRow+totalTestDataRows).equals("")) {
				totalTestDataRows++;	
			}
			
			//Now we have total number of columns for our Test Case in variable totalTestDataCol & Total number of test data rows for our test in varaible totalTestDataRows
			
			//Now declare the Data Array, 
			Object[][] dataArray = new Object[totalTestDataRows][1];
			
			
			//For every Row there will be one HashMap.
			
			HashMap<String, String> dataMap = null;
			int dataIndex = 0;
			
			for(int rNum = testDataStartRow; rNum<(testDataStartRow+totalTestDataRows); rNum++){
				//Start Initialize HashMap as Test Data Starts
				dataMap = new HashMap<String, String>();
				
				//Insert Data in Key-Value pair. Key will be Column Name and Value will be Column Value
				//It will insert the Data into HashMap for each and every Row
				for(int cNum =0 ; cNum<=totalTestDataCol; cNum++){
					dataMap.put(XlsReader.getCellData(sheetName, cNum, colstartTest), XlsReader.getCellData(sheetName, cNum, rNum));
					System.out.println(XlsReader.getCellData(sheetName, cNum, rNum));					
				}
				
				//Now put the DataMap inside the Two Dimensional Array
				dataArray[dataIndex][0]= dataMap;
				dataIndex++;
			}
			
			
			//Now return the DataArray
			return dataArray;
	}
	
}
