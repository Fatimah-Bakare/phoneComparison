package projectOne.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import projectOne.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public AmazonWebsite loginApplicationAmazon()
    {
        AmazonWebsite amazonWebsite = new AmazonWebsite(driver);
        return amazonWebsite;
    }

//    public EbayWebsite loginApplicationEbay()
//    {
//        EbayWebsite ebay = new EbayWebsite(driver);
//        return ebay;
//    }

    public void goToAmazon()
    {
        driver.get("https://www.amazon.com/");
    }

}
