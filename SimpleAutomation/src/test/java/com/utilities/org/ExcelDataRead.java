package com.utilities.org;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataRead {
	
	 public static FileInputStream fileinputstream;
	 public static XSSFWorkbook workbook;
	 public static XSSFSheet sheet;
	 public static XSSFRow row;
	 public static XSSFCell cell;

	 public static int getRowCount(String filename, String sheetName) throws IOException {
		 fileinputstream = new FileInputStream(filename);
	  workbook = new XSSFWorkbook(fileinputstream);
	  sheet = workbook.getSheet(sheetName);
	  int rowscount = sheet.getLastRowNum();
	  workbook.close();
	  fileinputstream.close();

	  return rowscount;
	 }

	 public static int getCellData(String filename, String sheetName, int rowNum) throws IOException {
		 fileinputstream = new FileInputStream(filename);
	  workbook = new XSSFWorkbook(fileinputstream);
	  sheet = workbook.getSheet(sheetName);
	  row = sheet.getRow(rowNum);
	  int cellCount = row.getLastCellNum();
	  workbook.close();
	  fileinputstream.close();
	  return cellCount;
	 }

	 public static String getCellData(String filename, String sheetName, int rowNum, int colNum) throws IOException {
		 fileinputstream = new FileInputStream(filename);
	  workbook = new XSSFWorkbook(fileinputstream);
	  sheet = workbook.getSheet(sheetName);
	  row = sheet.getRow(rowNum);
	  cell = row.getCell(colNum);

	  String data;
	  try {
	   DataFormatter dataFormatter = new DataFormatter();
	   String cellData = dataFormatter.formatCellValue(cell);
	   return cellData;
	  } catch (Exception e) {
	   data = "";
	  }

	  workbook.close();
	  fileinputstream.close();
	  return data;
	 }
	}
