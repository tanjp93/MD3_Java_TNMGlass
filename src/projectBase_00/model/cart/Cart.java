package projectBase_00.model.cart;

import projectBase_00.model.product.Product;
import projectBase_00.model.user.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Cart implements Serializable {
    private int id;
    private User user;
    private List<OrderProduct> listProductCart;
    private String status;
    private LocalDateTime timeBuy;
    private long total;

    public Cart() {
    }

    public Cart(int id, User user, List<OrderProduct> listProductCart, String status, LocalDateTime timeBuy) {
        this.id = id;
        this.user = user;
        this.listProductCart = listProductCart;
        this.status = status;
        this.timeBuy = timeBuy;
        this.total = getTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderProduct> getListProductCart() {
        return listProductCart;
    }

    public void setListProductCart(List<OrderProduct> listProductCart) {
        this.listProductCart = listProductCart;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getTimeBuy() {
        return timeBuy;
    }

    public void setTimeBuy(LocalDateTime timeBuy) {
        this.timeBuy = timeBuy;
    }

    public long getTotal() {
        total=0;
        for (int i = 0; i < getListProductCart().size(); i++) {
            total += getListProductCart().get(i).getQuantity() * getListProductCart().get(i).getProduct().getPrice();
        }
        return total;
    }


    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart[" +
                "id=" + id +
                ", user=" + user +
                ", listProductCart=" + listProductCart +
                ", status='" + status + '\'' +
                ", timeBuy=" + timeBuy +
                ", total=" + total +
                ']';
    }

}
