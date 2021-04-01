package model;

// This model is for DEEP scrap
// Currently not used
public class ProductLong {
    private String name;
    private String link;
    private String sellerNameAndRating;
    private String price;
    private String discount;
    private String oldPrice;
    private String parameters;
    private String opis;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSellerNameAndRating() {
        return sellerNameAndRating;
    }

    public void setSellerNameAndRating(String sellerNameAndRating) {
        this.sellerNameAndRating = sellerNameAndRating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "model.Product{" +
                "name='" + name + '\'' +
                ",\nlink='" + link + '\'' +
                ",\nsellerNameAndRating='" + sellerNameAndRating + '\'' +
                ",\nprice='" + price + '\'' +
                ",\ndiscount='" + discount + '\'' +
                ",\noldPrice='" + oldPrice + '\'' +
                ",\nparameters='" + parameters + '\'' +
                ",\nopis='" + opis + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
