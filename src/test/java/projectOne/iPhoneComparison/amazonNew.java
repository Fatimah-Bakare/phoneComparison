package projectOne.iPhoneComparison;

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

public class amazonNew {

    public static void main(String[] args) throws InterruptedException {

        // Provide the ChromeDriver path dynamically or through a configuration file
        String driverPath = "C:\\Users\\fatim\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));

        driver.get("https://www.amazon.com/");

        WebElement searchField = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchField.sendKeys("iPhone 14");
        searchField.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='brandsRefinements']//span[@class='a-size-base a-color-base'][normalize-space()='Apple']")));
//        driver.findElement(By.xpath("//div[@id='brandsRefinements']//span[@class='a-size-base a-color-base'][normalize-space()='Apple']")).click();

        List<WebElement> productList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));

        List<Map<String, String>> prices = new ArrayList<>();
        //Map<String, String> productInfo = new HashMap<>();

        for (WebElement product : productList) {
            //String name = product.getText();
            String[] rawName = product.findElement(By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']")).getText().split(",");
            String name = rawName[0].trim();
            if (name.contains("iPhone 11")) {
                try {
                    //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='a-offscreen']/following-sibling::span")));
                    WebElement priceElement = product.findElement(By.xpath(".//span[@class='a-offscreen']/following-sibling::span"));
                    String price = priceElement.getText();

                    Map<String, String> productInfo = new HashMap<>();
                    productInfo.put(name, price);
                    prices.add(productInfo);
                } catch (NumberFormatException e) {
                    System.out.println("Failed to parse price for product: " + name);
                    // Handle the exception as needed
                }
            }
        }

        //System.out.println("Matching items: " + prices);

        // Iterate through the list and print each product's information
        for (Map<String, String> productInfo : prices) {
            for (Map.Entry<String, String> entry : productInfo.entrySet()) {
                System.out.println("Product: " + entry.getKey() + " - Price: " + entry.getValue());
            }
        }

        driver.quit();
    }

}
