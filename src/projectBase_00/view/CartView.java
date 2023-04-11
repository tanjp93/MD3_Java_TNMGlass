package projectBase_00.view;
import projectBase_00.config.Config;
import projectBase_00.controller.CartController;
import projectBase_00.controller.UserController;
import projectBase_00.model.cart.Cart;
import projectBase_00.model.cart.OrderProduct;
import projectBase_00.model.product.Product;
import projectBase_00.model.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartView {
    CartController cartController = new CartController();
    List<Cart> listCart = cartController.getListCart();


    public CartView() {
    }

    public void addProductToListCart(User user, OrderProduct orderProduct) {
        Cart cart = userCart(user);
        int idCart = 1;
        int idOrderProduct = 1;
        long total = 0;
        //1. Khong co list
        if (listCart.size() == 0) {
            cart = new Cart();
            List<OrderProduct> orderProductList = new ArrayList<>();
            cart.setId(idCart);//1
            orderProduct.setId(idOrderProduct);
            orderProductList.add(orderProduct);
            cart.setListProductCart(orderProductList);//2
            // order Product
            for (int i = 0; i < orderProductList.size(); i++) {
                total += orderProductList.get(i).getProduct().getPrice() * orderProductList.get(i).getQuantity();
            }
            cart.setTotal(total);//6
            cart.setUser(user);//2
            cart.setListProductCart(orderProductList);//3
            cart.setStatus("Order");//4
            LocalDateTime now = LocalDateTime.now();
            cart.setTimeBuy(now);//5
            //create a new list cart
        }
        //co list
        else {
            List<OrderProduct> orderProductList = (cart != null) ? cart.getListProductCart() : new ArrayList<>();
            //null Exxe
            if (cart != null) {
                //co ton tai user
                //Case 1 : user in list cart is existed .

                if (new OrderProductView().checkProductInOrderList(orderProductList, orderProduct.getProduct())) {
                    // co product in List va update
                    System.out.println("Product is existed in your Cart! Would you like to change quantity?");
                    System.out.println("1. Go to Cart");
                    System.out.println("2. Change quantity");
                        int choice = Integer.parseInt(Config.scanner.nextLine());
                        switch (choice) {
                            case 1:
                                showUserCart(user);
                                break;
                            case 2:
                                for (int i = 0; i < orderProductList.size(); i++) {
                                    if (orderProductList.get(i).getProduct().getId()==orderProduct.getProduct().getId()){
                                        int quantity=orderProductList.get(i).getQuantity()+orderProduct.getQuantity();
                                        System.out.println("quantity---->"+quantity);
                                        orderProduct.setQuantity(quantity);
                                    }
                                }
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again ! ");
                                new CategoryView();
                                break;
                    }
                } else {
                    //chua co proudct in List
                    idOrderProduct = orderProductList.get(orderProductList.size() - 1).getId() + 1;
                    orderProduct.setId(idOrderProduct);
                    orderProductList.add(orderProduct);
                }


            } else {
                //khong ton tai user
                //Case 2 : user in list cart is not existed .
                cart = new Cart();
                cart.setId(idCart);//1
                orderProduct.setId(idOrderProduct);
                orderProductList.add(orderProduct);
                cart.setListProductCart(orderProductList);//2
            }
            for (int i = 0; i < orderProductList.size(); i++) {
                total += orderProductList.get(i).getProduct().getPrice() * orderProductList.get(i).getQuantity();
            }
            // ***************************** //
            cart.setUser(user);//2
            cart.setListProductCart(orderProductList);//3
            cart.setStatus("Order");//4
            LocalDateTime now = LocalDateTime.now();
            cart.setTimeBuy(now);//5
            cart.setTotal(total);

            //update cart
            //update List
        }
        new CartController().addToCart(cart);
    }

    public Cart userCart(User user) {
        return cartController.getUserCart(user);
    }

    public void updateUserCart(User user, Product product, int quantity) {
        Cart updateCart = userCart(user);
        Product findProduct = new Product();

        cartController.update(updateCart);
    }

    public void showUserCart(User user) {
        Cart cart = userCart(user);
        System.out.println("cart --->" + cart);
        List<OrderProduct> listOrder = cart.getListProductCart();
        System.out.println("******************* User Cart *******************");
        System.out.println("--Id----User----Product Follow----Quantity----Product Price----Total Price----");
        listOrder.forEach(orderProduct -> {
            System.out.print("--" + cart.getId() + "----" + cart.getUser().getName() + "----");
            System.out.print("----" + orderProduct.getProduct().getProductName() + "----" + orderProduct.getQuantity() + "----");
            System.out.println();
        });
        System.out.println("------------- Total :------------" + cart.getTotal() + " vnd -----");
    }

    public void showListCart() {
        CartController cartController = new CartController();
        List<Cart> listCart = cartController.getListCart();
        System.out.println("listCart-->>>" + listCart);
        System.out.println("---User---------Product----------Quantity-------Total-----");
        listCart.forEach(cart -> {
            System.out.println(cart.getUser().getUsername() + "----:");
            List<OrderProduct> orderProductList = cart.getListProductCart();
            for (int i = 0; i < orderProductList.size(); i++) {
                System.out.println(orderProductList.get(i).getProduct().getProductName() + "----" + orderProductList.get(i).getQuantity() + "----");
            }
            System.out.println("------------- Total :------------" + cart.getTotal() + " vnd -----");
        });
    }

    public static void main(String[] args) {
        User userLogin = new UserController().getUserLogin();
        new CartView().showUserCart(userLogin);
    }
}
