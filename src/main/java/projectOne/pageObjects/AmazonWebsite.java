package projectOne.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import projectOne.abstractComponents.AbstractComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmazonWebsite extends AbstractComponent {

	WebDriver driver;

	public AmazonWebsite(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement searchField;

	@FindBy(xpath = "//div[@data-component-type='s-search-result']")
	List<WebElement> productList;

	@FindBy(xpath = "//div[@id='brandsRefinements']//span[@class='a-size-base a-color-base'][normalize-space()='Apple']")
	WebElement appleBtn;

	 By priceElementBy = By.xpath(".//span[@class='a-offscreen']/following-sibling::span");

	private final By rawNameBy = By.xpath(".//span[@class='a-size-medium a-color-base a-text-normal']");

	public  List<Map<String, String>> getAmazonProducts() {
		searchField.sendKeys("iPhone 14");
		searchField.sendKeys(Keys.ENTER);

		// waitForElementToAppear(appleBtnBy);
		// appleBtn.click();

		// List<WebElement> productList =
		// driver.findElements(By.xpath("//div[@data-component-type='s-search-result']"));

		List<Map<String, String>> prices = new ArrayList<>();
		// Map<String, String> productInfo = new HashMap<>();

		for (WebElement product : productList) {
			// String name = product.getText();
			String[] rawName = product.findElement(rawNameBy).getText().split(",");
			String name = rawName[0].trim();
			if (name.contains("iPhone 11")) {
				try {
					// waitForElementToAppear(priceElementEleBy);
					WebElement priceElement = product.findElement(priceElementBy);
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

	}

	public EbayWebsite goToEbay()
	{
        EbayWebsite ebayWebsite = new EbayWebsite(driver);
        ebayWebsite.goToEbay();
        return ebayWebsite;
    }

}
