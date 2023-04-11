package projectBase_00.model.cart;

import projectBase_00.model.product.Product;

import java.io.Serializable;

public class OrderProduct implements Serializable {
    private int id;
    private Product product;
    private int quantity;

    public OrderProduct() {
    }

    public OrderProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderProduct(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderProduct[" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                ']';
    }
}
