package model;

// This model is for FAST / REGULAR scrap
public class ProductShort {
    private String name;
    private String link;
    private String deliveryWithCourier;
    private String deliveryTerm;
    private String price;
    private String discount;
    private String oldPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDeliveryWithCourier() {
        return deliveryWithCourier;
    }

    public void setDeliveryWithCourier(String deliveryWithCourier) {
        this.deliveryWithCourier = deliveryWithCourier;
    }

    public String getDeliveryTerm() {
        return deliveryTerm;
    }

    public void setDeliveryTerm(String deliveryTerm) {
        this.deliveryTerm = deliveryTerm;
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

    @Override
    public String toString() {
        return "ProductShort{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", deliveryWithCourier='" + deliveryWithCourier + '\'' +
                ", deliveryTerm='" + deliveryTerm + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", oldPrice='" + oldPrice + '\'' +
                '}';
    }
}
