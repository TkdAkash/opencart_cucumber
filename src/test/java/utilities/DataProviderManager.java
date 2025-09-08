package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviderManager {

	@DataProvider(name = "LoginData")
	public String[][] getLoginData() throws IOException{
		
		String path = System.getProperty("user.dir")+"//testData//testDataFile.xlsx";
		xlUtility xl = new xlUtility(path);
		
		int totalNoOfRows = xl.getRowCount("Sheet1");
		int totalNoOfCell = xl.getcellCount("Sheet1", 1);
		System.out.println(totalNoOfRows);
		
		String loginData[][] = new String[totalNoOfRows][totalNoOfCell];
		
		for(int i=1; i<=totalNoOfRows; i++) {
			
			for(int j=0; j<totalNoOfCell; j++) {
				loginData[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		
		return loginData;
		
		
	}
	
}
