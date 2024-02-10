package projectOne.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import projectOne.abstractComponents.AbstractComponent;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EbayWebsite extends AbstractComponent {

    WebDriver driver;

    public EbayWebsite(WebDriver driver) {
        super(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//input[@id='gh-ac']")
    WebElement searchField;

    @FindBy(xpath = "//li[contains(@class, 's-item')]")
    List<WebElement> phoneList;

    By phoneListBy = By.xpath("//li[contains(@class, 's-item')]");
    private final By rawNameBy = By.xpath(".//div[contains(@class, 's-item__title')]");
    By priceBy = By.xpath(".//span[contains(@class, 's-item__price')]");

    public List<Map<String, String>> getEbayProducts()
    {
        searchField.sendKeys("iPhone 11");
        searchField.sendKeys(Keys.ENTER);

        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));

        //waitForElementToAppear(phoneListBy);
        //List<WebElement> phoneList = driver.findElements(By.xpath("//li[contains(@class, 's-item')]"));

        List<Map<String, String>> prices = new ArrayList<>();

        for (WebElement phone:phoneList){
            String[] rawName = phone.findElement(rawNameBy).getText().split("-");
            String name = rawName[0].trim();
            if (name.contains("iPhone 11 Pro")){
                try {
                    //waitForElementToAppear(priceBy);
                    String price = phone.findElement(priceBy).getText();

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

    }

    public EbayWebsite goToEbay()
    {
        driver.get("https://www.ebay.com/");
        return null;
    }
}
