package projectOne.ebayPhones;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ebay {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\fatim\\Downloads\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

        driver.get("https://www.ebay.com/");

        WebElement searchField = driver.findElement(By.xpath("//input[@id='gh-ac']"));
        searchField.sendKeys("iPhone 11");
        searchField.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/span[@role='heading']")));
        List<WebElement> phoneList = driver.findElements(By.xpath("//li[contains(@class, 's-item')]"));

        List<Map<String, String>> prices = new ArrayList<>();

        for (WebElement phone:phoneList){
            String[] rawName = phone.findElement(By.xpath(".//div[contains(@class, 's-item__title')]")).getText().split("-");
            String name = rawName[0].trim();
            if (name.contains("iPhone 11 Pro")){
                try {
                    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[contains(@class, 's-item__price')]")));
                    String price = phone.findElement(By.xpath(".//span[contains(@class, 's-item__price')]")).getText();

                    Map<String, String> productInfo = new HashMap<>();
                    productInfo.put(name, price);
                    prices.add(productInfo);
                } catch (NumberFormatException e) {
                    System.out.println("Failed to parse price for product: " + name);
                    // Handle the exception as needed
                }
            }
        }

        //System.out.println("Matching items: " + " - " + prices);

        // Iterate through the list and print each product's information
        for (Map<String, String> productInfo : prices) {
            for (Map.Entry<String, String> entry : productInfo.entrySet()) {
                System.out.println("Product: " + entry.getKey() + " - Price: " + entry.getValue());
            }
        }

        driver.quit();

    }

}
