package gnatyuk.java.core.read_excel;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Product {
    private List<String> categories;
    private Long id;
    private String productName;
    private Double price;
    private Double wholesalePrice;
    private URL linkToMicrotron;
    private URL linkToDescription;

    public Product() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(Double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public URL getLinkToMicrotron() {
        return linkToMicrotron;
    }

    public void setLinkToMicrotron(URL linkToMicrotron) {
        this.linkToMicrotron = linkToMicrotron;
    }

    public URL getLinkToDescription() {
        return linkToDescription;
    }

    public void setLinkToDescription(URL linkToDescription) {
        this.linkToDescription = linkToDescription;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Product{" +
                "categories=" + categories +
                ", id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", wholesalePrice=" + wholesalePrice +
                ", linkToMicrotron=" + linkToMicrotron +
                ", linkToDescription=" + linkToDescription +
                '}';
    }

    public boolean isContainNullValue(){
        return (categories != null && id != null &&
                productName != null && price != null &&
                wholesalePrice != null && linkToDescription != null
                && linkToMicrotron != null) ? false : true;
    }

    public static List<Product> generateListOfProduct(List<Map<String, Object>> rowProducts){
        return rowProducts.stream().map(Product::createProduct).collect(Collectors.toList());
    }

    private static Product createProduct(Map<String, Object> rowProduct){
        Product product = new Product();
        product.setId(new Double((Double) rowProduct.get("id")).longValue());
        product.setProductName((String) rowProduct.get("productName"));
        product.setPrice(Double.valueOf((Double) rowProduct.get("price")));
        product.setWholesalePrice(Double.valueOf((Double) rowProduct.get("wholesalePrice")));

        try {
            URL urlToMicron = new URL((String) rowProduct.get("linkToMicrotron"));
            product.setLinkToMicrotron(urlToMicron);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Object linkToDescription = rowProduct.get("linkToDescription");
            try {
                URL link = new URL((String) linkToDescription);
                product.setLinkToDescription(link);
            } catch (MalformedURLException e) {
                e.printStackTrace();
        }

        return product;
    }
}
