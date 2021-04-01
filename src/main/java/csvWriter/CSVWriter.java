package csvWriter;

import model.ProductLong;
import model.ProductShort;
import org.apache.log4j.Logger;
import org.csveed.bean.BeanWriterImpl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private static final Logger logger = Logger.getLogger(CSVWriter.class);

    public static void writeCSV(List<ProductLong> products) throws IOException {
        try (FileWriter fw = new FileWriter("productsLong.csv")) {
            BeanWriterImpl<ProductLong> bwi = new BeanWriterImpl<>(fw, ProductLong.class);

            bwi.writeBeans(products);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }

    public static void writeCSVShort(List<ProductShort> products) throws IOException {
        try (FileWriter fw = new FileWriter("products.csv")) {
            BeanWriterImpl<ProductShort> bwi = new BeanWriterImpl<>(fw, ProductShort.class);

            bwi.writeBeans(products);
        } catch (Exception ex) {
            logger.error(ex);
            throw ex;
        }
    }
}
