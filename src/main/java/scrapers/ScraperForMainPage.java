package scrapers;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScraperForMainPage {
    private static final String MAIN_URL = "https://allegro.pl";
    private static final String DEALS = "/strefaokazji/";
    private static final String CATEGORIES_CLASS = "opbox-sheet _26e29_11PCu card _9f0v0 msts_n7";
    private final Logger logger = Logger.getLogger(this.getClass());

    public List<String> extractCategoriesLinks() {
        List<String> listOfCategoriesLinks = new ArrayList<>();

        Document document;
        try {
            document = Jsoup.connect(MAIN_URL + DEALS).get();
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        Element element = document.getElementsByClass(CATEGORIES_CLASS).first();
        Elements linkElements = element.getElementsByAttribute("href");
        for (Element elem : linkElements
        ) {
            listOfCategoriesLinks.add(MAIN_URL + elem.attr("href"));
        }

        return listOfCategoriesLinks;
    }
}
