package scrappers;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapperForCategory {
    private static final String LISTING_CLASS = "opbox-listing";
    private final Logger logger = Logger.getLogger(this.getClass());
    private final String categoryURL;

    public ScrapperForCategory(String categoryURL) {
        this.categoryURL = categoryURL;
    }

    List<String> getListOfLinksOfProducts() {
        List<String> listOfLinksOfProducts = new ArrayList<>();

        Document document;
        try {
            document = Jsoup.connect(categoryURL).get();
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        Element element = document.getElementsByClass(LISTING_CLASS).first();
        Elements linkElements = element.getElementsByAttribute("href");

        // In listing box we have two links on each product
        // so we move through loop with +2 steps
        for (int i = 0; i < linkElements.size(); i += 2) {
            String link = linkElements.get(i).attr("href");
            listOfLinksOfProducts.add(link);
        }

        return listOfLinksOfProducts;
    }
}
