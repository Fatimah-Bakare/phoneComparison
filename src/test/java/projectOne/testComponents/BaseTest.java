package projectOne.testComponents;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import projectOne.pageObjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPageAmazon;
    //public LandingPage landingPageEbay;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//projectOne//resources//GlobalData.properties");
		prop.load(fis);

		String browserName = prop.getProperty("browser");

        if (browserName.contains("chrome"))
        {
            String driverPath = "C:\\Users\\fatim\\Downloads\\chromedriver_win32\\chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver();
        }
        else if (browserName.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

        return driver;
	}

    public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplicationAmazon() throws IOException {
        driver = initializeDriver();
        landingPageAmazon = new LandingPage(driver);
        landingPageAmazon.goToAmazon();
        return landingPageAmazon;
    }

//    @BeforeMethod(alwaysRun = true)
//    public LandingPage launchApplicationEbay() throws IOException {
//        driver = initializeDriver();
//        landingPageEbay = new LandingPage(driver);
//        landingPageEbay.goToEbay();
//        return landingPageEbay;
//    }

    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.close();
    }

}
