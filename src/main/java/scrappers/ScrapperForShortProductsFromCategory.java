package scrappers;

import model.ProductShort;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class ScrapperForShortProductsFromCategory {
    private static final String LISTING_CLASS = "opbox-listing";
    private static final String PRODUCT_INFO_CLASS = "mx7m_1 mnyp_co mlkp_ag";
    private static final String PRODUCT_NAME_AND_HREF_CLASS = "mgn2_14 m9qz_yp mqu1_16 mp4t_0 m3h2_0 mryx_0 munh_0";
    private static final String PRODUCT_DISCOUNT_CLASS = "_9c44d_1uHr2";
    private static final String PRODUCT_OLD_PRICE_CLASS = "mpof_uk mqu1_ae _9c44d_18kEF m9qz_yp _9c44d_2BSa0  _9c44d_KrRuv";
    private static final String PRODUCT_PRICE_CLASS = "_1svub _lf05o";
    private static final String PRODUCT_DELIVERY_WITH_COURIER_CLASS = "mpof_92 mp7g_oh m389_0a _9c44d_1BTID";
    private static final String PRODUCT_DELIVERY_TERM_CLASS = "mpof_uk mqu1_ae _9c44d_18kEF _9c44d_3gH36 m9qz_yq ";
    private final Logger logger = Logger.getLogger(this.getClass());
    private final String categoryURL;


    public ScrapperForShortProductsFromCategory(String categoryURL) {
        this.categoryURL = categoryURL;
    }

    void addProductsToListOfShortProducts(List<ProductShort> listOfShortProducts, int maxNumberOfProducts) {
        Document document;
        try {
            document = Jsoup.connect(categoryURL).get();
        } catch (IOException e) {
            logger.error(e);
            throw new RuntimeException(e);
        }
        Element element = document.getElementsByClass(LISTING_CLASS).first();

        Elements products = element.getElementsByClass(PRODUCT_INFO_CLASS);

        // In listing box we have two links on each product
        // so we move through loop with +2 steps
        for (int i = 0; i < products.size() && listOfShortProducts.size() < maxNumberOfProducts; i++) {

            Element prodInfo = products.get(i).getElementsByClass(PRODUCT_NAME_AND_HREF_CLASS).first();
            ProductShort productShort = new ProductShort();

            productShort.setName(prodInfo.text());

            productShort.setLink(prodInfo.getElementsByAttribute("href").first().attr("href"));
            //   productShort.setLink(prodInfo.attr("href"));
            Element discount = products.get(i).getElementsByClass(PRODUCT_DISCOUNT_CLASS).first();
            if (discount == null) {
                continue;
            } else {
                productShort.setDiscount(discount.text());
            }
            Element oldPrice = products.get(i).getElementsByClass(PRODUCT_OLD_PRICE_CLASS).first();
            if (oldPrice == null) {
                continue;
            } else {
                productShort.setOldPrice(oldPrice.text());
            }
            productShort.setPrice(products.get(i).getElementsByClass(PRODUCT_PRICE_CLASS).first().text());
            Element deliveryWithCourier = products.get(i).getElementsByClass(PRODUCT_DELIVERY_WITH_COURIER_CLASS).first();
            if (deliveryWithCourier != null) {
                productShort.setDeliveryWithCourier(deliveryWithCourier.text());
            }
            Element delivery = products.get(i).getElementsByClass(PRODUCT_DELIVERY_TERM_CLASS).first();
            if (delivery != null) {
                productShort.setDeliveryTerm(delivery.text());
            }
            listOfShortProducts.add(productShort);
        }
    }
}
