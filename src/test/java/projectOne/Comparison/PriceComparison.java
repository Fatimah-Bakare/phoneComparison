package projectOne.Comparison;

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

public class PriceComparison {

    public static void main(String[] args) throws InterruptedException {
        comparePrices();
    }


    private static void comparePrices() {
        List<Map<String, String>> amazonProducts = getAmazonProducts();
        List<Map<String, String>> ebayProducts = getEbayProducts();

        // Compare brand names and prices
        for (Map<String, String> amazonProduct : amazonProducts) {
            String amazonProductName = amazonProduct.keySet().iterator().next();
            String amazonProductPrice = amazonProduct.get(amazonProductName);

            for (Map<String, String> ebayProduct : ebayProducts) {
                String ebayProductName = ebayProduct.keySet().iterator().next();
                String ebayProductPrice = ebayProduct.get(ebayProductName);

                // Compare brand names and prices
                if (amazonProductName.equalsIgnoreCase(ebayProductName)) {
                    System.out.println("Matching product: " + amazonProductName);
                    System.out.println("Amazon Price: " + amazonProductPrice);
                    System.out.println("eBay Price: " + ebayProductPrice);
                    System.out.println("--------------------------");
                }
            }
        }
    }


    private static List<Map<String, String>> getAmazonProducts()
    {
        String driverPath = "C:\\Users\\fatim\\Downloads\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();

        try {
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

            return prices;

        } finally
        {
            driver.quit();
        }
    }


    private static List<Map<String, String>> getEbayProducts()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\fatim\\Downloads\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        try
        {
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(120));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));

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

            return prices;

        } finally
        {
            driver.quit();
        }
    }

}
