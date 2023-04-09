package projectBase_00.view;

import projectBase_00.controller.CartController;
import projectBase_00.controller.UserController;
import projectBase_00.model.cart.Cart;
import projectBase_00.model.user.User;

public class CartView {
    CartController cartController=new CartController();
    User userLogin = new UserController().getUserLogin();

    public CartView() {
    }

    public Cart userCart() {
        if (userLogin == null) {
            System.out.println("Please login first");
            new UserView().formLogin();
            userLogin = new UserController().getUserLogin();
            System.out.println("Sau khi dang nhap" +userLogin);
            showUserCart();
        }
        return cartController.getUserCart(userLogin);
    }

    public void showUserCart() {
        Cart cart=userCart();
        System.out.println("******************* User Cart *******************");
        System.out.println("--Id----User----Product Follow----Quantity----Product Price----Total Price----");
    }

    public static void main(String[] args) {
        new CartView().showUserCart();
    }
}
