package projectBase_00.view;

import projectBase_00.config.Config;
import projectBase_00.config.InputMethod;
import projectBase_00.controller.ProductController;
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
        OrderProduct orderProduct = null;
        System.out.println("Select Product");
        int idProduct = InputMethod.getInteger();
        Product product = new ProductView().findProductById(idProduct);
        if (product == null) {
            System.out.println("Try again !");
            oderListProduct();
        }
        System.out.println("Input quantity");
        int quantity = InputMethod.getInteger();
        int stokeTemp = product.getStoke() - quantity;
        if (stokeTemp < 0) {
            System.out.println("Try input quantity again or choose another product ");
            return null;
        } else {
            product.setStoke(product.getStoke() - quantity);
            new ProductController().updateProduct(product);
            orderProduct = new OrderProduct(product, quantity);
            return orderProduct;
        }
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
