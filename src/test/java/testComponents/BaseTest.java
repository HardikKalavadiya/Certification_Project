package testComponents;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.google.common.io.Files;

import pageObjects.LoginPage;

public class BaseTest {
	
	public WebDriver driver;
	public String testURL;
	public LoginPage loginPg;

	public WebDriver browserInitialization() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		testURL = prop.getProperty("url");
		
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();
			
		} else if (browserName.equals("firefox")) {
			
			driver = new FirefoxDriver();
			
		} else if(browserName.equals("edge")){
			driver = new EdgeDriver();
		}
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage openLoginPage() throws IOException {
		driver = browserInitialization();
		loginPg = new LoginPage(driver);
		loginPg.goToLogin(testURL);
		return loginPg;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() {
		driver.close();
	}
	
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws StreamReadException, DatabindException, IOException {
	//Files.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\data\\testData.json"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		File dataFile = new File(filePath);

		List<HashMap<String, String>> userData = mapper.readValue(dataFile, new TypeReference<List<HashMap<String, String>>>(){
			
		});
		return userData;
	
	}
	
	public Object[][] getExcelData() throws IOException {
		DataFormatter formatter = new DataFormatter();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\data\\TestBook.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		int numberOfRows = sheet.getPhysicalNumberOfRows();
		System.out.println("Total number of rows in current sheet :"+ numberOfRows);
		XSSFRow row = sheet.getRow(0);
		int colCount = row.getPhysicalNumberOfCells();
		System.out.println("Total number of columns in row :"+colCount);
		Object excelData[][] = new Object[numberOfRows-1][colCount];
		
		for(int i =0; i<numberOfRows-1;i++) {
			row=sheet.getRow(i+1);
			for(int j=0; j<colCount; j++) {
				excelData[i][j] = formatter.formatCellValue(row.getCell(j));
				//excelData[i][j] =row.getCell(j);
				
			}
		}
				return excelData;
		
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		//TakesScreenshot ts = (TakesScreenshot)driver;
		//File source = ts.getScreenshotAs(OutputType.FILE);
		//File file = new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png");
		//FileOutputStream fis = new FileOutputStream (new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png"));
		//Files.copy(source, file);
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		Files.copy(src, new File(System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png"));
		
		return System.getProperty("user.dir")+"\\reports\\"+testCaseName+".png";
		
		
	}
	
}
