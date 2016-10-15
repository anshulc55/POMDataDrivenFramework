package com.SBIXDDF.Util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;



import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Xls_Reader {

	// Declare File Name, You want to read. File path, Declare Workwook,
	// worksheet, Excel Row and Excel Cell
	public static String fileName;
	public String path;
	public static XSSFWorkbook workBook = null;
	public static XSSFSheet workSheet = null;
	public static XSSFRow row = null;
	public static XSSFCell cell = null;

	// Declare Input, Output stream to read and write file
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;

	// Constructor to read the create object of Xls Workbook and Worksheet
	public Xls_Reader(String filepath) {
		this.path = filepath;

		try {
			fis = new FileInputStream(path);
			workBook = new XSSFWorkbook(fis);
			workSheet = workBook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			
		}
	}

	// To get Number of Rows present in the Sheet
	public int getRowCount(String sheetName) {

		// To verify Sheet is present or not
		int index = workBook.getSheetIndex(sheetName);
		
		if (index == -1) {
			return 0;
		} else {
			workSheet = workBook.getSheetAt(index);
			// to get the number of rows and retun. Adding 1 because excel row
			// start from 0
			int rowCount = workSheet.getLastRowNum() + 1;
			return rowCount;
		}
	}

	// To get Number of Columns from sheet
	public int getCellCount(String sheetName) {

		// To verify sheet is present or not
		int index = workBook.getSheetIndex(sheetName);
		
		if (index == -1) {
			return 0;

		} else {
			workSheet = workBook.getSheetAt(index);
			// Cells can be count on row only
			int cellCount = workSheet.getRow(0).getLastCellNum();
			return cellCount;
		}
	}

	// To get Cell Data from Excel Sheet
	public String getCellData(String sheetName, String cellName, int rowNum) {

		// Declare Column number
		int cell_num = -1;

		// Verify if given Row Number
		try {
			if (rowNum <= 0)
				return "";

			// Verify, Is sheet present
			int index = workBook.getSheetIndex(sheetName);
			if (index == -1) {
				return "";
			} 
			// Initialize the work sheet on the basis of given sheet name
			workSheet = workBook.getSheetAt(index);

			// Save row
			row = workSheet.getRow(0);

			// Find the column Number of given cellName
			for (int i = 0; i < row.getLastCellNum(); i++) {

				if (row.getCell(i).getStringCellValue().trim()
						.equalsIgnoreCase(cellName.trim())) {
					System.out.println(row.getCell(i).getStringCellValue()
							.trim().equalsIgnoreCase(cellName.trim()));
					cell_num = i;
				}
			}
			// If given cellName is not present in WorkSheet
			if (cell_num == -1) {
				
				return "";
			}

			// Get row on the basic of given rowNum
			row = workSheet.getRow(rowNum - 1);
			if (row == null) {
				
				return "";
			}

			// Get Cell type of the Basic of given cellName
			cell = row.getCell(cell_num);

			// Verify Cell Type
			if (cell == null) {
				
			}

			System.out.println(cell.getCellType());
			if (cell.getCellType() == cell.CELL_TYPE_STRING) {

				// Return cell String Data
				return cell.getStringCellValue();
			} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == cell.CELL_TYPE_FORMULA) {

				// If cell is numric then concert it into String and return
				String cellText = String.valueOf(cell.getNumericCellValue());

				// If cell has date format
				if (HSSFDateUtil.isCellDateFormatted(cell)) {

					double date = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(date));
					cellText = (String.valueOf(cal.get(Calendar.YEAR)))
							.substring(4);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/"
							+ cal.get(Calendar.MONTH) + "/" + cellText;
					System.out.println(cellText);
				}
				return cellText;
			} else if (cell.getCellType() == cell.CELL_TYPE_BLANK) {
				return "";
			} else {
				return String.valueOf(cell.getBooleanCellValue());
			}

		} catch (Exception e) {
			e.printStackTrace();
	
			return "row " + rowNum + " or column " + cellName
					+ " does not exist in xls file";
		}
	}

	// Get Cell Data from Excel file
	public String getCellData(String sheetName, int cellNum, int rowNum) {

		try {
			// return Null, If number of rows are zero
			if (rowNum <= 0)
				return "";

			// Verify Sheet Index & return null if no sheet is present
			int index = workBook.getSheetIndex(sheetName);
			if (index == -1)
				return "";

			// Save the sheet name in sheet varaible
			workSheet = workBook.getSheetAt(index);
			row = workSheet.getRow(rowNum - 1);

			// Return Null if No row is present
			if (row == null)
				return "";

			// Save value in cell
			cell = row.getCell(cellNum);

			// Retrun Null if Cell is null
			if (cell == null)
				return "";

			// read cell data
			if (cell.getCellType() == cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();

			else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC
					|| cell.getCellType() == cell.CELL_TYPE_FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				return cellText;
			} else if (cell.getCellType() == cell.CELL_TYPE_BLANK) {
				return "";
			} else
				return String.valueOf(cell.getBooleanCellValue());

		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + cellNum
					+ " does not exist  in xls";
		}

	}

	// Find, Is sheet exist
	public boolean isSheetExist(String sheetName) {

		int indx = workBook.getSheetIndex(sheetName);
		if (indx == -1) {
			indx = workBook.getSheetIndex(sheetName.toUpperCase());
			if (indx == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// To get Cell Row Number
	public int getCellRowNum(String sheetName, String cellName, String cellValue) {

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, cellName, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
		}
		return -1;
	}
}
