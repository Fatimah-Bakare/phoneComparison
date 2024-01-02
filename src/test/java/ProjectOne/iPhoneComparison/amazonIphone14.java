package ProjectOne.iPhoneComparison;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class amazonIphone14 {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\fatim\\Downloads\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));

		driver.get("https://www.amazon.com/");

		//Actions action = new Actions(driver);

		WebElement searchField = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchField.sendKeys("iPhone 14 Pro Max");
		searchField.sendKeys(Keys.ENTER);

//		List<WebElement> iframeElement = driver.findElements(By.tagName("iframe"));
//		System.out.println(iframeElement.size());
//		//WebElement frame = driver.findElement(By.xpath("//div[@cel_widget_id='LEFT-SAFE_FRAME-1']"));
//		//driver.switchTo().frame(1);

//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='brandsRefinements']//span[@class='a-size-base a-color-base'][normalize-space()='Apple']")));
//		driver.findElement(By.xpath("//div[@id='brandsRefinements']//span[@class='a-size-base a-color-base'][normalize-space()='Apple']")).click();

		List<WebElement> productList = driver.findElements(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));

		List<String> prices = new ArrayList<>();

        for (int i = 0; i < productList.size(); i++) {
			WebElement product = productList.get(i);
            String name = product.getText();
//			double price = Double.parseDouble(driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText());
//			System.out.println("Item " + (i + 1) + ": " + name + " - Price: " + price);
			//System.out.println("Item " + (i + 1) + ": " + product.get(i).getText());
			if(name.contains("iPhone 14 Pro Max")) {
				String priceXPath = "//div[@class='a-section a-spacing-small a-spacing-top-small']//div[@data-cy='price-recipe']//span[@class='a-price']//span[@class='a-offscreen']";
				String price = productList.get(i).findElement(By.xpath(priceXPath)).getText();
				prices.add(name + price);

			}
        }

		System.out.println("Matching items: " + prices);

//		List<List<String>> phoneList = List.of(prices);
//
//		System.out.println(phoneList);

		for(int i = 0; i < prices.size(); i++)
		{
			System.out.println("Matching items: " + prices.get(i));
		}

		Thread.sleep(3000);

		driver.quit();

	}

}
