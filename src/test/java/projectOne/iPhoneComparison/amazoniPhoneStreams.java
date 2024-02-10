package projectOne.iPhoneComparison;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class amazoniPhoneStreams {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\fatim\\Downloads\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

        driver.get("https://www.amazon.com/");

        WebElement searchField = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchField.sendKeys("iPhone 13");
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> productElement = driver.findElements(By.xpath("//div[@data-asin]"));

        List<String> price = productElement.stream().filter(phone -> phone.getText().contains("iPhone 14")).map(phone -> getPhonePrice(phone)).collect(Collectors.toList());
        price.forEach(phone -> System.out.println(phone));

    }

    public static String getPhonePrice(WebElement phone)
    {
        String priceValue = phone.findElement(By.xpath("//span[@class= 'a-offscreen']")).getText();

        return priceValue;
    }

}

