package scrapers.deepScrap;

import model.ProductLong;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

// This Scrapper is for DEEP scrap
// Currently not used
public class ScraperForProduct {
    private static final String PRODUCT_NAME_CLASS = "_1s2v1 _1djie _4lbi0";
    private static final String PRODUCT_SELLER_AND_RATING_CLASS = "_w7z6o _15mod _9a071_3tKtu";
    private static final String PRODUCT_DISCOUNT_CLASS = "_bsvj8 _1t9p2 _8ozz4 _1dd5x _1jusk _9a071_30JM1";
    private static final String PRODUCT_OLD_PRICE_CLASS = "_16hg1";
    private static final String PRODUCT_PRICE_CLASS = "_1svub _lf05o _9a071_3SxcJ";
    private static final String PRODUCT_PARAMETERS_CLASS = "_1h7wt _1bo4a _1fwkl _f8818_2MnBl";
    private static final String PRODUCT_OPIS_CLASS = "_1h7wt _1l8iq _2d49e_1NgnH";
    private static final String PRODUCT_PRICE_BLOCK_CLASS = "_9a071_1Ov3c";
    private final Logger logger = Logger.getLogger(this.getClass());
    private final String productURL;

    public ScraperForProduct(String productURL) {
        this.productURL = productURL;
    }

    ProductLong extractProduct() {
        ProductLong product = new ProductLong();

        Document doc;
        try {
            doc = Jsoup.connect(productURL).timeout(30000).get();
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        product.setLink(productURL);
        Element title = doc.getElementsByClass(PRODUCT_NAME_CLASS).first();
        if (title != null) {
            product.setName(title.text());
        }
        Element titleSellerAndRating = doc.getElementsByClass(PRODUCT_SELLER_AND_RATING_CLASS).first();
        if (titleSellerAndRating != null) {
            product.setSellerNameAndRating(titleSellerAndRating.text());
        }
        Element priceBlock = doc.getElementsByClass(PRODUCT_PRICE_BLOCK_CLASS).first();
        if (priceBlock != null) {

            product.setPrice(priceBlock.getElementsByClass(PRODUCT_PRICE_CLASS).first().text());

            Elements discountElems = priceBlock.getElementsByClass(PRODUCT_DISCOUNT_CLASS);
            if (discountElems.isEmpty()) return null;
            product.setDiscount(discountElems.first().text());
            Elements oldPriceElems = priceBlock.getElementsByClass(PRODUCT_OLD_PRICE_CLASS);
            if (oldPriceElems.isEmpty()) return null;
            product.setOldPrice(oldPriceElems.first().text());
        }
        Element parameters = doc.getElementsByClass(PRODUCT_PARAMETERS_CLASS).first();
        if (parameters != null) {
            product.setParameters(parameters.text());
        }
        Element opis = doc.getElementsByClass(PRODUCT_OPIS_CLASS).first();
        if (opis != null) {
            String opisT = opis.text();
            // At this moment, cutting string to 150 characters, just to make it shorter.
            // in case we need full length - remove substring
            if (opisT.length() > 150) {
                product.setOpis(opisT.substring(0, 150));
            } else {
                product.setOpis(opisT);
            }
        }
        return product;
    }
}
