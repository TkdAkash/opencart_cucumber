package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlUtility {

	public String xlFilePath;
	public FileInputStream fi;
	public FileOutputStream fo;
	public Workbook wb;
	public Sheet sh;
	public Row row;
	public Cell cell;
	public CellStyle style;
	
	
	public xlUtility(String xlFilePath) {
		this.xlFilePath = xlFilePath;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fi = new FileInputStream(xlFilePath);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		int rowCount = sh.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public int getcellCount(String sheetName, int rownum) throws IOException {
		fi = new FileInputStream(xlFilePath);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rownum, int celnum) throws IOException {
		fi = new FileInputStream(xlFilePath);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(sheetName);
		row = sh.getRow(rownum);
		cell = row.getCell(celnum);
		String data;
		try {
			DataFormatter df = new DataFormatter();
			data = df.formatCellValue(cell);
		}catch (Exception e) {
			// TODO: handle exception
			data = "";
		}
		
		return data;
	}
	
	public void setCellData(String sheetName, int rownum, int celnum, String data) {
		File xfile = new File(xlFilePath);
		if(!xfile.exists()){
			wb = new XSSFWorkbook();
		}
		
	}
	
}
