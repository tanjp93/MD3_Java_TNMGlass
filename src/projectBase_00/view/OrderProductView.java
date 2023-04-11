package projectBase_00.view;

import projectBase_00.config.Config;
import projectBase_00.model.cart.OrderProduct;
import projectBase_00.model.product.Product;
import java.util.List;

public class OrderProductView {

    // list product style order Product in List cart
    // id of Cart not increase ,just be updated in List <oderListProduct>.
    //Only id of orderProduct in list <orderListProduct> be increasing.

    public OrderProductView() {
    }

    public OrderProduct oderListProduct() {
        System.out.println("Select Product");
        int idProduct = Integer.parseInt(Config.scanner.nextLine());
        Product product = new ProductView().findProductById(idProduct);
        if (product == null) {
            System.out.println("Try again !");
            oderListProduct();
        }
        System.out.println("Input quantity");
        int quantity = Integer.parseInt(Config.scanner.nextLine());
        OrderProduct orderProduct = new OrderProduct(product,quantity);
        return orderProduct;
    }

    public boolean checkProductInOrderList(List<OrderProduct> orderProductList, Product product) {
        for (OrderProduct orderProduct : orderProductList) {
            if (orderProduct.getProduct().getId() == product.getId()) {
                return true;
            }
        }
        return false;
    }
}