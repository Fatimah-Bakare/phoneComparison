package projectOne.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import projectOne.abstractComponents.AbstractComponent;

import java.util.List;
import java.util.Map;

public class Comparison extends AbstractComponent {

    WebDriver driver;

    public Comparison(WebDriver driver) {
        super(driver);

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

//    public void compareAmazonAndEbay() {
//        List<Map<String, String>> amazonProducts = AmazonWebsite.getAmazonProducts();
//        List<Map<String, String>> ebayProducts = EbayWebsite.getEbayProducts();
//
//        // Compare brand names and prices
//        for (Map<String, String> amazonProduct : amazonProducts) {
//            String amazonProductName = amazonProduct.keySet().iterator().next();
//            String amazonProductPrice = amazonProduct.get(amazonProductName);
//
//            for (Map<String, String> ebayProduct : ebayProducts) {
//                String ebayProductName = ebayProduct.keySet().iterator().next();
//                String ebayProductPrice = ebayProduct.get(ebayProductName);
//
//                // Compare brand names and prices
//                if (amazonProductName.equalsIgnoreCase(ebayProductName)) {
//                    System.out.println("Matching product: " + amazonProductName);
//                    System.out.println("Amazon Price: " + amazonProductPrice);
//                    System.out.println("eBay Price: " + ebayProductPrice);
//                    System.out.println("--------------------------");
//                }
//            }
//        }
//    }

}
