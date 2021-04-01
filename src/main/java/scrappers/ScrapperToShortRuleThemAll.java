package scrappers;

import csvWriter.CSVWriter;
import model.ProductShort;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapperToShortRuleThemAll {
    private static final Logger logger = Logger.getLogger(ScrapperToRuleThemAll.class);

    // How many categories to scrap?
    private static final int NUMBER_OF_CATEGORIES = 3;
    // How many products per category to scrap?
    private static final int MAX_NUMBER_OF_PRODUCTS_PER_CATEGORY = 100;

    public void executeOrder66() {

        List<ProductShort> allScrappedProducts = new ArrayList<>();

        try {

            List<String> categoryLinks = new ScrapperForMainPage().extractCategoriesLinks();

            // Skip first 2 categories ( just to random result )
            categoryLinks.remove(0);
            categoryLinks.remove(0);

            for (int i = 1; i <= NUMBER_OF_CATEGORIES; i++) {
                for (int j = 1; allScrappedProducts.size() < MAX_NUMBER_OF_PRODUCTS_PER_CATEGORY * i; j++) {
                    new ScrapperForShortProductsFromCategory(categoryLinks.get(i) + "&p=" + j)
                            .addProductsToListOfShortProducts(allScrappedProducts, MAX_NUMBER_OF_PRODUCTS_PER_CATEGORY * i);

                }
            }
        } catch (Exception ex) {
            logger.error(ex);
        }
        if (!allScrappedProducts.isEmpty()) {
            try {
                CSVWriter.writeCSVShort(allScrappedProducts);
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }
}
