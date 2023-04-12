package projectBase_00.model.product;

import projectBase_00.model.category.Category;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String productName;
    private String describe;
    private String img;
    private long price;
    private Category category;
    private  int stoke;

    public Product() {
    }

    public Product(int id, String productName, String describe, String img, long price, Category category, int stoke) {
        this.id = id;
        this.productName = productName;
        this.describe = describe;
        this.img = img;
        this.price = price;
        this.category = category;
        this.stoke=stoke;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStoke() {
        return stoke;
    }
    public void setStoke(int stoke) {
        this.stoke = stoke;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", describe='" + describe + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", stoke=" + stoke +
                '}';
    }
}
