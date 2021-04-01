package scrappers;

import csvWriter.CSVWriter;
import model.ProductLong;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Not used in current project
// but made this class to make deeper scrapping
// and also need to improve "Thread.Sleep" choice
public class ScrapperToRuleThemAll {
    private static final Logger logger = Logger.getLogger(ScrapperToRuleThemAll.class);

    // How many categories to scrap?
    private static final int NUMBER_OF_CATEGORIES = 3;
    // How many products per category to scrap?
    private static final int MAX_NUMBER_OF_PRODUCTS_PER_CATEGORY = 100;

    public static List<ProductLong> scrapCategory(String linkToCategory, int maxNumberOfProducts) {
        List<ProductLong> productsFromCategory = new ArrayList<>();
        for (int i = 1; i < maxNumberOfProducts; i++) {
            try {
                List<String> linksOfProducts = new ScrapperForCategory(linkToCategory + "&p=" + i).getListOfLinksOfProducts();
                scrapProduct(linksOfProducts, productsFromCategory, maxNumberOfProducts);
            } catch (Exception exception) {
                logger.error(exception);
                return productsFromCategory;
            }
        }
        return productsFromCategory;
    }

    public static void scrapProduct(List<String> productsLinks, List<ProductLong> listOfProducts, int maxNumberOfProducts) {
        int productNumber = 0;

        while (listOfProducts.size() < maxNumberOfProducts) {
            for (String link : productsLinks
            ) {
                System.out.println(++productNumber);
                ProductLong tempProduct = new ScrapperForProduct(link).extractProduct();
                if (tempProduct != null) {
                    System.out.println(tempProduct.getName());
                    listOfProducts.add(tempProduct);

                }
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    logger.error(e);
                    e.printStackTrace();
                }
            }
        }
    }

    public void executeOrder66() {

        List<ProductLong> productsFromFirstCategory;
        List<ProductLong> productsFromSecondCategory;
        List<ProductLong> productsFromThirdCategory;
        List<ProductLong> allScrappedProducts = new ArrayList<>();

        try {

            List<String> categoryLinks = new ScrapperForMainPage().extractCategoriesLinks();

            productsFromFirstCategory = scrapCategory(categoryLinks.get(1), MAX_NUMBER_OF_PRODUCTS_PER_CATEGORY);
            productsFromSecondCategory = scrapCategory(categoryLinks.get(2), MAX_NUMBER_OF_PRODUCTS_PER_CATEGORY);
            productsFromThirdCategory = scrapCategory(categoryLinks.get(3), MAX_NUMBER_OF_PRODUCTS_PER_CATEGORY);

            allScrappedProducts = productsFromFirstCategory;
            allScrappedProducts.addAll(productsFromSecondCategory);
            allScrappedProducts.addAll(productsFromThirdCategory);

            System.out.println(productsFromFirstCategory.size());
            System.out.println(productsFromSecondCategory.size());
            System.out.println(productsFromThirdCategory.size());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        if (allScrappedProducts != null && !allScrappedProducts.isEmpty()) {
            try {
                CSVWriter.writeCSV(allScrappedProducts);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
