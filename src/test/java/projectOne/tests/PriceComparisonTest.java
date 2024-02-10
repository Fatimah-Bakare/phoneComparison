package projectOne.tests;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import projectOne.pageObjects.AmazonWebsite;
import projectOne.pageObjects.EbayWebsite;
import projectOne.testComponents.BaseTest;

public class PriceComparisonTest extends BaseTest {

    @Test
    public void priceComparison()
    {
        //Get Amazon Website
        AmazonWebsite amazonWebsite = landingPageAmazon.loginApplicationAmazon();
        List<Map<String, String>> amazonProducts = amazonWebsite.getAmazonProducts();

        //Get Ebay Website
        EbayWebsite ebayWebsite = amazonWebsite.goToEbay();
        List<Map<String, String>> ebayProducts = ebayWebsite.getEbayProducts();

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

}
